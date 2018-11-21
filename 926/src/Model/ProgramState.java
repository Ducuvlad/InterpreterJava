package Model;

import Model.Statement.IStatement;
import Model.Utils.*;

import java.io.BufferedReader;

public class ProgramState {
    private MyStack<IStatement> exeStack = new MyStack<>();
    private MyList<Integer> output = new MyList<>();
    private MyDictionary<String, Integer> symTable = new MyDictionary<>();
    private IStatement program;
    public MyDictionary<Integer,Pair<String, BufferedReader>> ftable=new MyDictionary<Integer,Pair<String, BufferedReader>>();
    private IHeap heap=new Heap();
    public ProgramState(MyStack<IStatement> exeStack,
                        MyList<Integer> output,
                        MyDictionary<String, Integer>
                                symTable, IStatement program) {
        this.exeStack = exeStack;
        this.output = output;
        this.symTable = symTable;
        this.program = program;

    }

    public ProgramState(MyStack<IStatement> exeStack,
                        MyList<Integer> output,
                        MyDictionary<String, Integer>
                                symTable, IStatement program,MyDictionary<Integer,Pair<String, BufferedReader>> FileTable){
        this.exeStack = exeStack;
        this.output = output;
        this.symTable = symTable;
        this.program = program;
        this.ftable=FileTable;

    }

    public ProgramState(IStatement program) {
        this.program = program;
        //exeStack.push(program);
    }

    public MyDictionary<String, Integer> getSymTable() {
        return symTable;
    }

    public void setSymTable(MyDictionary<String, Integer>
                                    symTable) {
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

    public MyDictionary<Integer, Pair<String, BufferedReader>> getFtable() {
        return ftable;
    }

    @Override
    public String toString(){
        String s= "ExeStack:\n "+exeStack.toString()+"\n symTable:\n"+symTable.toString()+"\n Output:\n"+ output.toString()+"\n FileTable: \n"+ ftable.toString()+"\n Heap: \n"+ heap.toString();
        return s;
    }
}
