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

    public ProgramState execute(ProgramState ps) throws Exception {
        MyList<Integer> newoutput=ps.getOutput();
        try {
            int i = exp.evaluate(ps.getSymTable(), ps.getHeap());
            newoutput.add(i);
            ps.setOutput(newoutput);
        }catch (Exception e){throw new Exception("Cant eval");}

        return ps;
    }
    @Override
    public String toString(){
        String s= exp.toString();
        return s;
    }
}
