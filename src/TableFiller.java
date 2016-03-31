import grammar.ini.analysis.DepthFirstAdapter;
import grammar.ini.node.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

/**
 * Created by august on 24/03/16.
 */
public class TableFiller extends DepthFirstAdapter {

    public Stack<Hashtable<String, Node>> symStack;
    public Hashtable<String, Node> symbolTable;
    public Hashtable<Node, String> typeTable;
    public ArrayList<String> ErrorList;

    private Node node;


    public TableFiller(Node node){
        this.node = node;

        symStack = new Stack<>();
        symbolTable = new Hashtable<>();
        typeTable = new Hashtable<>();
        ErrorList = new ArrayList<>();

        symStack.push(new Hashtable<String, Node>());
    }

    private void openScope(){
        symStack.push(new Hashtable<String, Node>());
    }

    private void closeScope(){
        symStack.pop();
    }

    //Symbol table methods
    private void addSymbol(String name, Node node){
        if (!symStack.peek().containsKey(name)) {
            symStack.peek().put(name, node);
        }
        else
            ErrorList.add("ERROR: " + name + " is already defined in this scope " + symStack.peek().size());
    }

    private void addType(Node node, String type){
        typeTable.put(node, type.trim());
    }

    //Class dcl
    public void inAClassPdcl(AClassPdcl node){
        openScope();
    }

    public void outAClassPdcl(AClassPdcl node){
        if(node != this.node){
            closeScope();
            addSymbol(node.getId().getText(), node);
            addType(node, node.getId().getText());
        }

    }

    //Func dcl
    public void inAFuncPdcl(AFuncPdcl node){
        openScope();
    }

    public void outAFuncPdcl(AFuncPdcl node){
        closeScope();
        addSymbol(node.getId().getText(), node);
    }

    public void outAVarPdcl(AVarPdcl node){
        addSymbol(node.getId().getText(), node);
        addType(node, node.getType().toString());


    }

    public void outAVarasgPdcl(AVarasgPdcl node){
        addSymbol(node.getId().getText(), node);
        addType(node, node.getType().toString());
    }

    public void outAListPdcl(AListPdcl node){
        addSymbol(node.getId().getText(), node);
        addType(node, node.getType().toString());
    }
}
