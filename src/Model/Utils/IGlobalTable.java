package Model.Utils;

import Model.Statement.IStatement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IGlobalTable {

        int put(String k,Pair<List<String>, IStatement> v);
        void remove(String k);
        boolean contains(String k);
        Pair<List<String>, IStatement> get(String k);
        String toString();
        void update(String k,Pair<List<String>, IStatement> v);
        void setContent(HashMap<String,Pair<List<String>, IStatement>> map);
        Map<String, Pair<List<String>, IStatement>> getContent();
    }

