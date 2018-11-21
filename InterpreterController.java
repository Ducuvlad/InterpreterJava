package Controller;

import Model.ProgramState;
import Model.Statement.IStatement;
import Model.Utils.MyStack;
import Repository.IRepo;
import Repository.Repo;

import java.io.IOException;

public class InterpreterController {
    IRepo repo;

    public InterpreterController(IRepo repo) {
        this.repo = repo;
    }

    public ProgramState oneStep(int i) throws Exception {
        ProgramState prg=repo.getProgram().get(i);
        MyStack <IStatement> stk=prg.getExeStack();
        IStatement crtStmt=stk.pop();
        try {
            return crtStmt.execute(prg);
        }catch (Exception e){throw new Exception(e.toString());}
    }
    public void allStep(int i) throws Exception {
        ProgramState prg=repo.getProgram().get(i);
        while(!prg.getExeStack().isEmpty()){
            try {
                oneStep(i);
            }catch (Exception e){throw new Exception(e.toString());}

            try {
                repo.logPrgStateExec(prg);
            }catch (IOException ioe){throw new IOException(ioe.toString()); }
        }
    }
}
