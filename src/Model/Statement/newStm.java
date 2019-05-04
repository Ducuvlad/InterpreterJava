package Model.Statement;

import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Utils.IHeap;
import Model.Utils.MyDictionary;

public class newStm implements IStatement {
    IExpression expr;
    String varName;

    public newStm(String varNam,IExpression expr) {
        this.expr = expr;
        this.varName = varNam;
    }

    @Override
    public ProgramState execute(ProgramState ps) throws Exception {
        IHeap h=ps.getHeap();
        MyDictionary<String, Integer> st = ps.getSymTable();
        try {
            int i=h.put(expr.evaluate(st,ps.getHeap()));
            st.put(this.varName, i);

        }catch (Exception e){}
        return null;
    }
    @Override
    public String toString(){
        String s="New "+varName+"="+expr.toString();
        return s;
    }
}
