package Model.Expression;

import Model.Expression.IExpression;
import Model.Utils.Heap;
import Model.Utils.IHeap;
import Model.Utils.MyDictionary;

public class HReadExp implements IExpression {
    String varName;

    public HReadExp(String varName) {
        this.varName = varName;
    }

    @Override
    public int evaluate(MyDictionary<String, Integer> st, IHeap heap) throws Exception {
        if(st.contains(varName)) {
            int i = st.get(varName);
            if (heap.contains(i))
                return heap.get(i);
            else throw new Exception ("Not in heap");
        }
        else throw new Exception ("Not in symtable");
    }
    public String toString(){
        String s= " Read "+varName+" ";
        return s;
    }
}
