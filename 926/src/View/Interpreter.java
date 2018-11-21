package View;

import Controller.InterpreterController;
import Model.Expression.ArithExp;
import Model.Expression.ConstExp;
import Model.Expression.VarExp;
import Model.ProgramState;
import Model.Statement.AssignmentStm;
import Model.Statement.CompoundStm;
import Model.Statement.IStatement;
import Model.Statement.PrintStm;
import Repository.IRepo;
import Repository.Repo;

class Interpreter {
    public static void main(String[] args) {
        IStatement a= new AssignmentStm("a",new ArithExp(new ConstExp(2),new ArithExp(new ConstExp(3),new ConstExp(5),'*'),'+'));
        IStatement b= new CompoundStm(new AssignmentStm("b",new ArithExp(new VarExp("a"),new ConstExp(1),'+')),new PrintStm(new VarExp("b")));
        IStatement ex1= new CompoundStm(a,b);
        ProgramState prg1 = new ProgramState(ex1);
        IRepo repo1 = new Repo("log1.txt");
        repo1.add(prg1,0);
        repo1.addstack(0,ex1);
        InterpreterController ctr1 = new InterpreterController(repo1);

        IStatement ex2 =  new CompoundStm(new AssignmentStm("x",new ConstExp(11)),new PrintStm(new VarExp("x")));
        ProgramState prg2 = new ProgramState(ex2);
        IRepo repo2 = new Repo("log2.txt");
        repo2.add(prg2,0);
        repo2.addstack(0,ex2);
        InterpreterController ctr2 = new InterpreterController(repo2);

        IStatement ex3 = ex1;
        ProgramState prg3 = new ProgramState(ex3);
        IRepo repo3 = new Repo("log3.txt");
        repo3.add(prg3,0);
        repo3.addstack(0,ex3);
        InterpreterController ctr3 = new InterpreterController(repo3);
        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), ctr1));
        menu.addCommand(new RunExample("2", ex2.toString(), ctr2));
        menu.addCommand(new RunExample("3", ex3.toString(), ctr3));
        menu.show();
    }
}