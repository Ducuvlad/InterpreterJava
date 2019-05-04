package Model.Utils;

import javafx.beans.property.SimpleStringProperty;

public class TwoStrings {
    private SimpleStringProperty int1;
    private SimpleStringProperty int2;

    public TwoStrings(String in,String  st){
        this.int1=new SimpleStringProperty(in);
        this.int2=new SimpleStringProperty(st);
    }



    public String  getInt1() {
        return int1.get();
    }

    public void setInt1(String  i) {
        this.int1.set(i);
    }

    public String  getInt2() {
        return int2.get();
    }

    public SimpleStringProperty int2Property() {
        return int2;
    }

    public void setInt2(String  int2) {
        this.int2.set(int2);
    }
}

