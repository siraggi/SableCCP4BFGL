/**
 * Created by august on 22/03/16.
 */
import grammar.ini.analysis.*;
import grammar.ini.node.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

public class TypeChecker extends DepthFirstAdapter{

    // Stack of symbol tables with name as key and type as value
    Stack<Hashtable<String, Node>> symStack;
    Hashtable<Node, String> typeTable;
    ArrayList<String> ErrorList = new ArrayList<String>();



    public TypeChecker(){
        symStack = new Stack<>();
        typeTable = new Hashtable<>();

    }

    //Scope methods
    private void openScope(){
        System.out.println("Open scope");
        symStack.push(new Hashtable<String, Node>());
    }

    private void closeScope(){
        System.out.println("Close scope");
        System.out.println("Stack size: " + symStack.size());
        Hashtable<String, Node> temp = symStack.pop();

        System.out.println("Hash Size: " + temp.size());

        //for(int i = 0; i < temp.size(); i++){
        //    System.out.println(temp.elements().nextElement());
        //}
    }



    //Symbol table methods
    private void addSymbol(String name, Node node, String type){
        if (!symStack.peek().containsKey(name)) {
            symStack.peek().put(name, node);
            typeTable.put(node, type);
        }
        else
            ErrorList.add("ERROR: " + name + " is already defined in this scope " + symStack.peek().size());
    }

    private String getType(String id){
        String type = "";

        for (int i = symStack.size(); i > 0; i--){
            if(symStack.get(i-1).containsKey(id))
                type = typeTable.get(symStack.get(i-1).get(id));
        }

        if(type == "")
            ErrorList.add("ERROR: " + id + " is not in scope");

        return type;
    }



    //Visitor methods

    //Prog in/out
    public void inAProg(AProg node){
        openScope();
    }

    public void outAProg(AProg node){
        closeScope();
        for (String s: ErrorList)
            System.out.println(s);
    }

    //Main dcl
    public void inAMainPdcl(AMainPdcl node){
        openScope();
    }

    public void outAMainPdcl(AMainPdcl node){
        closeScope();
    }

    //Class dcl in/out
    public void inAClassPdcl(AClassPdcl node){
        openScope();

    }

    public void outAClassPdcl(AClassPdcl node){
        closeScope();
    }

    //Function dcl
    public void inAFuncPdcl(AFuncPdcl node){
        openScope();
    }

    public void outAFuncPdcl(AFuncPdcl node){
        typeTable.put(node, typeTable.get(node.getBody()));
        closeScope();
    }

    //For loop up
    public void inAForupStmt(AForupStmt node){
        openScope();
    }

    public void outAForupStmt(AForupStmt node){
        closeScope();
    }

    //For loop down
    public void inAFordownStmt(AFordownStmt node){
        openScope();
    }

    public void outAFordownStmt(AFordownStmt node){
        closeScope();
    }

    //While loop
    public void inAWhileStmt(AWhileStmt node){
        openScope();
    }

    public void outAWhileStmt(AWhileStmt node){
        closeScope();
    }

    //If
    public void inAIfConditional(AIfConditional node){
        openScope();
    }

    public void outAIfConditional(AIfConditional node){
        closeScope();
    }

    //else
    public void inAElseBranch(AElseBranch node){
        openScope();
    }

    public void outAElseBranch(AElseBranch node){
        closeScope();
    }

    //else if
    public void inAElseifBranch(AElseifBranch node){
        openScope();
    }

    public void outAElseifBranch(AElseifBranch node){
        closeScope();
    }

    //Event dcl
    public void inAEventPdcl(AEventPdcl node){
        openScope();
    }

    public void outAEventPdcl(AEventPdcl node){
        closeScope();
    }

    //Var dcl
    public void outAVarPdcl(AVarPdcl node){
        addSymbol(node.getId().getText(), node, node.getType().toString());

    }

    //Var asg dcl
    public void outAVarasgPdcl(AVarasgPdcl node){
        addSymbol(node.getId().getText(), node, node.getType().toString());
    }

    //List dcl
    public void outAListPdcl(AListPdcl node){
        addSymbol(node.getId().getText(), node, node.getType().toString());
    }

    //Value types
    public void outANumVal(ANumVal node){
        typeTable.put(node, "num");
    }

    public void outATextVal(ATextVal node){
        typeTable.put(node, "text");
    }

    public void outABoolVal(ABoolVal node){
        typeTable.put(node, "bool");
    }

    public void outAConstrVal(AConstrVal node){
        typeTable.put(node, node.getId().getText());
    }

    public void outAValExpr(AValExpr node){
        typeTable.put(node, typeTable.get(node.getVal()));
    }

    public void outAIdExpr(AIdExpr node){
        typeTable.put(node, getType(node.getId().getText()));
    }

    public void outAFuncBody(AFuncBody node){
        typeTable.put(node, typeTable.get(node.getReturn()));
    }

    public void outAIdReturn(AIdReturn node){
        typeTable.put(node, typeTable.get(node.getExpr()));
    }

    public void outAEmptyReturn(AEmptyReturn node){
        typeTable.put(node, "void");
    }

    public void outAVarCall(AVarCall node){
        typeTable.put(node, getType(node.getId().getText()));
    }

    public void outAValCall(AValCall node){
        typeTable.put(node, typeTable.get(node.getVal()));
    }

    public void outAClassCall(AClassCall node){
        typeTable.put(node, typeTable.get(node.getRest().getLast()));
    }

    public void outAFuncCall(AFuncCall node){
        typeTable.put(node, getType(node.getId().getText()));
    }

    public void outACallExpr(ACallExpr node){
        typeTable.put(node, typeTable.get(node.getCall()));
    }
}
