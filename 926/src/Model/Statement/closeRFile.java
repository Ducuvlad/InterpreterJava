package Model.Statement;

import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Utils.MyDictionary;
import Model.Utils.Pair;

import java.io.BufferedReader;
import java.nio.Buffer;

public class closeRFile implements IStatement {
    IExpression exp_file_id;

    public closeRFile(IExpression exp_file_id) {
        this.exp_file_id = exp_file_id;
    }

    @Override
    public ProgramState execute(ProgramState ps) throws Exception {
        Integer i=exp_file_id.evaluate(ps.getSymTable(),ps.getHeap());
        MyDictionary<Integer,Pair<String, BufferedReader>> ftable= ps.getFtable();
        if(ftable.contains(i)) throw new Exception("no file found");
        Pair<String, BufferedReader> pair=ftable.get(i);
        BufferedReader bf=pair.getSecond();
        bf.close();
        ftable.remove(i);
        return ps;
    }
    @Override
    public String toString(){
        String s= "\n close id:"+exp_file_id.toString();
        return s;
    }
}
