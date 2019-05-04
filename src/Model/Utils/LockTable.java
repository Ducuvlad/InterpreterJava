package Model.Utils;

import java.util.HashMap;
import java.util.Map;

public class LockTable implements ILockTable {
    private HashMap<Integer, Integer> dictionary;
    private Integer index=1337;
    public LockTable() {
        this.dictionary = new HashMap<Integer, Integer>();
    }

    @Override
    public void put( Integer value) {
        dictionary.put(index, value); index++;
    }

    @Override
    public void remove(Integer key) {
        dictionary.remove(key);
    }

    @Override
    public boolean contains(Integer key) {
        return dictionary.containsKey(key);
    }

    @Override
    public Integer get(Integer key) {
        return dictionary.get(key);
    }

    @Override
    public void setContent(Map<Integer,Integer> map){
        this.dictionary=new HashMap<>(map);
    }
    @Override
    public Map<Integer,Integer> getContent(){
        return this.dictionary;
    }

    public String toString(){
        String s = "";
        for(Integer key : dictionary.keySet())
            s += key.toString() + "->" + dictionary.get(key).toString();
        return s;
    }

    public Integer getIndex() {
        return index;
    }

    @Override
    public void update(Integer key, Integer newvalue) {
        dictionary.replace(key,newvalue);
    }
}
