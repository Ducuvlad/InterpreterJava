package Repository;

import Model.ProgramState;
import Model.Statement.IStatement;

import java.io.*;
import java.util.ArrayList;

public class Repo implements IRepo {
    ArrayList<ProgramState> prgs=new ArrayList<ProgramState>();
    String logFilePath;

    public Repo(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    @Override
    public void add(ProgramState elem,int i) {
        prgs.add(i,elem);
    }
    @Override
    public void addstack(int i, IStatement s){
        prgs.get(i).getExeStack().push(s);

    }
    public int size(){
        return prgs.size();
    }

    @Override
    public void remove(ProgramState elem) {
        prgs.remove(elem);
    }
    @Override
    public ArrayList<ProgramState> getProgram(){
        return prgs;
    }

    @Override
    public void logPrgStateExec(ProgramState p) throws IOException {
        try{
        PrintWriter pw= new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        pw.append(p.toString());
        pw.close();
        }catch (java.io.IOException ioe){
            throw new IOException(ioe.toString());
        }


    }
}
