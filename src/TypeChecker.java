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
    private static final String LIST = "List";
    private static final String VOID = "void";
    private static final String ERRORTYPE = "9";
    private LineAndPos lineAndPos;

    // Stack of symbol tables with name as key and type as value
    public Stack<Hashtable<String, Node>> symStack;
    public Hashtable<Node, String> typeTable;
    public ArrayList<String> ErrorList;

    private Hashtable<String, String> superTable;


    public TypeChecker(){
        symStack = new Stack<>();
        typeTable = new Hashtable<>();
        superTable = new Hashtable<>();
        ErrorList = new ArrayList<>();
        lineAndPos = new LineAndPos();
    }

    //Scope methods
    private void openScope(Node node){
        TableFiller tf = new TableFiller(node, false, lineAndPos);

        if(node instanceof AClassPdcl){
            if(((AClassPdcl)node).getInherit() != null){
                openScope(getNode(((AInherit)((AClassPdcl)node).getInherit()).getType().toString().trim()));
            }
        }

        if(node != null)
            node.apply(tf);

        symStack.push(tf.symStack.pop());
        typeTable.putAll(tf.typeTable);
        superTable.putAll(tf.superTable);
        ErrorList.addAll(tf.ErrorList);
    }

    private void openScope(Node node, boolean dotCall){
        TableFiller tf = new TableFiller(node, dotCall, lineAndPos);

        if(node instanceof AClassPdcl){
            if(((AClassPdcl)node).getInherit() != null){
                openScope(getNode(((AInherit)((AClassPdcl)node).getInherit()).getType().toString().trim()), dotCall);
            }
        }

        if(node != null)
            node.apply(tf);

        symStack.push(tf.symStack.pop());
        typeTable.putAll(tf.typeTable);
        superTable.putAll(tf.superTable);
        ErrorList.addAll(tf.ErrorList);
    }

    private void closeScope(){
        symStack.pop();
    }



    //Symbol table methods
    private void addSymbol(String name, Node node, String type){
        if (!symStack.peek().containsKey(name)) {
            symStack.peek().put(name, node);
            addType(node, type);
        }
        else
            ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : " + name + " is already defined in this scope " + symStack.peek().size());
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

                    tempNode = (AFuncPdcl) symStack.get(i - 1).get(id);

                    //Check if the function call is recursive
                    if(((AFuncBody)tempNode.getBody()).getReturn() instanceof AIdReturn){
                        RecursiveCheck rc = new RecursiveCheck(tempNode);
                        tempNode.apply(rc);

                        if(!rc.isRecursive)
                        {
                            if (getNode(id) != null) {
                                getNode(id).apply(typeChecker);

                                typeTable.putAll(typeChecker.typeTable);
                                ErrorList.addAll(typeChecker.ErrorList);

                                if (symStack.get(i - 1).get(id).getClass().equals(AFuncPdcl.class)) {
                                    tempNode = (AFuncPdcl) symStack.get(i - 1).get(id); // er det nødvendigt

                                    addType(tempNode, typeTable.get(tempNode.getBody()));
                                }

                                type = typeTable.get(symStack.get(i - 1).get(id));
                            }
                        }
                        else
                            type = ERRORTYPE;
                        ErrorList.add("ERROR line " + lineAndPos.getLine(tempNode) + " pos " + lineAndPos.getPos(tempNode) + " : Recursive call not allowed.");

                    }
                    else
                        type = VOID;
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

        //ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : No decleration for " + id);

        return null;
    }

    private boolean compareType(Node node, String type){
        String firstType = typeTable.get(node);
        boolean notFinished = true;
        boolean result = false;

        while (notFinished){
            if(firstType.equals(type.trim())){
                notFinished = false;
                result = true;
            }
            else if(superTable.get(firstType) == null){
                notFinished = false;
            }
            else{
                firstType = superTable.get(firstType);
            }
        }


        return result;
    }

    private boolean compareType(String id, String type){
        String firstType = getType(id);
        boolean notFinished = true;
        boolean result = false;

        while (notFinished){
            if(firstType.equals(type.trim())){
                notFinished = false;
                result = true;
            }
            else if(superTable.get(firstType) == null){
                notFinished = false;
            }
            else{
                firstType = superTable.get(firstType);
            }
        }


        return result;
    }


    //Visitor methods

    //Prog in/out
    public void inAProg(AProg node){
        openScope(node);
        node.apply(lineAndPos);
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

        if(!compareType(node.getExpr(), NUM)){
            ErrorList.add("ERROR line " + lineAndPos.getLine(node.getExpr()) + " pos " + lineAndPos.getPos(node.getExpr()) + " : " + node.getExpr().toString() + ", is not of type " + NUM + ".");
        }

        if(!compareType(node.getId().getText(), NUM)){
            ErrorList.add("ERROR line " + lineAndPos.getLine(node.getId()) + " pos " + lineAndPos.getPos(node.getId()) + " : " + node.getId().toString() + ", is not of type " + NUM + ".");
        }
    }

    //For loop down
    public void inAFordownStmt(AFordownStmt node){
        openScope(node);
    }

    public void outAFordownStmt(AFordownStmt node){
        closeScope();
        if(!compareType(node.getExpr(), NUM)){
            ErrorList.add("ERROR line " + lineAndPos.getLine(node.getExpr()) + " pos " + lineAndPos.getPos(node.getExpr()) + " : " + node.getExpr().toString() + ", is not of type " + NUM + ".");
        }
        if(!compareType(node.getId().getText(), NUM)){
            ErrorList.add("ERROR line " + lineAndPos.getLine(node.getId()) + " pos " + lineAndPos.getPos(node.getId()) + " : " + node.getId().toString() + ", is not of type " + NUM + ".");
        }
    }

    //While loop
    public void inAWhileStmt(AWhileStmt node){
        openScope(node);
    }

    public void outAWhileStmt(AWhileStmt node){
        closeScope();

        if(!compareType(node.getExpr(), BOOL)){
            ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : " + node.getExpr().toString() + ", is not of type " + BOOL + ".");
        }
    }

    //If
    public void inAIfConditional(AIfConditional node){
        openScope(node);
    }

    public void outAIfConditional(AIfConditional node){
        closeScope();

        if(!compareType(node.getExpr(), BOOL)){
            ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : " + node.getExpr().toString() + ", is not of type " + BOOL + ".");
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

        if(!compareType(node.getExpr(), BOOL)){
            ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : " + node.getExpr().toString() + ", is not of type " + BOOL + ".");
        }
    }

    //Event dcl
    public void inAEventPdcl(AEventPdcl node){
        openScope(node);
    }

    public void outAEventPdcl(AEventPdcl node){
        closeScope();
    }

    public void outAVarPdcl(AVarPdcl node){
        addSymbol(node.getId().getText(), node, node.getType().toString());
    }

    //Var asg dcl
    public void outAVarasgPdcl(AVarasgPdcl node){
        if(!compareType(node.getExpr(), node.getType().toString().trim())){
            ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : " + node.getExpr().toString() + ", is not of type " + node.getType() + ".");
            addType(node, ERRORTYPE);
        }
        else{
            addSymbol(node.getId().getText(), node, node.getType().toString());
        }
    }

    //List dcl
    public void outAListPdcl(AListPdcl node){
        addSymbol(node.getId().getText(), node, LIST);
        superTable.put(node.getId().getText(), node.getType().toString().trim());
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
        openScope(getNode(getType(((AVarCall)node.getFirst()).getId().getText())), true);

        for (int i = 0; i < node.getRest().size() - 1; i++){
            openScope(getNode(getType(((AVarCall)node.getRest().get(i)).getId().getText())), true);
        }


    }

    public void outAClassCall(AClassCall node){
        Node n = node.getRest().getLast();


        if(n instanceof AFuncCall)
            addType(node, getType(((AFuncCall)n).getId().getText()));
        else
            addType(node, getType(n.toString().trim()));

        closeScope();

        for (int i = 0; i < node.getRest().size() - 1; i++){
            closeScope();
        }
    }

    public void outAFuncCall(AFuncCall node){
        AFuncPdcl dcl = (AFuncPdcl) getNode(node.getId().getText());

        if(dcl != null) {
            if (node.getParams().size() != dcl.getParams().size()) {
                ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : " + node.getId().getText() + ", takes " + dcl.getParams().size() + " not " + node.getParams().size() + ".");
            }
            else
                for (int i = 0; i < node.getParams().size(); i++) {
                    if (!compareType(node.getParams().get(i), ((AFormalParam)dcl.getParams().get(i)).getType().toString().trim())) {
                        ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : parameter " + i + " is not of type " + ((AFormalParam)dcl.getParams().get(i)).getType().toString().trim() + ".");
                    }
                }
        }

        addType(node, getType(node.getId().getText()));
    }

    public void outACallExpr(ACallExpr node){
        addType(node, typeTable.get(node.getCall()));
    }
    
    //Expr
    public void outANotExpr(ANotExpr node){
       if(compareType(node, BOOL))
           addType(node, BOOL);
       else{
           ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : " + node.getExpr().toString() + ", is not of type " + BOOL + ".");
           addType(node, ERRORTYPE);
       }
    }

    public void outAUnaryExpr(AUnaryExpr node){
        if(compareType(node.getExpr(), NUM))
            addType(node, NUM);
        else{
            ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : " + node.getExpr().toString() + ", is not of type " + NUM + ".");
            addType(node, ERRORTYPE);
        }

    }

    public void outALessequalsExpr(ALessequalsExpr node){
        if(compareType(node.getLeft(), NUM)){
            if(compareType(node.getRight(), NUM)){
                addType(node, BOOL);
            }else{
                ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : " + node.getRight().toString() + ", is not of type " + NUM + ".");
                addType(node, ERRORTYPE);
            }
        }
        else{
            ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : " + node.getLeft().toString() + ", is not of type " + NUM + ".");
            addType(node, ERRORTYPE);
        }
    }

    public void outAGreaterequalsExpr(AGreaterequalsExpr node){
        if(compareType(node.getLeft(), NUM)){
            if(compareType(node.getRight(), NUM)){
                addType(node, BOOL);
            }else{
                ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : " + node.getRight().toString() + ", is not of type " + NUM + ".");
                addType(node, ERRORTYPE);
            }
        }
        else{
            ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : " + node.getLeft().toString() + ", is not of type " + NUM + ".");
            addType(node, ERRORTYPE);
        }
    }

    public void outALessExpr(ALessExpr node){
        if(compareType(node.getLeft(), NUM)){
            if(compareType(node.getRight(), NUM)){
                addType(node, BOOL);
            }else{
                ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : " + node.getRight().toString() + ", is not of type " + NUM + ".");
                addType(node, ERRORTYPE);
            }
        }
        else{
            ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : " + node.getLeft().toString() + ", is not of type " + NUM + ".");
            addType(node, ERRORTYPE);
        }
    }

    public void outAGreaterExpr(AGreaterExpr node){
        if(compareType(node.getLeft(), NUM)){
            if(compareType(node.getRight(), NUM)){
                addType(node, BOOL);
            }else{
                ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : " + node.getRight().toString() + ", is not of type " + NUM + ".");
                addType(node, ERRORTYPE);
            }
        }
        else{
            ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : " + node.getLeft().toString() + ", is not of type " + NUM + ".");
            addType(node, ERRORTYPE);
        }
    }

    public void outANotequalsExpr(ANotequalsExpr node){
        if(compareType(node.getLeft(), NUM)){
            if(compareType(node.getRight(), NUM)){
                addType(node, BOOL);
            }else{
                ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : " + node.getRight().toString() + ", is not of type " + NUM + ".");
                addType(node, ERRORTYPE);
            }
        }
        else{
            ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : " + node.getLeft().toString() + ", is not of type " + NUM + ".");
            addType(node, ERRORTYPE);
        }
    }

    public void outAEqualsExpr(AEqualsExpr node){
        if(compareType(node.getLeft(), NUM)){
            if(compareType(node.getRight(), NUM)){
                addType(node, BOOL);
            }else{
                ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : " + node.getRight().toString() + ", is not of type " + NUM + ".");
                addType(node, ERRORTYPE);
            }
        }
        else{
            ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : " + node.getLeft().toString() + ", is not of type " + NUM + ".");
            addType(node, ERRORTYPE);
        }
    }

    public void outAAndExpr(AAndExpr node){
        if(compareType(node.getLeft(), BOOL)){
            if(compareType(node.getRight(), BOOL)){
                addType(node, BOOL);
            }
            else{
                ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : " + node.getRight().toString() + ", is not of type " + BOOL + ".");
                addType(node, ERRORTYPE);
            }
        }
        else{
            ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : " + node.getLeft().toString() + ", is not of type " + BOOL + ".");
            addType(node, ERRORTYPE);
        }
    }

    public void outAOrExpr(AOrExpr node){
        if(compareType(node.getLeft(), BOOL)){
            if(compareType(node.getRight(), BOOL)){
                addType(node, BOOL);
            }else{
                ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : " + node.getRight().toString() + ", is not of type " + BOOL + ".");
                addType(node, ERRORTYPE);
            }
        }
        else{
            ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : " + node.getLeft().toString() + ", is not of type " + BOOL + ".");
            addType(node, ERRORTYPE);
        }
    }

    public void outAModExpr(AModExpr node){
        if(compareType(node.getLeft(), NUM)){
            if(compareType(node.getRight(), NUM)){
                addType(node, NUM);
            }else{
                ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : " + node.getRight().toString() + ", is not of type " + NUM + ".");
                addType(node, ERRORTYPE);
            }
        }
        else{
            ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : " + node.getLeft().toString() + ", is not of type " + NUM + ".");
            addType(node, ERRORTYPE);
        }
    }

    public void outAMultExpr(AMultExpr node){
        if(compareType(node.getLeft(), NUM)){
            if(compareType(node.getRight(), NUM)){
                addType(node, NUM);
            }else{
                ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : " + node.getRight().toString() + ", is not of type " + NUM + ".");
                addType(node, ERRORTYPE);
            }
        }
        else{
            ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : " + node.getLeft().toString() + ", is not of type " + NUM + ".");
            addType(node, ERRORTYPE);
        }
    }

    public void outADivideExpr(ADivideExpr node){
        if(compareType(node.getLeft(), NUM)){
            if(compareType(node.getRight(), NUM)){
                addType(node, NUM);
            }else{
                ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : " + node.getRight().toString() + ", is not of type " + NUM + ".");
                addType(node, ERRORTYPE);
            }
        }
        else{
            ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : " + node.getLeft().toString() + ", is not of type " + NUM + ".");
            addType(node, ERRORTYPE);
        }
    }

    public void outAPlusExpr(APlusExpr node){
        if(compareType(node.getLeft(), TEXT) || compareType(node.getRight(), TEXT)){
            addType(node, TEXT);
        }
        else if(typeTable.get(node.getLeft()).equals(NUM)){
            if(typeTable.get(node.getRight()).equals(NUM)){
                addType(node, NUM);
            }
            else{
                ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : " + node.getRight().toString() + ", is not of type " + NUM + ".");
                addType(node, ERRORTYPE);
            }
        }
        else{
            ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : " + node.getLeft().toString() + ", is not of type " + NUM + ".");
            addType(node, ERRORTYPE);
        }
    }

    public void outAMinusExpr(AMinusExpr node){
        if(compareType(node.getLeft(), NUM)){
            if(compareType(node.getRight(), NUM)){
                addType(node, NUM);
            }else{
                ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : " + node.getRight().toString() + ", is not of type " + NUM + ".");
                addType(node, ERRORTYPE);
            }
        }
        else{
            ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : " + node.getLeft().toString() + ", is not of type " + NUM + ".");
            addType(node, ERRORTYPE);
        }
    }

    //Stmt
    public void outAAssignmentStmt(AAssignmentStmt node){
        if(getType(node.getId().getText()).equals(typeTable.get(node.getExpr()))){
            addType(node, getType(node.getId().getText()));
        }
        else{
            ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : " + node.getId().getText() + ", is not of type " + typeTable.get(node.getId()) + ".");
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
        Node n = getNode(node.getType().toString().trim());

        if(n == null){
            ErrorList.add("ERROR line " + lineAndPos.getLine(node) + " pos " + lineAndPos.getPos(node) + " : No declaration for " + node.getType().toString().trim() + ".");
        }
    }
}
