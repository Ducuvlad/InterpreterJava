package Model.Expression;

import Model.Utils.IHeap;
import Model.Utils.MyDictionary;

public class BooleanExp implements IExpression {
    private IExpression op1, op2;
    private String operator;

    public BooleanExp(IExpression op1, IExpression op2, String operator) {
        this.op1 = op1;
        this.op2 = op2;
        this.operator = operator;
    }

    public int evaluate(MyDictionary<String, Integer> st, IHeap heap) throws Exception {

        switch (this.operator) {
            case "<":
                if(op1.evaluate(st,heap)<op2.evaluate(st,heap))
                    return 1;
                else
                    return 0;

            case "<=":
                if(op1.evaluate(st,heap)<=op2.evaluate(st,heap))
                    return 1;
                else
                    return 0;
            //break;

            case "==":
                if(op1.evaluate(st,heap)==op2.evaluate(st,heap))
                    return 1;
                else
                    return 0;
            //break;

            case "!=":
                if(op1.evaluate(st,heap)!=op2.evaluate(st,heap))
                    return 1;
                else
                    return 0;
            case ">":
                if(op1.evaluate(st,heap)>op2.evaluate(st,heap))
                    return 1;
                else
                    return 0;
            case ">=":
                if(op1.evaluate(st,heap)>=op2.evaluate(st,heap))
                    return 1;
                else
                    return 0;
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
