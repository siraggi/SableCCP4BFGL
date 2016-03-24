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
    public Stack<Hashtable<String, Node>> symStack;
    public Hashtable<Node, String> typeTable;
    public ArrayList<String> ErrorList;
    private TypeChecker typeChecker;



    public TypeChecker(){
        symStack = new Stack<>();
        typeTable = new Hashtable<>();
        ErrorList = new ArrayList<>();
    }

    //Scope methods
    private void openScope(Node node){
        TableFiller tf = new TableFiller();

        node.apply(tf);

        System.out.println("Open scope");

        //symStack.push(new Hashtable<String, Node>());
        symStack.push(tf.symbolTable);
        typeTable.putAll(tf.typeTable);
        ErrorList.addAll(tf.ErrorList);
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
        AFuncPdcl tempNode;

        for (int i = symStack.size(); i > 0; i--){
            if(symStack.get(i-1).containsKey(id)){
                type = typeTable.get(symStack.get(i-1).get(id));

                if(type == null){
                    typeChecker = new TypeChecker();

                    typeChecker.typeTable.putAll(typeTable);
                    typeChecker.symStack = symStack;

                    getNode(id).apply(typeChecker);

                    typeTable.putAll(typeChecker.typeTable);
                    ErrorList.addAll(typeChecker.ErrorList);

                    if(symStack.get(i-1).get(id).getClass().equals(AFuncPdcl.class)){
                        tempNode = (AFuncPdcl)symStack.get(i-1).get(id);
                        typeTable.put(tempNode, typeTable.get(tempNode.getBody()));
                    }

                    type = typeTable.get(symStack.get(i-1).get(id));
                }
            }
        }

        if(type == "")
            ErrorList.add("ERROR: " + id + " is not in scope");

        return type;
    }

    private Node getNode(String id){

        for (int i = symStack.size(); i > 0; i--){
            if(symStack.get(i-1).containsKey(id))
                return symStack.get(i-1).get(id);
        }

        ErrorList.add("ERROR: No decleration for " + id);

        return null;
    }

    //Visitor methods

    //Prog in/out
    public void inAProg(AProg node){
        openScope(node);
    }

    public void outAProg(AProg node){
        closeScope();
        for (String s: ErrorList)
            System.out.println(s);
    }

    //Main dcl
    public void inAMainPdcl(AMainPdcl node){
        openScope(node);
    }

    public void outAMainPdcl(AMainPdcl node){
        closeScope();
    }

    //Class dcl in/out
    public void inAClassPdcl(AClassPdcl node){
        openScope(node);

    }

    public void outAClassPdcl(AClassPdcl node){
        closeScope();
        //addSymbol(node.getId().getText(), node, node.getId().getText());
    }

    //Function dcl
    public void inAFuncPdcl(AFuncPdcl node){
        openScope(node);
    }

    public void outAFuncPdcl(AFuncPdcl node){
        closeScope();
        //addSymbol(node.getId().getText(), node, typeTable.get(node.getBody()));
    }

    //For loop up
    public void inAForupStmt(AForupStmt node){
        openScope(node);
    }

    public void outAForupStmt(AForupStmt node){
        closeScope();
    }

    //For loop down
    public void inAFordownStmt(AFordownStmt node){
        openScope(node);
    }

    public void outAFordownStmt(AFordownStmt node){
        closeScope();
    }

    //While loop
    public void inAWhileStmt(AWhileStmt node){
        openScope(node);
    }

    public void outAWhileStmt(AWhileStmt node){
        closeScope();
    }

    //If
    public void inAIfConditional(AIfConditional node){
        openScope(node);
    }

    public void outAIfConditional(AIfConditional node){
        closeScope();
    }

    //else
    public void inAElseBranch(AElseBranch node){
        openScope(node);
    }

    public void outAElseBranch(AElseBranch node){
        closeScope();
    }

    //else if
    public void inAElseifBranch(AElseifBranch node){
        openScope(node);
    }

    public void outAElseifBranch(AElseifBranch node){
        closeScope();
    }

    //Event dcl
    public void inAEventPdcl(AEventPdcl node){
        openScope(node);
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
        if(getType(node.getId().getText()) != "")
            typeTable.put(node, node.getId().getText());
    }

    public void outAValExpr(AValExpr node){
        if(typeTable.get(node.getVal()) != null)
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

    //Expr
    public void outANotExpr(ANotExpr node){
       if(typeTable.get(node) == "bool")
           typeTable.put(node, "bool");
        else
           ErrorList.add("ERROR: " + node.getExpr().toString() + ", is not of type BOOL.");
    }

    public void outAUnaryExpr(AUnaryExpr node){
        if(typeTable.get(node) == "num")
            typeTable.put(node, "num");
        else
            ErrorList.add("ERROR: " + node.getExpr().toString() + ", is not of type NUM.");
    }


}
