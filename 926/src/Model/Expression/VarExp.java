package Model.Expression;

import Model.Utils.Heap;
import Model.Utils.IHeap;
import Model.Utils.ISymTable;
import Model.Utils.MyDictionary;

public class VarExp implements IExpression {
    private String name;

    public VarExp(String name) {
        this.name = name;
    }

    public int evaluate(MyDictionary<String, Integer> st, IHeap heap) {
        if (st.contains(this.name)) {
            return st.get(this.name);
        } else {
            throw new RuntimeException("Inexistent variable");
        }
    }
    @Override
    public String toString(){
        return name;
    }
}
