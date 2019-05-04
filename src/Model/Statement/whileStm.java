package Model.Statement;

import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Utils.MyStack;

public class whileStm implements IStatement {
    private IStatement st1;
    private IExpression exp;

    public whileStm(IStatement st1, IExpression exp) {
        this.st1 = st1;
        this.exp = exp;
    }

    public ProgramState execute(ProgramState ps) throws Exception {
        try {
            if (exp.evaluate(ps.getSymTable(), ps.getHeap())!=0) {
                MyStack<IStatement> exeStack = ps.getExeStack();
                exeStack.push(this);
                exeStack.push(this.st1);
            }
        }catch (Exception e){throw new Exception(e.toString());}


        return null;
    }
    @Override
    public String toString(){
        String s= " While "+exp.toString()+" then "+st1.toString();
        return s;
    }
}
