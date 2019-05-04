package Model.Statement;

import Model.ProgramState;

public class ReturnStm implements IStatement {

    @Override
    public ProgramState execute(ProgramState ps) throws Exception {
        ps.getFullStack().pop();
        return null;
    }
    @Override
    public String toString(){
        String s= "Return";
        return s;
    }
}
