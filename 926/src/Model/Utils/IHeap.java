package Model.Utils;

import java.util.Map;

public interface IHeap {
    int put(int v);
    void remove(int k);
    boolean contains(int k);
    int get(int k);
    String toString();
    void update(int k,int v);
    void setContent(Map<Integer,Integer> map);
    Map<Integer,Integer> getContent();
}
