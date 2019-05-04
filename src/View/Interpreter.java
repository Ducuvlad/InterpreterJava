package View;

import Controller.InterpreterController;
import Model.Expression.ArithExp;
import Model.Expression.ConstExp;
import Model.Expression.HReadExp;
import Model.Statement.HWriteExp;
import Model.Expression.VarExp;
import Model.ProgramState;
import Model.Statement.*;
import Model.Utils.*;
import Repository.IRepo;
import Repository.Repo;

import java.io.BufferedReader;

class Interpreter {
    public static void main(String[] args) {
        /*
        Command line start point with text menu
         */
        MyStack<IStatement> exeStack = new MyStack<>();
        MyList<Integer> output = new MyList<>();
        MyDictionary<String, Integer> symTable = new MyDictionary<>();

         MyDictionary<Integer, Pair<String, BufferedReader>> ftable=new MyDictionary<Integer,Pair<String, BufferedReader>>();
        IHeap heap=new Heap();

        IStatement a= new AssignmentStm("a",new ArithExp(new ConstExp(2),new ArithExp(new ConstExp(3),new ConstExp(5),'*'),'+'));
        IStatement b= new CompoundStm(new AssignmentStm("b",new ArithExp(new VarExp("a"),new ConstExp(1),'+')),new PrintStm(new VarExp("b")));
        IStatement ex1= new CompoundStm(a,b);
        MyStack<MyDictionary<String, Integer>> newSymStack= new MyStack<MyDictionary<String, Integer>>();
        newSymStack.push(symTable);
        ProgramState prg1 = new ProgramState(exeStack,output,newSymStack,ex1,ftable,heap);
        IRepo repo1 = new Repo("log1.txt");
        prg1.getExeStack().push(ex1);
        repo1.add(prg1);
        InterpreterController ctr1 = new InterpreterController(repo1);

        IStatement ex2 =  new CompoundStm(new AssignmentStm("x",new ConstExp(11)),new PrintStm(new VarExp("x")));
        ProgramState prg2 = new ProgramState(ex2);
        IRepo repo2 = new Repo("log2.txt");
        prg1.getExeStack().push(ex2);
        repo2.add(prg2);

        InterpreterController ctr2 = new InterpreterController(repo2);

        IStatement ex3 = new CompoundStm(new newStm("v",new ConstExp(20)),new CompoundStm(new newStm("a",new ConstExp(22)),new CompoundStm(new HWriteExp("a",new ConstExp(30)),new PrintStm(new ConstExp(30)))));
        ProgramState prg3 = new ProgramState(ex3);
        IRepo repo3 = new Repo("log3.txt");
        prg1.getExeStack().push(ex3);
        repo3.add(prg3);
        InterpreterController ctr3 = new InterpreterController(repo3);

        IStatement ex4 = new CompoundStm( new CompoundStm(new CompoundStm(new openRFileStm(0, "C:\\Users\\ducuv\\source\\repos\\MAP_A7\\MAP_A7\\View\\file1.txt"),new readFileStm(new ConstExp(0),"c")),new PrintStm(new VarExp("c"))),new closeRFile(new ConstExp(0)));
                ProgramState prg4 = new ProgramState(ex4);
        IRepo repo4 = new Repo("log4.txt");
        prg1.getExeStack().push(ex4);
        repo4.add(prg4);
        InterpreterController ctr4 = new InterpreterController(repo4);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), ctr1));
        menu.addCommand(new RunExample("2", ex2.toString(), ctr2));
        menu.addCommand(new RunExample("3", ex3.toString(), ctr3));
        menu.addCommand(new RunExample("4", ex4.toString(), ctr4));




        menu.show();
    }
}