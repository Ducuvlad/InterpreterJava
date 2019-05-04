package Controller;

import Model.ProgramState;
import Model.Statement.IStatement;
import Model.Utils.*;
import Repository.IRepo;
import Repository.Repo;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class InterpreterController {
    IRepo repo;
    ExecutorService executor = Executors.newFixedThreadPool(4);

    Map<Integer, Integer> conservativeGarbageCollector(Collection<Integer> symTableValues, Map<Integer, Integer> heap) {
        return heap.entrySet().stream()
                .filter(e -> symTableValues.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    void closeAllRemainingFiles(Map<Integer, Pair<String, BufferedReader>> fTable) {
        fTable.values().stream()
                .forEach(e -> {
                    try {
                        e.getSecond().close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                });


    }

    public InterpreterController(IRepo repo) {
        this.repo = repo;
    }

    public ProgramState oneStep() throws Exception {
        ProgramState prg = repo.getProgram().get(0);
        MyStack<IStatement> stk = prg.getExeStack();
        IStatement crtStmt = stk.pop();
        try {
            return crtStmt.execute(prg);
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }

    public void allStep() throws Exception {
        ProgramState prg = repo.getProgram().get(0);
        while (!prg.getExeStack().isEmpty()) {
            try {
                prg.oneStep();
                prg.getHeap().setContent(conservativeGarbageCollector(prg.getSymTable().getContent().values(), prg.getHeap().getContent()));
            } catch (Exception e) {
                throw new Exception(e.toString());
            }

            try {
                repo.logPrgStateExec(prg);
            } catch (IOException ioe) {
                throw new IOException(ioe.toString());
            }
        }
        MyDictionary<Integer, Pair<String, BufferedReader>> ftable = prg.getFtable();
        closeAllRemainingFiles(ftable.getContent());
    }


    public List<ProgramState> removeCompletedPrgs(List<ProgramState> prgs) {
        return  prgs.stream()
                .filter(p -> p.isNotComplete())
                .collect(Collectors.toList());
    }

    public void oneStepForAllPrg(List<ProgramState> prgList) throws Exception {
        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        List<Callable<ProgramState>> callList = prgList.stream()
                .map((ProgramState p) -> (Callable<ProgramState>) (() -> {
                    return p.oneStep();
                }))
                .collect(Collectors.toList());

        List<ProgramState> newPrgList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        return null;
                    }
                }).filter(p -> p != null).collect(Collectors.toList());

        prgList.addAll(newPrgList);
        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        repo.setPrgList(prgList);
    }

    public void allStepForAll() throws Exception {
        executor = Executors.newFixedThreadPool(4);
        List<ProgramState>  prgList=removeCompletedPrgs(repo.getPrgs());
        IHeap h=prgList.get(0).getHeap();
        MyDictionary<Integer, Pair<String, BufferedReader>> ftable = prgList.get(0).getFtable();
        while(prgList.size() > 0){
            oneStepForAllPrg(prgList);
            prgList=removeCompletedPrgs(repo.getPrgs());
            prgList.forEach(prg->prg.getHeap().setContent(conservativeGarbageCollector(prg.getSymTable().getContent().values(),prg.getHeap().getContent())));
        }
        closeAllRemainingFiles(ftable.getContent());
        executor.shutdownNow();
        repo.setPrgList(prgList);
    }


    public IRepo getRepo() {
        return repo;
    }
}
