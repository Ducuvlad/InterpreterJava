package Repository;

import Model.ProgramState;
import Model.Statement.IStatement;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Repo implements IRepo {
    List<ProgramState> prgs=new ArrayList<ProgramState>();
    String logFilePath;

    public List<ProgramState> getPrgs() {
        return prgs;
    }

    public Repo(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    @Override
    public void add(ProgramState elem) {
        prgs.add(elem);
    }
    /*@Override
    public void addstack(IStatement s){
        prgs.get(i).getExeStack().push(s);

    }*/
    public int size(){
        return prgs.size();
    }

    @Override
    public void remove(ProgramState elem) {
        prgs.remove(elem);
    }
    @Override
    public List<ProgramState> getProgram(){
        return prgs;
    }

    @Override
    public void logPrgStateExec(ProgramState p) throws IOException {
        try{
        PrintWriter pw= new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        pw.append("#################"+p.toString());
        pw.close();
        }catch (java.io.IOException ioe){
            throw new IOException(ioe.toString());
        }


    }

    @Override
    public void setPrgList(List<ProgramState> newprgs) {
        this.prgs=newprgs;
    }
}
