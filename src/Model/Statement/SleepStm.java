package Model.Statement;

import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Utils.MyStack;

public class SleepStm implements IStatement{
    private Integer nr;

    public SleepStm(Integer nr) {
        this.nr = nr;
    }

    public ProgramState execute(ProgramState ps) throws Exception {
        try {
            if (nr!=0) {
                MyStack<IStatement> exeStack = ps.getExeStack();
                IStatement newSleep=new SleepStm(nr-1);
                exeStack.push(newSleep);
            }
            else{}
        }catch (Exception e){throw new Exception(e.toString());}


        return null;
    }
    @Override
    public String toString(){
        String s= " Sleep: ("+nr+")";
        return s;
    }
}
