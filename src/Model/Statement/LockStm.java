package Model.Statement;

import Model.ProgramState;

import java.util.concurrent.locks.ReentrantLock;

public class LockStm implements IStatement {
    private String varName;

    public LockStm(String varName) {
        this.varName = varName;
    }

    @Override
    public ProgramState execute(ProgramState ps) throws Exception {
        if (!ps.getSymTable().contains(varName))
            throw new Exception("NO FOUND LOCK NAME IN SYMTABLE");
        Integer found=ps.getSymTable().get(varName);
        ReentrantLock lock = new ReentrantLock();
        lock.lock(); //lock
        if (!ps.getLookT().contains(found))
            throw new Exception("NO FOUND LOCK NAME IN LOOKTABLE");
        else {
            Integer tablevalue=ps.getLookT().get(found);
            if (ps.getLookT().get(found) == -1)
            {
                ps.getLookT().update(tablevalue,ps.getID());
            }
            else
                ps.getExeStack().push(this);
        }
        lock.unlock(); //unlock
        return null;
    }

    @Override
    public String toString(){
        String s= " Lock("+varName+")";
        return s;
    }
}
