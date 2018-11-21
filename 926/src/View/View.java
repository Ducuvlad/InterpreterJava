package View;

import Controller.InterpreterController;
import Model.Expression.ArithExp;
import Model.Expression.ConstExp;
import Model.Expression.VarExp;
import Model.ProgramState;
import Model.Statement.*;
import Model.Utils.MyDictionary;
import Model.Utils.MyList;
import Model.Utils.MyStack;
import Repository.IRepo;
import Repository.Repo;

import java.io.IOException;

public class View {
    static String namevar="C:\\Users\\ducuv\\Desktop\\926\\src\\View\\test.in";
    public static void main(String[] args) {
        MyStack<IStatement> exeStack = new MyStack<>();
        MyDictionary<String, Integer> symTable = new MyDictionary<>();
        MyList<Integer> output = new MyList<>();
        IStatement op = new PrintStm(new ConstExp(2));/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //a=2+3*5;b=a+1;Print(b)
        IStatement a= new AssignmentStm("a",new ArithExp(new ConstExp(2),new ArithExp(new ConstExp(3),new ConstExp(5),'*'),'+'));
        IStatement b= new CompoundStm(new AssignmentStm("b",new ArithExp(new VarExp("a"),new ConstExp(1),'+')),new PrintStm(new VarExp("b")));
        IStatement op2= new CompoundStm(a,b);

        //a=2-2;(If a Then v=2 Else v=3);Print(v)
        //IStatement a1=new AssignmentStm("a",new ArithExp(new ConstExp(2),new ArithExp(new ConstExp(3),new ConstExp(5),'*'),'+'));
        //IStatement b1=new CompoundStm(new IfStm(new VarExp("a",new AssignmentStm("v",new Const))))
        //ProgramState ps2 = new ProgramState(exeStack, output, symTable, op2);
        IRepo r=new Repo("logfile1.txt");
        //r.add(ps2,0);
        //r.addstack(0,op2);


        String var_c="var_c";
        IStatement ex1=new openRFileStm(1,namevar);
        IStatement ex1r=new readFileStm(new ConstExp(1),var_c);
        IStatement prn=new PrintStm(new VarExp("var_c"));

        ProgramState ps3 = new ProgramState(exeStack, output, symTable, ex1);
        r.add(ps3,0);
        r.addstack(0,prn);
        r.addstack(0,ex1r);
        r.addstack(0,ex1);
        InterpreterController c=new InterpreterController(r);
        try {

            c.allStep(0);
            System.out.println(ps3.toString());
        }
            catch (java.util.EmptyStackException e){ System.out.println("Empty stack"); }
            catch (IOException ioe){System.out.println(ioe.toString());}
            catch(Exception excep){System.out.println(excep.toString());}
    }
}
