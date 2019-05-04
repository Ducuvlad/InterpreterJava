package Model.Statement;

import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Utils.ISymTable;
import Model.Utils.MyDictionary;

public class AssignmentStm implements IStatement {
    //Assign a variable an IExpression
    //example: new AssignmentStm("variable1",new ConstExp(3))
    private String varName;
    private IExpression expr;

    public AssignmentStm(String varName, IExpression expr) {
        this.varName = varName;
        this.expr = expr;
    }

    public ProgramState execute(ProgramState ps) {
        MyDictionary<String, Integer> st = ps.getSymTable();
        try {
            st.put(this.varName, this.expr.evaluate(st,ps.getHeap()));
        }catch (Exception e){}
        return null;
    }
    @Override
    public String toString(){
        String s= varName+"="+expr.toString();
        return s;
    }
}
