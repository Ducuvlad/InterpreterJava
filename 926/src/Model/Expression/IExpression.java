package Model.Expression;

import Model.Utils.Heap;
import Model.Utils.IHeap;
import Model.Utils.ISymTable;
import Model.Utils.MyDictionary;

public interface IExpression {
    int evaluate(MyDictionary<String, Integer> st, IHeap heap) throws Exception;
    String toString();
}
