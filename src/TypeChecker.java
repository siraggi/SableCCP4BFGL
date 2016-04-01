/**
 * Created by august on 22/03/16.
 */
import grammar.ini.analysis.*;
import grammar.ini.node.*;

import java.util.*;

public class TypeChecker extends DepthFirstAdapter{
    private static final String BOOL = "bool";
    private static final String NUM = "num";
    private static final String TEXT = "text";
    //private static final String VECTOR = "vector";
    private static final String ERRORTYPE = "9";

    // Stack of symbol tables with name as key and type as value
    public Stack<Hashtable<String, Node>> symStack;
    public Hashtable<Node, String> typeTable;
    public ArrayList<String> ErrorList;




    public TypeChecker(){
        symStack = new Stack<>();
        typeTable = new Hashtable<>();
        ErrorList = new ArrayList<>();
    }

    //Scope methods
    private void openScope(Node node){
        TableFiller tf = new TableFiller(node);

        node.apply(tf);

        System.out.println("Open scope");

        //symStack.push(new Hashtable<String, Node>());
        symStack.push(tf.symStack.pop());
        typeTable.putAll(tf.typeTable);
        ErrorList.addAll(tf.ErrorList);
    }

    private void closeScope(){
        System.out.println("Close scope");
        System.out.println("Stack size: " + symStack.size());
        symStack.pop();
    }



    //Symbol table methods
    private void addSymbol(String name, Node node, String type){
        if (!symStack.peek().containsKey(name)) {
            symStack.peek().put(name, node);
            addType(node, type);
        }
        else
            ErrorList.add("ERROR: " + name + " is already defined in this scope " + symStack.peek().size());
    }
    
    private void addType(Node node, String type){
        typeTable.put(node, type.trim());
    }

    private String getType(String id){
        TypeChecker typeChecker;
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
                        addType(tempNode, typeTable.get(tempNode.getBody()));
                    }

                    type = typeTable.get(symStack.get(i-1).get(id));
                }

                break;
            }
        }

        if(type.equals(""))
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
        Set<String> temp = new HashSet<>();

        closeScope();
        temp.addAll(ErrorList);
        ErrorList.clear();
        ErrorList.addAll(temp);
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

        for (Node n : node.getParams()){
            addSymbol(((AFormalParam) n).getId().getText(), n, ((AFormalParam) n).getType().toString());
        }
    }

    public void outAFuncPdcl(AFuncPdcl node){
        closeScope();
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

        if(!typeTable.get(node.getExpr()).equals(BOOL)){
            ErrorList.add("ERROR: " + node.getExpr().toString() + ", is not of type " + BOOL + ".");
        }
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

        if(!typeTable.get(node.getExpr()).equals(BOOL)){
            ErrorList.add("ERROR: " + node.getExpr().toString() + ", is not of type " + BOOL + ".");
        }
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
        //addSymbol(node.getId().getText(), node, node.getType().toString());

    }

    //Var asg dcl
    public void outAVarasgPdcl(AVarasgPdcl node){
        if(!typeTable.get(node.getExpr()).trim().equals(node.getType().toString().trim())){
            ErrorList.add("ERROR: " + node.getExpr().toString() + ", is not of type " + node.getType() + ".");
            addType(node, ERRORTYPE);
        }
    }

    //List dcl
    public void outAListPdcl(AListPdcl node){
        //addSymbol(node.getId().getText(), node, node.getType().toString());
    }

    //Value types
    public void outANumVal(ANumVal node){
        addType(node, NUM);
    }

    public void outATextVal(ATextVal node){
        addType(node, TEXT);
    }

    public void outABoolVal(ABoolVal node){
        addType(node, BOOL);
    }

    public void outAConstrVal(AConstrVal node){

        addType(node, node.getId().getText());
    }

    public void outAValExpr(AValExpr node){
        if(typeTable.get(node.getVal()) != null)
            addType(node, typeTable.get(node.getVal()));
    }

    public void outAIdExpr(AIdExpr node){
        addType(node, getType(node.getId().getText()));
    }

    public void outAFuncBody(AFuncBody node){
        addType(node, typeTable.get(node.getReturn()));
    }

    public void outAIdReturn(AIdReturn node){
        addType(node, typeTable.get(node.getExpr()));
    }

    public void outAEmptyReturn(AEmptyReturn node){
        addType(node, "void");
    }

    public void outAVarCall(AVarCall node){
        addType(node, getType(node.getId().getText()));
    }

    public void outAValCall(AValCall node){
        addType(node, typeTable.get(node.getVal()));
    }

    public void inAClassCall(AClassCall node){
        openScope(getNode(getType(((AVarCall)node.getFirst()).getId().getText())));

        for (int i = 0; i < node.getRest().size() - 1; i++){
            openScope(getNode(getType(((AVarCall)node.getRest().get(i)).getId().getText())));
        }


    }

    public void outAClassCall(AClassCall node){
        Node n = node.getRest().getLast();
        addType(node, getType(n.toString().trim()));
        closeScope();

        for (int i = 0; i < node.getRest().size() - 1; i++){
            closeScope();
        }
    }

    public void outAFuncCall(AFuncCall node){
        addType(node, getType(node.getId().getText()));
    }

    public void outACallExpr(ACallExpr node){
        addType(node, typeTable.get(node.getCall()));
    }
    
    //Expr
    public void outANotExpr(ANotExpr node){
       if(typeTable.get(node).equals(BOOL))
           addType(node, BOOL);
       else{
           ErrorList.add("ERROR: " + node.getExpr().toString() + ", is not of type " + BOOL + ".");
           addType(node, ERRORTYPE);
       }
    }

    public void outAUnaryExpr(AUnaryExpr node){
        if(typeTable.get(node.getExpr()).equals(NUM))
            addType(node, NUM);
        else{
            ErrorList.add("ERROR: " + node.getExpr().toString() + ", is not of type " + NUM + ".");
            addType(node, ERRORTYPE);
        }

    }

    public void outALessequalsExpr(ALessequalsExpr node){
        if(typeTable.get(node.getLeft()).equals(NUM)){
            if(typeTable.get(node.getRight()).equals(NUM)){
                addType(node, BOOL);
            }else{
                ErrorList.add("ERROR: " + node.getRight().toString() + ", is not of type " + NUM + ".");
                addType(node, ERRORTYPE);
            }
        }
        else{
            ErrorList.add("ERROR: " + node.getLeft().toString() + ", is not of type " + NUM + ".");
            addType(node, ERRORTYPE);
        }
    }

    public void outAGreaterequalsExpr(AGreaterequalsExpr node){
        if(typeTable.get(node.getLeft()).equals(NUM)){
            if(typeTable.get(node.getRight()).equals(NUM)){
                addType(node, BOOL);
            }else{
                ErrorList.add("ERROR: " + node.getRight().toString() + ", is not of type " + NUM + ".");
                addType(node, ERRORTYPE);
            }
        }
        else{
            ErrorList.add("ERROR: " + node.getLeft().toString() + ", is not of type " + NUM + ".");
            addType(node, ERRORTYPE);
        }
    }

    public void outALessExpr(ALessExpr node){
        if(typeTable.get(node.getLeft()).equals(NUM)){
            if(typeTable.get(node.getRight()).equals(NUM)){
                addType(node, BOOL);
            }else{
                ErrorList.add("ERROR: " + node.getRight().toString() + ", is not of type " + NUM + ".");
                addType(node, ERRORTYPE);
            }
        }
        else{
            ErrorList.add("ERROR: " + node.getLeft().toString() + ", is not of type " + NUM + ".");
            addType(node, ERRORTYPE);
        }
    }

    public void outAGreaterExpr(AGreaterExpr node){
        if(typeTable.get(node.getLeft()).trim().equals(NUM)){
            if(typeTable.get(node.getRight()).equals(NUM)){
                addType(node, BOOL);
            }else{
                ErrorList.add("ERROR: " + node.getRight().toString() + ", is not of type " + NUM + ".");
                addType(node, ERRORTYPE);
            }
        }
        else{
            ErrorList.add("ERROR: " + node.getLeft().toString() + ", is not of type " + NUM + ".");
            addType(node, ERRORTYPE);
        }
    }

    public void outANotequalsExpr(ANotequalsExpr node){
        if(typeTable.get(node.getLeft()).equals(NUM)){
            if(typeTable.get(node.getRight()).equals(NUM)){
                addType(node, BOOL);
            }else{
                ErrorList.add("ERROR: " + node.getRight().toString() + ", is not of type " + NUM + ".");
                addType(node, ERRORTYPE);
            }
        }
        else{
            ErrorList.add("ERROR: " + node.getLeft().toString() + ", is not of type " + NUM + ".");
            addType(node, ERRORTYPE);
        }
    }

    public void outAEqualsExpr(AEqualsExpr node){
        if(typeTable.get(node.getLeft()).equals(NUM)){
            if(typeTable.get(node.getRight()).equals(NUM)){
                addType(node, BOOL);
            }else{
                ErrorList.add("ERROR: " + node.getRight().toString() + ", is not of type " + NUM + ".");
                addType(node, ERRORTYPE);
            }
        }
        else{
            ErrorList.add("ERROR: " + node.getLeft().toString() + ", is not of type " + NUM + ".");
            addType(node, ERRORTYPE);
        }
    }

    public void outAAndExpr(AAndExpr node){
        if(typeTable.get(node.getLeft()).equals(BOOL)){
            if(typeTable.get(node.getRight()).equals(BOOL)){
                addType(node, BOOL);
            }
            else{
                ErrorList.add("ERROR: " + node.getRight().toString() + ", is not of type " + BOOL + ".");
                addType(node, ERRORTYPE);
            }
        }
        else{
            ErrorList.add("ERROR: " + node.getLeft().toString() + ", is not of type " + BOOL + ".");
            addType(node, ERRORTYPE);
        }
    }

    public void outAOrExpr(AOrExpr node){
        if(typeTable.get(node.getLeft()).equals(BOOL)){
            if(typeTable.get(node.getRight()).equals(BOOL)){
                addType(node, BOOL);
            }else{
                ErrorList.add("ERROR: " + node.getRight().toString() + ", is not of type " + BOOL + ".");
                addType(node, ERRORTYPE);
            }
        }
        else{
            ErrorList.add("ERROR: " + node.getLeft().toString() + ", is not of type " + BOOL + ".");
            addType(node, ERRORTYPE);
        }
    }

    public void outAModExpr(AModExpr node){
        if(typeTable.get(node.getLeft()).equals(NUM)){
            if(typeTable.get(node.getRight()).equals(NUM)){
                addType(node, NUM);
            }else{
                ErrorList.add("ERROR: " + node.getRight().toString() + ", is not of type " + NUM + ".");
                addType(node, ERRORTYPE);
            }
        }
        else{
            ErrorList.add("ERROR: " + node.getLeft().toString() + ", is not of type " + NUM + ".");
            addType(node, ERRORTYPE);
        }
    }

    public void outAMultExpr(AMultExpr node){
        if(typeTable.get(node.getLeft()).equals(NUM)){
            if(typeTable.get(node.getRight()).equals(NUM)){
                addType(node, NUM);
            }else{
                ErrorList.add("ERROR: " + node.getRight().toString() + ", is not of type " + NUM + ".");
                addType(node, ERRORTYPE);
            }
        }
        else{
            ErrorList.add("ERROR: " + node.getLeft().toString() + ", is not of type " + NUM + ".");
            addType(node, ERRORTYPE);
        }
    }

    public void outADivideExpr(ADivideExpr node){
        if(typeTable.get(node.getLeft()).equals(NUM)){
            if(typeTable.get(node.getRight()).equals(NUM)){
                addType(node, NUM);
            }else{
                ErrorList.add("ERROR: " + node.getRight().toString() + ", is not of type " + NUM + ".");
                addType(node, ERRORTYPE);
            }
        }
        else{
            ErrorList.add("ERROR: " + node.getLeft().toString() + ", is not of type " + NUM + ".");
            addType(node, ERRORTYPE);
        }
    }

    public void outAPlusExpr(APlusExpr node){
        if(typeTable.get(node.getLeft()).equals(TEXT) || typeTable.get(node.getRight()).equals(TEXT)){
            addType(node, TEXT);
        }
        else if(typeTable.get(node.getLeft()).equals(NUM)){
            if(typeTable.get(node.getRight()).equals(NUM)){
                addType(node, NUM);
            }
            else{
                ErrorList.add("ERROR: " + node.getRight().toString() + ", is not of type " + NUM + ".");
                addType(node, ERRORTYPE);
            }
        }
        else{
            ErrorList.add("ERROR: " + node.getLeft().toString() + ", is not of type " + NUM + ".");
            addType(node, ERRORTYPE);
        }
    }

    public void outAMinusExpr(AMinusExpr node){
        if(typeTable.get(node.getLeft()).equals(NUM)){
            if(typeTable.get(node.getRight()).equals(NUM)){
                addType(node, NUM);
            }else{
                ErrorList.add("ERROR: " + node.getRight().toString() + ", is not of type " + NUM + ".");
                addType(node, ERRORTYPE);
            }
        }
        else{
            ErrorList.add("ERROR: " + node.getLeft().toString() + ", is not of type " + NUM + ".");
            addType(node, ERRORTYPE);
        }
    }

    //Stmt
    public void outAAssignmentStmt(AAssignmentStmt node){
        if(getType(node.getId().getText()).equals(typeTable.get(node.getExpr()))){
            addType(node, getType(node.getId().getText()));
        }
        else{
            ErrorList.add("ERROR: " + node.getId().getText() + ", is not of type " + typeTable.get(node.getId()) + ".");
            addType(node, ERRORTYPE);
        }
    }

    public void outAClasscallStmt(AClasscallStmt node){
        addType(node, typeTable.get(node.getCall()));
    }

    public void outAFunccallStmt(AFunccallStmt node){
        addType(node, typeTable.get(node.getCall()));
    }

    public void outAVardclStmt(AVardclStmt node){
        addType(node, typeTable.get(node.getPdcl()));
    }

    //Inherit
    public void outAInherit(AInherit node){
        //Implement later
    }


    //Type - Not necessary
    /*public void outABoolType(ABoolType node){
        addType(node, BOOL);
    }

    public void outANumType(ANumType node){
        addType(node, NUM);
    }

    public void outATextType(ATextType node){
        addType(node, TEXT);
    }

    public void outAObjectType(AObjectType node){
        addType(node, node.getId().getText());
    }*/


}
