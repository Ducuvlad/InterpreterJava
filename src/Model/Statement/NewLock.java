package Model.Statement;

import Model.ProgramState;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class newLock implements IStatement {
    private String varName;

    public newLock(String varName) {
        this.varName = varName;
    }

    public ProgramState execute(ProgramState ps){
//LockTable2 = LockTable1 synchronizedUnion {newfreelocation ->-1}
//if var exists in SymTable1 then
//SymTable2 = update(SymTable1,var, newfreelocation)
// else SymTable2 = add(SymTable1,var, newfreelocation)
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        ps.getLookT().put(-1);
        if(ps.getSymTable().contains(varName)){
            ps.getSymTable().remove(varName);
        }
        ps.getSymTable().put(varName,ps.getLookT().getIndex()-1);
        lock.unlock();
        return null;

    }
    @Override
    public String toString(){
        String s= " NewLock("+varName+")";
        return s;
    }
}
