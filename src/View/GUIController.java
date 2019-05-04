package View;
import Controller.InterpreterController;
import Model.Expression.ArithExp;
import Model.Expression.ConstExp;
import Model.Expression.HReadExp;
import Model.Expression.VarExp;
import Model.ProgramState;
import Model.Statement.*;
import Model.Utils.*;
import Repository.IRepo;
import Repository.Repo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class GUIController {
    InterpreterController ctr1,ctr2,ctr3,ctr4,currentController;
    Integer selectID=0;
    @FXML
    ListView<String> listaprg,fxoutput,fxprgid,fxexestack;

    @FXML
    private TableView<TwoInt2furious> heapTable;

    @FXML
    private TableColumn<TwoInt2furious,Integer> addCol;

    @FXML
    private TableColumn<TwoInt2furious,Integer> heapValCol;

    @FXML
    private TableView<IntStringPair> fileTable;

    @FXML
    private TableColumn<IntStringPair,Integer> identCol;

    @FXML
    private TableColumn<IntStringPair,String> fNameCol;

    @FXML
    private TableView<TwoStrings> ProcTable;

    @FXML
    private TableColumn<TwoStrings,String>FxProcName;
    @FXML
    private TableColumn<TwoStrings,String>fxVarsAndBody;

    @FXML
    private TableView<IntStringPair> symTable;

    @FXML
    private TableColumn<IntStringPair,Integer> symValCol;
    @FXML
    private TableColumn<IntStringPair,String> vNameCol;



    @FXML
    public void initialize(){
        addCol.setCellValueFactory(new PropertyValueFactory<>("int1"));
        heapValCol.setCellValueFactory(new PropertyValueFactory<>("int2"));
        identCol.setCellValueFactory(new PropertyValueFactory<>("in"));
        fNameCol.setCellValueFactory(new PropertyValueFactory<>("st"));
        vNameCol.setCellValueFactory(new PropertyValueFactory<>("st"));
        symValCol.setCellValueFactory(new PropertyValueFactory<>("in"));
        FxProcName.setCellValueFactory(new PropertyValueFactory<>("int1"));
        fxVarsAndBody.setCellValueFactory(new PropertyValueFactory<>("int2"));
        MyStack<IStatement> exeStack = new MyStack<>();
        MyList<Integer> output = new MyList<>();
        MyList<Integer> output2 = new MyList<>();
        MyList<Integer> output3 = new MyList<>();
        MyList<Integer> output4 = new MyList<>();

        MyDictionary<Integer, Pair<String, BufferedReader>> ftable=new MyDictionary<Integer,Pair<String, BufferedReader>>();
        IHeap heap=new Heap();
        IGlobalTable GTable=new GlobalTable();
        List<String> lsum=new ArrayList<String>();
        lsum.add("a");
        lsum.add("b");
        IStatement sum=new CompoundStm(new AssignmentStm( "v",new ArithExp(new VarExp("v"),new VarExp("w"),'+')),new PrintStm(new VarExp("v")));
        GTable.put("sum",new Pair(lsum,sum));

        List<String> lprod=new ArrayList<String>();
        lprod.add("a");
        lprod.add("b");
        IStatement prod=new CompoundStm(new AssignmentStm( "v",new ArithExp(new VarExp("v"),new VarExp("w"),'*')),new PrintStm(new VarExp("v")));
        GTable.put("prod",new Pair(lprod,prod));
        IStatement a= new AssignmentStm("a",new ArithExp(new ConstExp(2),new ArithExp(new ConstExp(3),new ConstExp(5),'*'),'+'));
        IStatement b= new CompoundStm(new AssignmentStm("b",new ArithExp(new VarExp("a"),new ConstExp(1),'+')),new PrintStm(new VarExp("b")));
        IStatement ex1= new CompoundStm(a,b);
        MyStack<MyDictionary<String, Integer>> newSymStack= new MyStack<MyDictionary<String, Integer>>();
        MyDictionary<String, Integer> symTable = new MyDictionary<>();
        newSymStack.push(symTable);
        ProgramState prg1 = new ProgramState(exeStack,output,ex1,newSymStack,GTable);
        IRepo repo1 = new Repo("log1.txt");
        prg1.getExeStack().push(ex1);
        repo1.add(prg1);
        ctr1 = new InterpreterController(repo1);

        MyStack<IStatement> exeStack2 = new MyStack<>();
        MyStack<MyDictionary<String, Integer>> newSymStack2= new MyStack<MyDictionary<String, Integer>>();
        MyDictionary<String, Integer> symTable2 = new MyDictionary<>();
        newSymStack2.push(symTable2);
        IStatement f1=new CompoundStm(new AssignmentStm("v",new ConstExp(10)),new newStm("a",new ConstExp(22)));
        IStatement f2=new forkStm(new CompoundStm(new HWriteExp("a",new ConstExp(30)),new CompoundStm(new AssignmentStm("v",new ConstExp(32)),new CompoundStm(new PrintStm(new VarExp("v")),new PrintStm(new HReadExp("a"))))));
        IStatement f3=new CompoundStm(new PrintStm(new VarExp("v")),new PrintStm(new HReadExp("a")));
        IStatement ff=new CompoundStm(f1,new CompoundStm(f2,f3));
        //IStatement ex2 =  new CompoundStm(new AssignmentStm("x",new ConstExp(11)),new PrintStm(new VarExp("x")));
        ProgramState prg2 = new ProgramState(exeStack2,output2,ff,newSymStack2,GTable);
        IRepo repo2 = new Repo("log2.txt");
        prg2.getExeStack().push(ff);
        repo2.add(prg2);

        ctr2 = new InterpreterController(repo2);

        List<String> lpr=new ArrayList<String>();
        lpr.add("v");
        lpr.add("w");
        IStatement p1=new CompoundStm(new AssignmentStm("v",new ConstExp(2)),new AssignmentStm("w",new ConstExp(5)));
        IStatement p2=new CompoundStm(new callProc("sum",lpr),new PrintStm(new VarExp("v")));
        IStatement p3=new forkStm(new CompoundStm(new callProc("prod",lpr),new forkStm(new callProc("sum",lpr))));
        IStatement pf=new CompoundStm(p1,new CompoundStm(p2,p3));
        MyStack<IStatement> exeStack3 = new MyStack<>();
        MyStack<MyDictionary<String, Integer>> newSymStack3= new MyStack<MyDictionary<String, Integer>>();
        MyDictionary<String, Integer> symTable3 = new MyDictionary<>();
        newSymStack3.push(symTable3);
        IStatement ex3 = new CompoundStm(new newStm("v",new ConstExp(20)),new CompoundStm(new newStm("a",new ConstExp(22)),new CompoundStm(new HWriteExp("a",new ConstExp(30)),new PrintStm(new ConstExp(30)))));
        ProgramState prg3 = new ProgramState(exeStack3,output3,pf,newSymStack3,GTable);
        IRepo repo3 = new Repo("log3.txt");
        prg3.getExeStack().push(pf);
        repo3.add(prg3);
         ctr3 = new InterpreterController(repo3);

        IStatement cs7= new CompoundStm(
                new newStm("v1",new ConstExp(20)), new CompoundStm(
                new newLock("x"), new CompoundStm(
                new LockStm("x"),new CompoundStm(
                new forkStm(new CompoundStm(new LockStm("x"), new CompoundStm(
                        new HWriteExp("v1",new ConstExp(19)), new CompoundStm(
                        new PrintStm(new HReadExp("v1")),new UnlockStm("x")
                )
                )
                )
                ),
                new CompoundStm(new PrintStm(new HReadExp("v1")), new UnlockStm("x"))))
        )
        );

        IStatement forksleep=new forkStm(new CompoundStm(new CompoundStm(new AssignmentStm("v",new ArithExp(new VarExp("v"),new ConstExp(1),'-')),new AssignmentStm("v",new ArithExp(new VarExp("v"),new ConstExp(1),'-'))),new PrintStm(new VarExp("v"))));
        IStatement exSleep= new CompoundStm( new CompoundStm(new CompoundStm(new AssignmentStm("v",new ConstExp(10)),forksleep),new SleepStm(10)),new PrintStm(new ArithExp(new VarExp("v"),new ConstExp(10),'*')));


        MyStack<IStatement> exeStack4 = new MyStack<>();
        MyStack<MyDictionary<String, Integer>> newSymStack4= new MyStack<MyDictionary<String, Integer>>();
        MyDictionary<String, Integer> symTable4 = new MyDictionary<>();
        newSymStack4.push(symTable4);
        IStatement ex4 =   new CompoundStm( new CompoundStm(new CompoundStm(new openRFileStm(0, "C:\\Users\\ducuv\\source\\repos\\MAP_A7\\MAP_A7\\View\\file1.txt"),new readFileStm(new ConstExp(0),"c")),new PrintStm(new VarExp("c"))),new closeRFile(new ConstExp(0)));
        ProgramState prg4 = new ProgramState(exeStack4,output4,exSleep,newSymStack4,GTable);
        IRepo repo4 = new Repo("log4.txt");
        prg4.getExeStack().push(exSleep);
        repo4.add(prg4);
        ctr4 = new InterpreterController(repo4);



        ObservableList<String> items= FXCollections.observableArrayList(ex1.toString(),ff.toString(),pf.toString(),exSleep.toString());
        listaprg.setItems(items);



    }
    @FXML
    private void clickSelect(){
        int i=listaprg.getSelectionModel().getSelectedIndex();
        switch (i) {
            case 0:
                currentController=ctr1;
                return ;
            case 1:
                currentController=ctr2;
                return ;
            case 2:
                currentController=ctr3;
                return ;
            case 3:
                currentController=ctr4;
                return ;
                default:System.out.println("WRONG NUMBER");
        }
    }
    @FXML
    private void clickOneStepForAll() throws Exception {
        if(currentController.getRepo().getPrgs().size()==0)
            return;
        List<ProgramState> prglist=currentController.getRepo().getPrgs();

        currentController.oneStepForAllPrg(prglist);
        //output
        List<String> outputs=prglist.stream().map(prg->prg.getOutput().toString()).collect(Collectors.toList());
        ObservableList<String> items= FXCollections.observableList(outputs);
        fxoutput.setItems(items);
        //exestack
        String exestacks=prglist.get(selectID).getExeStack().toString();
        ObservableList<String> items2= FXCollections.observableArrayList(exestacks);
        fxexestack.setItems(items2);
        //id
        List<String> ids=prglist.stream().map(prg->String.valueOf(prg.getID())).collect(Collectors.toList());
        ObservableList<String> items3= FXCollections.observableArrayList(ids);
        fxprgid.setItems(items3);

        //symtables
        Set<Map.Entry<String, Integer>> st = new HashMap<>(prglist.get(selectID).getSymTable().getContent()).entrySet();
        ArrayList<IntStringPair> arlis = new ArrayList<>();
        for (Map.Entry<String, Integer> ent : st) {
            arlis.add(new IntStringPair(ent.getKey(), ent.getValue()));
        }
        ObservableList<IntStringPair> itemis = FXCollections.observableArrayList(
                arlis);
        symTable.setItems(itemis);
        //heap
        Set<Map.Entry<Integer, Integer>> heap = new HashMap<>(prglist.get(selectID).getHeap().getContent()).entrySet();
        ArrayList<TwoInt2furious> hlis = new ArrayList<>();
        for (Map.Entry<Integer, Integer> ent : heap) {
            hlis.add(new TwoInt2furious(ent.getKey(), ent.getValue()));
        }
        ObservableList<TwoInt2furious> itemh = FXCollections.observableArrayList(hlis);
        heapTable.setItems(itemh);

        //ftable
        Set<Map.Entry<Integer, Pair<String, BufferedReader>>> ft = new HashMap<>(prglist.get(selectID).getFtable().getContent()).entrySet();

        ArrayList<IntStringPair> flis = new ArrayList<>();
        for (Map.Entry<Integer, Pair<String, BufferedReader>> ent : ft) {
            flis.add(new IntStringPair(ent.getKey(), ent.getValue().getFirst()));
        }
        ObservableList<IntStringPair> itemf = FXCollections.observableArrayList(
                flis);
        fileTable.setItems(itemf);

        //GlobalTable
        Set<Map.Entry<String, Pair<List<String>, IStatement>>> gt = new HashMap<>(prglist.get(selectID).getGlobalT().getContent()).entrySet();

        ArrayList<TwoStrings> glis = new ArrayList<>();
        for (Map.Entry<String, Pair<List<String>, IStatement>> ent : gt) {
            glis.add(new TwoStrings(ent.getKey(), ent.getValue().getFirst().stream().collect(Collectors.joining())+" "+ent.getValue().getSecond().toString()));
        }
        ObservableList<TwoStrings> itemg = FXCollections.observableArrayList(
                glis);
        ProcTable.setItems(itemg);

        //select current id
        if(fxprgid.getSelectionModel().getSelectedIndex()==-1)
            selectID=0;
        else
            selectID=fxprgid.getSelectionModel().getSelectedIndex();

    }
    @FXML
    private void onClickExeStack(){
        if(currentController.getRepo().getPrgs().size()==0)
            return;
        List<ProgramState> prglist=currentController.getRepo().getPrgs();
        String exestacks=prglist.get(selectID).getExeStack().toString();
        ObservableList<String> items2= FXCollections.observableArrayList(exestacks);
        fxexestack.setItems(items2);
        if(fxprgid.getSelectionModel().getSelectedIndex()==-1)
            selectID=0;
        else
            selectID=fxprgid.getSelectionModel().getSelectedIndex();
    }
}
