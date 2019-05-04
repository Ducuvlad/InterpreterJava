package Model.Utils;

import Model.Statement.IStatement;

public interface IExeStack {
    void push(IStatement stmt);
    boolean isEmpty();
    void pop();
}
