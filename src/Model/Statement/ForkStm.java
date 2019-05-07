package Model.Statement;

import Model.ProgramState;
import Model.Utils.*;

import java.io.BufferedReader;

public class forkStm implements IStatement {
    private IStatement st1;

    public forkStm(IStatement st1) {
        this.st1 = st1;
    }
    //create and return new ProgramState
    @Override
    public ProgramState execute(ProgramState ps) throws Exception {
        MyStack<IStatement> exeStack=new MyStack<IStatement>(); //different exe stacks
        exeStack.push(st1);
        MyList<Integer> output = ps.getOutput(); //same output
        MyStack<MyDictionary<String, Integer>> oldSymStack= ps.getFullStack();//different symtables
        MyStack<MyDictionary<String, Integer>> newSymStack= new MyStack<MyDictionary<String, Integer>>();
        newSymStack.stack=oldSymStack.clona();
        IGlobalTable newGT=ps.getGlobalT();//same global table
        IHeap heap=ps.getHeap(); //same heap
        LockTable locktable= ps.getLookT();
        int ID=ps.getID();//different id , will be +1 later
        MyDictionary<Integer, Pair<String, BufferedReader>> ftable=ps.getFtable();
        //this.exeStack = exeStack;
        //        this.output = output;
        //        this.program = program;
        //        this.ID = ID;
        //        this.ftable = ftable;
        //        LookT = lookT;
        //        this.heap = heap;
        //        this.symTable = symTable;
        //        GlobalT = globalT;
        return new ProgramState(exeStack,output,st1,ps.getID()+1,ftable,locktable,heap,newSymStack,newGT);

    }
    @Override
    public String toString(){
        String s= "FORK( "+st1.toString()+" ) ";
        return s;
    }
}
