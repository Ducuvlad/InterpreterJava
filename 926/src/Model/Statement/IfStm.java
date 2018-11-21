package Model.Statement;

import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Utils.MyDictionary;
import Model.Utils.MyStack;

public class IfStm implements IStatement {
    private IStatement ifst, elsest2;
    private IExpression exp;

    public IfStm(IStatement ifst, IStatement elsest2, IExpression exp) {
        this.ifst = ifst;
        this.elsest2 = elsest2;
        this.exp = exp;
    }

    public ProgramState execute(ProgramState ps) {
        MyStack<IStatement> exeStack = ps.getExeStack();
        MyDictionary<String, Integer> st = ps.getSymTable();
        try {
            if (exp.evaluate(st,ps.getHeap()) == 0)
                exeStack.push(this.ifst);
            else
                exeStack.push(this.elsest2);
        }catch (Exception e){}
        return ps;
    }
    @Override
    public String toString(){
        String s= "if "+exp.toString()+" then "+ ifst.toString() + " else "+ elsest2.toString();
        return s;
    }
}
