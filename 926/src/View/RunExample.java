package View;

import Controller.InterpreterController;

public class RunExample extends Command {
    private InterpreterController ctr;

    public RunExample(String key, String desc, InterpreterController ctr) {
        super(key, desc);
        this.ctr = ctr;
    }

    @Override
    public void execute() {
        try {
            ctr.allStep(0);
        } catch (Exception e) { System.out.println(e.toString());} //here you must treat the exceptions that can not be solved in the controller
    }
}