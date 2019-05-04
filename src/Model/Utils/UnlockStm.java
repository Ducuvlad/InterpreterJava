package Model.Utils;

import Model.ProgramState;
import Model.Statement.IStatement;

import java.util.concurrent.locks.ReentrantLock;

public class UnlockStm implements IStatement {
    private String varName;

    public UnlockStm(String varName) {
        this.varName = varName;
    }
    //unlock lockstatement
    @Override
    public ProgramState execute(ProgramState ps) throws Exception {
        if (!ps.getSymTable().contains(varName))
            throw new Exception("NO FOUND LOCK NAME IN SYMTABLE");
        Integer found = ps.getSymTable().get(varName);
        ReentrantLock lock = new ReentrantLock();
        lock.lock(); //lock
        if (!ps.getLookT().contains(found))
            return null;
        else {
            Integer tablevalue = ps.getLookT().get(found);
            if (tablevalue==ps.getID()) {
                ps.getLookT().update(tablevalue, -1);
            } else
                return null;
        }
        lock.unlock(); //unlock
        return null;
    }

    @Override
    public String toString() {
        String s = " Unlock(" + varName + ")";
        return s;
    }
}
