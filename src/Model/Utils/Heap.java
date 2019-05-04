package Model.Utils;

import java.util.HashMap;
import java.util.Map;

public class Heap implements IHeap{
    Integer newFreeSpace=1;
    HashMap<Integer,Integer> HM=new HashMap<Integer, Integer>();
    @Override
    public void setContent(Map<Integer,Integer> map){
        this.HM=new HashMap<>(map);
    }
    @Override
    public Map<Integer,Integer> getContent(){
        return this.HM;
    }

    @Override
    public int put(int v) {
        HM.put(newFreeSpace,v);
        newFreeSpace+=1;
        return newFreeSpace-1;
    }

    @Override
    public void remove(int k) {
        HM.remove(k);
    }

    @Override
    public boolean contains(int k) {
        return HM.containsKey(k);
    }

    @Override
    public int get(int k) {
        return HM.get(k);
    }

    @Override
    public String toString(){
        String s = " ";
        for(int key : HM.keySet())
            s += Integer.toString(key) + "->" + HM.get(key).toString()+" ";
        return s;
    }

    @Override
    public void update(int k, int v) {
        HM.replace(k,v);
    }
}
