package Model.Statement;

import Model.ProgramState;
import Model.Utils.IExeStack;
import Model.Utils.MyStack;

public class CompoundStm implements IStatement {
    private IStatement st1, st2;

    public CompoundStm(IStatement st1, IStatement st2) {
        this.st1=st1;
        this.st2=st2;
    }

    public ProgramState execute(ProgramState ps) {
        MyStack<IStatement> exeStack = ps.getExeStack();
        exeStack.push(this.st2);
        exeStack.push(this.st1);

        return ps;
    }
    @Override
    public String toString(){
        String s= st1.toString()+" then "+st2.toString();
        return s;
    }
}
