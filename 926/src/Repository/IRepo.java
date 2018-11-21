package Repository;

import Model.ProgramState;
import Model.Statement.IStatement;

import java.io.IOException;
import java.util.ArrayList;

public interface IRepo {
    ArrayList<ProgramState> getProgram();
    public void add(ProgramState elem,int i);
    public void remove(ProgramState elem);
    public void addstack(int i, IStatement s);
    void logPrgStateExec(ProgramState p) throws IOException;
}
