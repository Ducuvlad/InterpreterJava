package Model.Statement;

import Model.ProgramState;
import Model.Utils.MyDictionary;
import Model.Utils.Pair;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class openRFileStm implements IStatement {

    Integer var_file;
    String filename;

    public openRFileStm(Integer var_file, String filename) {
        this.var_file = var_file;
        this.filename = filename;
    }

    @Override
    public ProgramState execute(ProgramState ps) throws Exception {
         MyDictionary<Integer,Pair<String, BufferedReader>> ftable= ps.getFtable();
         if( ftable.contains(var_file)) throw new Exception("File exists");
         try {
             BufferedReader bf = new BufferedReader(new FileReader(filename));

                 Pair<String,BufferedReader> pair=new Pair<String,BufferedReader> (filename,bf);
                 ftable.put(var_file,pair);
         }catch (FileNotFoundException e) {throw new Exception("File does not exists");}
        return null;
    }
    @Override
    public String toString(){
        String s= "\n open id:"+var_file+" named:" +filename;
        return s;
    }
}
