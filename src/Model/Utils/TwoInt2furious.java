package Model.Utils;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TwoInt2furious {
    private SimpleIntegerProperty int1;
    private SimpleIntegerProperty int2;

    public TwoInt2furious(Integer in,Integer st){
        this.int1=new SimpleIntegerProperty(in);
        this.int2=new SimpleIntegerProperty(st);
    }



    public Integer getInt1() {
        return int1.get();
    }

    public void setInt1(Integer i) {
        this.int1.set(i);
    }

    public int getInt2() {
        return int2.get();
    }

    public SimpleIntegerProperty int2Property() {
        return int2;
    }

    public void setInt2(int int2) {
        this.int2.set(int2);
    }
}
