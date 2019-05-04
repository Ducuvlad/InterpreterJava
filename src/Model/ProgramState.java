package Model;

import Model.Statement.IStatement;
import Model.Utils.*;

import java.io.BufferedReader;
import java.util.Stack;

public class ProgramState {
    private MyStack<IStatement> exeStack = new MyStack<>();
    private MyList<Integer> output = new MyList<>();
    //private MyDictionary<String, Integer> symTable = new MyDictionary<>();
    private IStatement program;
    private int ID=1;
    public MyDictionary<Integer,Pair<String, BufferedReader>> ftable=new MyDictionary<Integer,Pair<String, BufferedReader>>();
    private LockTable  LookT=new LockTable();
    private IHeap heap=new Heap();
    private MyStack<MyDictionary<String, Integer>> symTable=new MyStack<MyDictionary<String, Integer>>();
    private IGlobalTable GlobalT;

    public IGlobalTable getGlobalT() {
        return GlobalT;
    }

    public void setGlobalT(IGlobalTable globalT) {
        GlobalT = globalT;
    }

    public ProgramState(MyStack<IStatement> exeStack, MyList<Integer> output, IStatement program, MyStack<MyDictionary<String, Integer>> symTable, IGlobalTable globalT) {
        this.exeStack = exeStack;
        this.output = output;
        this.program = program;
        this.symTable = symTable;
        GlobalT = globalT;
    }

    public ProgramState(MyStack<IStatement> exeStack, MyList<Integer> output, IStatement program, int ID, MyDictionary<Integer, Pair<String, BufferedReader>> ftable, LockTable lookT, IHeap heap, MyStack<MyDictionary<String, Integer>> symTable, IGlobalTable globalT) {
        this.exeStack = exeStack;
        this.output = output;
        this.program = program;
        this.ID = ID;
        this.ftable = ftable;
        LookT = lookT;
        this.heap = heap;
        this.symTable = symTable;
        GlobalT = globalT;
    }

    public ProgramState(MyStack<IStatement> exeStack,
                        MyList<Integer> output,
                        MyStack<MyDictionary<String, Integer>>
                                symTable, IStatement program) {
        this.exeStack = exeStack;
        this.output = output;
        this.symTable = symTable;
        this.program = program;

    }

    public ProgramState(MyStack<IStatement> exeStack, MyList<Integer> output, MyStack<MyDictionary<String, Integer>> symTable, IStatement program, int ID, MyDictionary<Integer, Pair<String, BufferedReader>> ftable, LockTable lookT, IHeap heap) {
        this.exeStack = exeStack;
        this.output = output;
        this.symTable = symTable;
        this.program = program;
        this.ID = ID;
        this.ftable = ftable;
        LookT = lookT;
        this.heap = heap;
    }

    public ProgramState(MyStack<IStatement> exeStack, MyList<Integer> output, MyStack<MyDictionary<String, Integer>> symTable, IStatement program, int ID, MyDictionary<Integer, Pair<String, BufferedReader>> ftable, IHeap heap) {
        this.exeStack = exeStack;
        this.output = output;
        this.symTable = symTable;
        this.program = program;
        this.ID = ID;
        this.ftable = ftable;
        this.heap = heap;
    }

    public ProgramState(MyStack<IStatement> exeStack,
                        MyList<Integer> output,
                        MyStack<MyDictionary<String, Integer>>
                                symTable, IStatement program, MyDictionary<Integer,Pair<String, BufferedReader>> FileTable){
        this.exeStack = exeStack;
        this.output = output;
        this.symTable = symTable;
        this.program = program;
        this.ftable=FileTable;

    }

    public ProgramState(MyStack<IStatement> exeStack, MyList<Integer> output, MyStack<MyDictionary<String, Integer>> symTable, IStatement program, MyDictionary<Integer, Pair<String, BufferedReader>> ftable, IHeap heap) {
        this.exeStack = exeStack;
        this.output = output;
        this.symTable = symTable;
        this.program = program;
        this.ftable = ftable;
        this.heap = heap;
    }

    public ProgramState(IStatement program) {
        this.program = program;
        //exeStack.push(program);
    }

    public MyDictionary<String, Integer> getSymTable()
    {
        MyDictionary<String, Integer> topSym= symTable.pop();
        symTable.push(topSym);
        return topSym;
    }

    public void setSymTable(MyStack<MyDictionary<String, Integer>> symTable) {
        this.symTable = symTable;
    }

    public MyStack<IStatement> getExeStack() {
        return exeStack;
    }

    public void setExeStack(MyStack<IStatement> exeStack) {
        this.exeStack = exeStack;
    }

    public MyList<Integer> getOutput() {
        return output;
    }

    public void setOutput(MyList<Integer> output) {
        this.output = output;
    }

    public void setFtable(MyDictionary<Integer, Pair<String, BufferedReader>> ftable) {
        this.ftable = ftable;
    }

    public IHeap getHeap() {
        return heap;
    }

    public void setHeap(IHeap heap) {
        this.heap = heap;
    }

    void setProgram(IStatement prg) {
        this.program = prg;
    }

    IStatement getProgram() {
        return this.program;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public MyDictionary<Integer, Pair<String, BufferedReader>> getFtable() {
        return ftable;
    }

    @Override
    public String toString(){
        String s= "ID: "+ID+"\nExeStack:\n "+exeStack.toString()+"\n symTable:\n"+symTable.toString()+"\n Output:\n"+ output.toString()+"\n FileTable: \n"+ ftable.toString()+"\n Heap: \n"+ heap.toString();
        return s;
    }

    public Boolean isNotComplete(){
        return !exeStack.isEmpty();
    }
    /*
    PrgState oneStep(){
    if(exeStack.isEmpty()) throws MyStmtExecException;
    IStmt  crtStmt = exeStack.pop();
    return crtStmt.execute(this);
    }
     */
    public ProgramState oneStep() throws Exception {
        try {
            IStatement crtStmt = exeStack.pop();
            return crtStmt.execute(this);
        }catch(Exception e){throw new Exception(e.toString()+"ProgramStateError");}
    }

    public LockTable getLookT() {
        return LookT;
    }

    public void setLookT(LockTable lookT) {
        LookT = lookT;
    }
    public MyStack<MyDictionary<String, Integer>> getFullStack(){
        return symTable;
    }

}
