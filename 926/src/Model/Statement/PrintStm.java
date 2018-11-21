package Model.Statement;

import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Utils.MyList;

import java.beans.Expression;

public class PrintStm implements IStatement {
    IExpression exp;

    public PrintStm(IExpression exp) {
        this.exp = exp;
    }

    public ProgramState execute(ProgramState ps) {
        MyList<Integer> newoutput=ps.getOutput();
        int i=ps.getSymTable().get(exp.toString());
        newoutput.add(i);
        ps.setOutput(newoutput);
        return ps;
    }
    @Override
    public String toString(){
        String s= exp.toString();
        return s;
    }
}
