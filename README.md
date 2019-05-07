# InterpreterJava
Toy language interpreter
A GUI application (or console based) that simulates the creation and execution of a toy programming language using java and javafx.

The main functionality of the application is the creation and execution of ProgramStates.

Features of toy language:
-Expressions- Variables -Constant Integers
                        -Stored in a SymbolTable as a map of String->Integer
                        -Stored in a Heap-like structure and referenced in the SymbolTable
                        -Boolean 0 or 1
                        -Arithmetic expression that takes two expressions and an operator and returns the result of operation (exp1+exp2)
                        
-Statements- Functions  -If,While,Sleep statements
                        -Compound statements of two statements that will be executed in order
                        -Assignment stms giving a value to a variable
                        -Heap accessing
                        -File accessing 
                        -Print 
                        -Fork 
                        -Lock 
                        -Procedure calling
                  
 -ProgramStates- Keeps current ExecutionStack (made of statements), variable names and values stored in heap and SymbolTable,
                 a table for files, a map of procedures.Each ProgramState has an ID that is used in threading.
                 
 To run the application run the main function in the GUIMain class.Select a program state and click OneStepForAll to execute 
 one step for all the threads.You may also select rows of the table Prg Id to observe different threads' execution schedule. 
                 
 
 
                        
