/**
 * Created by august on 22/03/16.
 */
import grammar.ini.analysis.*;
import grammar.ini.node.AAssignmentStmt;
import grammar.ini.node.AProg;
import grammar.ini.node.AVardclPdcl;

public class TypeChecker extends DepthFirstAdapter{

    public void outAProg(AProg node){
        System.out.print("Hello test");
    }

    public void outAAssignmentStmt(AAssignmentStmt node){
        System.out.println(node.getId() + " = " + node.getExpr());
    }

}
