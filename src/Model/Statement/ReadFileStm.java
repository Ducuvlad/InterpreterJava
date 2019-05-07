package Model.Statement;

import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Utils.MyDictionary;
import Model.Utils.Pair;

import java.io.BufferedReader;

public class readFileStm implements IStatement {
    IExpression exp_file_id;
    String var_name;

    public readFileStm(IExpression exp_file_id, String var_name) {
        this.exp_file_id = exp_file_id;
        this.var_name = var_name;
    }

    @Override
    public ProgramState execute(ProgramState ps) throws Exception {
        Integer i=exp_file_id.evaluate(ps.getSymTable(),ps.getHeap());
        MyDictionary<Integer,Pair<String, BufferedReader>> ftable= ps.getFtable();
        MyDictionary<String, Integer> st = ps.getSymTable();
        if(!ftable.contains(i))
            throw new Exception ("No file");

            Pair<String,BufferedReader> pair=ftable.get(i);
            BufferedReader bf=pair.getSecond();
            String line=bf.readLine();
            if (line==null)
                line="0";
            int val=Integer.parseInt(line);
            if(st.contains(var_name))
                st.remove(var_name);
            st.put(var_name,val);

        return null;
    }
    @Override
    public String toString(){
        String s= "\n read from id:"+exp_file_id.toString()+" into variable:" +var_name;
        return s;
    }
}
