package Repository;

import Model.ProgramState;
import Model.Statement.IStatement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface IRepo {
    List<ProgramState> getProgram();
    public void add(ProgramState elem);
    public void remove(ProgramState elem);
    //public void addstack(IStatement s);
    void logPrgStateExec(ProgramState p) throws IOException;
//void setPrgList(List<PrgState>)
    public void setPrgList(List<ProgramState> newprgs);
    public List<ProgramState> getPrgs();
}
