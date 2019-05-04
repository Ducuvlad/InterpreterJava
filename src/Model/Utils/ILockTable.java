package Model.Utils;

import java.util.Map;

public interface ILockTable {
    void put( Integer value);
    void remove(Integer key);
    boolean contains(Integer key);
    Integer get(Integer key);
    String toString();
    void setContent(Map<Integer,Integer> map);
    Map<Integer,Integer> getContent();
    void update(Integer key,Integer newvalue);
}
