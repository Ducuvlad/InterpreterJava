package Model.Statement;

import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Utils.IHeap;
import Model.Utils.MyDictionary;

public class HWriteExp implements IStatement {
    String varName;
    IExpression expr;

    public HWriteExp(String varName,IExpression expr) {
        this.varName = varName;
        this.expr=expr;
    }

    @Override
    public ProgramState execute(ProgramState ps) throws Exception {
        if(ps.getSymTable().contains(varName)) {
            int i = ps.getSymTable().get(varName);
            if (ps.getHeap().contains(i)) {
                ps.getHeap().update(i,expr.evaluate(ps.getSymTable(),ps.getHeap()));
                return ps;
            }
            else throw new Exception ("Not in heap");
        }
        else throw new Exception ("Not in symtable");
    }
    @Override
    public String toString(){
        String s="Write "+varName+"="+expr.toString();
        return s;
    }
}
