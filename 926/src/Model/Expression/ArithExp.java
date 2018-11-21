package Model.Expression;

import Model.Utils.Heap;
import Model.Utils.IHeap;
import Model.Utils.ISymTable;
import Model.Utils.MyDictionary;

public class ArithExp implements IExpression {
    private IExpression op1, op2;
    private char operator;

    public ArithExp(IExpression op1, IExpression op2, char operator) {
        this.op1 = op1;
        this.op2 = op2;
        this.operator = operator;
    }

    public int evaluate(MyDictionary<String, Integer> st, IHeap heap) throws Exception {
        int firstRes = this.op1.evaluate(st,heap);
        int secondRes = this.op2.evaluate(st,heap);

        switch (this.operator) {
            case '+':
                return firstRes + secondRes;
                //break;

            case '-':
                return firstRes - secondRes;
                //break;

            case '*':
                return firstRes * secondRes;
                //break;

            case '/':
                if (secondRes == 0) {
                    throw new Exception("Division by 0!!");
                }

                return firstRes / secondRes;
                //break;

            default:
                throw new RuntimeException("invalid");
        }
    }
    @Override
    public String toString(){
        String s=op1.toString()+" "+operator+" "+op2.toString();
        return s;
    }
}
