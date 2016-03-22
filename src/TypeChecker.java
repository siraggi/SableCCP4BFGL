/**
 * Created by august on 22/03/16.
 */
import grammar.ini.analysis.*;
import grammar.ini.node.*;

import java.util.Hashtable;
import java.util.Stack;

public class TypeChecker extends DepthFirstAdapter{

    // Stack of symbol tables with name as key and type as value
    Stack<Hashtable<String, String>> symStack;


    public TypeChecker(){
        symStack = new Stack<>();


    }

    //Scope methods
    private void openScope(){
        System.out.println("Open scope");
        symStack.push(new Hashtable<String, String>());
    }

    private void closeScope(){
        System.out.println("Close scope");
        symStack.pop();
    }

    //Visitor methods

    //Prog in/out
    public void inAProg(AProg node){
        System.out.println("Root in");
        openScope();
    }

    public void outAProg(AProg node){
        System.out.println("Root out");
        closeScope();
    }

    //Class dcl in/out
    public void inAClassdclPdcl(AClassdclPdcl node){
        openScope();
    }

    public void outAClassdclPdcl(AClassdclPdcl node){
        closeScope();
    }

    public void inAFuncPdcl(AFuncPdcl node){
        openScope();
    }

    public void outAFuncPdcl(AFuncPdcl node){
        closeScope();
    }

    public void inAForupStmt(AForupStmt node){
        openScope();
    }

    public void outAForupStmt(AForupStmt node){
        closeScope();
    }

    public void inAFordownStmt(AFordownStmt node){
        openScope();
    }

    public void outAFordownStmt(AFordownStmt node){
        closeScope();
    }

    public void inAWhileStmt(AWhileStmt node){
        openScope();
    }

    public void outAWhileStmt(AWhileStmt node){
        closeScope();
    }


}
