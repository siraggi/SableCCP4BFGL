import grammar.ini.analysis.DepthFirstAdapter;
import grammar.ini.node.ACallExpr;
import grammar.ini.node.AFuncCall;
import grammar.ini.node.AFuncPdcl;
import grammar.ini.node.Node;

/**
 * Created by august on 14/04/16.
 */
public class RecursiveCheck extends DepthFirstAdapter {
    public boolean isRecursive = false;
    private AFuncPdcl dcl;

    public RecursiveCheck(AFuncPdcl dcl){
        this.dcl = dcl;
    }

    public void defaultOut(Node node){
        AFuncCall expr;

        if(node instanceof ACallExpr && ((ACallExpr) node).getCall() instanceof AFuncCall){
            expr = (AFuncCall) ((ACallExpr) node).getCall();

            if(dcl.getId().getText().trim().equals(expr.getId().getText().trim())){
                isRecursive = true;
            }
        }
    }
}
