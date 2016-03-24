import grammar.ini.analysis.DepthFirstAdapter;
import grammar.ini.node.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

/**
 * Created by august on 24/03/16.
 */
public class TableFiller extends DepthFirstAdapter {

    public Hashtable<String, Node> symbolTable;
    public Hashtable<Node, String> typeTable;
    public ArrayList<String> ErrorList;


    public TableFiller(){
        symbolTable = new Hashtable<>();
        typeTable = new Hashtable<>();
        ErrorList = new ArrayList<>();
    }

    //Symbol table methods
    private void addSymbol(String name, Node node){
        if (!symbolTable.containsKey(name)) {
            symbolTable.put(name, node);
        }
        else
            ErrorList.add("ERROR: " + name + " is already defined in this scope");
    }

    //Class dcl
    public void outAClassPdcl(AClassPdcl node){
        addSymbol(node.getId().getText(), node);
        typeTable.put(node, node.getId().getText());
    }

    //Func dcl
    public void outAFuncPdcl(AFuncPdcl node){
        addSymbol(node.getId().getText(), node);
    }
}
