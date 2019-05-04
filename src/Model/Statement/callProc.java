package Model.Statement;

import Model.ProgramState;
import Model.Utils.GlobalTable;
import Model.Utils.IGlobalTable;
import Model.Utils.MyDictionary;

import java.util.List;

public class callProc implements IStatement {
    String fname;
    List<String> variables;

    public callProc(String fname, List<String> variables) {
        this.fname = fname;
        this.variables = variables;
    }

    @Override
    public ProgramState execute(ProgramState ps) throws Exception {
        IGlobalTable cGT=ps.getGlobalT();
        MyDictionary<String, Integer> oldSymTable=ps.getSymTable();
        MyDictionary<String, Integer> newSymTable=new MyDictionary<String, Integer>();
        if(!cGT.contains(fname))
            throw new Exception("No procedure with that name");
        else{
            for (String v: variables
                 ) {
                if(oldSymTable.contains(v))
                    newSymTable.put(v,oldSymTable.get(v));
                else
                    throw new Exception("Variable not found in old symtable");

            }
            ps.getFullStack().push(newSymTable);
            ps.getExeStack().push(new ReturnStm());
            ps.getExeStack().push(cGT.get(fname).getSecond()); //push IStatement body of procedure
        }
        return null;
    }
    @Override
    public String toString(){
        String s="Call procedure "+fname+" ";
        return s;
    }
}
