package Model.Expression;

import Model.Utils.Heap;
import Model.Utils.IHeap;
import Model.Utils.ISymTable;
import Model.Utils.MyDictionary;

public class ConstExp implements IExpression {
    private int value;
    public ConstExp(int val){ value = val;}
    public int evaluate(MyDictionary<String, Integer> st, IHeap heap) {
        return this.value;
    }

    @Override
    public String toString(){
        String s= String.valueOf(value);
        return s;
    }
}
