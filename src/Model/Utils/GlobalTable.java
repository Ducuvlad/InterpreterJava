package Model.Utils;

import Model.Statement.IStatement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlobalTable implements IGlobalTable {
    //Map of nameOfProcedure->list of localVariableNames , procedure execution statement
    HashMap<String,Pair<List<String>, IStatement>> HM=new HashMap<String,Pair<List<String>, IStatement>>();
    @Override
    public void setContent(HashMap<String,Pair<List<String>, IStatement>> map){
        this.HM=new HashMap<>(map);
    }
    @Override
    public Map<String, Pair<List<String>, IStatement>> getContent(){
        return this.HM;
    }

    @Override
    public int put(String k,Pair<List<String>, IStatement> v) {
        if(!HM.containsKey(k)){
            HM.put(k,v);
            return 1;
        }
        return -1;
    }

    @Override
    public void remove(String k) {
        HM.remove(k);
    }

    @Override
    public boolean contains(String k) {
        return HM.containsKey(k);
    }

    @Override
    public Pair<List<String>, IStatement> get(String k) {
        return HM.get(k);
    }

    @Override
    public String toString(){
        String s = " ";
        for(String key : HM.keySet())
            s += key + "->" + HM.get(key).toString()+" ";
        return s;
    }

    @Override
    public void update(String k, Pair<List<String>, IStatement> v) {
        HM.replace(k,v);
    }
}
