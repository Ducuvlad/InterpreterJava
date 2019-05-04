package View;

import Controller.InterpreterController;
import Model.Expression.ArithExp;
import Model.Expression.ConstExp;
import Model.Expression.HReadExp;
import Model.Expression.VarExp;
import Model.ProgramState;
import Model.Statement.*;
import Model.Utils.*;
import Repository.IRepo;
import Repository.Repo;

import java.io.BufferedReader;
import java.io.IOException;

public class View {
    static String namevar="C:\\Users\\ducuv\\Desktop\\926\\src\\View\\test.in";
    public static void main(String[] args) {
        MyStack<IStatement> exeStack = new MyStack<>();
        MyDictionary<String, Integer> symTable = new MyDictionary<>();
        MyList<Integer> output = new MyList<>();
        IStatement op = new forkStm(new PrintStm(new ConstExp(2)));/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        MyDictionary<Integer, Pair<String, BufferedReader>> ftable=new MyDictionary<Integer,Pair<String, BufferedReader>>();
        IHeap heap=new Heap();
        IRepo r=new Repo("LogFile11");
        IStatement a= new AssignmentStm("a",new ArithExp(new ConstExp(2),new ArithExp(new ConstExp(3),new ConstExp(5),'*'),'+'));
        IStatement b= new CompoundStm(new AssignmentStm("b",new ArithExp(new VarExp("a"),new ConstExp(1),'+')),new PrintStm(new VarExp("b")));
        IStatement ex1= new CompoundStm(a,b);
        //exeStack.push(op);

        //exemplu fork
        IStatement f1=new CompoundStm(new AssignmentStm("v",new ConstExp(10)),new newStm("a",new ConstExp(22)));
        IStatement f2=new forkStm(new CompoundStm(new HWriteExp("a",new ConstExp(30)),new CompoundStm(new AssignmentStm("v",new ConstExp(32)),new CompoundStm(new PrintStm(new VarExp("v")),new PrintStm(new HReadExp("a"))))));
        IStatement f3=new CompoundStm(new PrintStm(new VarExp("v")),new PrintStm(new HReadExp("a")));
        IStatement ff=new CompoundStm(f1,new CompoundStm(f2,f3));
        exeStack.push(ff);
        MyStack<MyDictionary<String, Integer>> newSymStack= new MyStack<MyDictionary<String, Integer>>();
        newSymStack.push(symTable);
        ProgramState ps3 = new ProgramState(exeStack, output, newSymStack,ff,1,ftable,heap);
        r.add(ps3);
        InterpreterController c=new InterpreterController(r);
        try {

            c.allStepForAll();

        }
            catch (java.util.EmptyStackException e){ System.out.println("Empty stack"); }
            catch (IOException ioe){System.out.println(ioe.toString());}
            catch(Exception excep){System.out.println(excep.toString());}
        //System.out.println(ps3.toString());
    }
}
