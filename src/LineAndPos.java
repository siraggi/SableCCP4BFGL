import grammar.ini.node.Node;
import grammar.ini.analysis.ReversedDepthFirstAdapter;
import grammar.ini.node.Token;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by august on 05/04/16.
 */
public class LineAndPos extends ReversedDepthFirstAdapter{
    private Map<Node, Integer> lines = new HashMap<Node, Integer>();
    private Map<Node, Integer> positions = new HashMap<Node, Integer>();

    private int line, pos;


    public LineAndPos(){
        lines = new HashMap<>();
        positions = new HashMap<>();
    }

    public int getLine(Node node) {
        return lines.get(node);
    }

    public int getPos(Node node) {
        return positions.get(node);
    }

    public void defaultCase(Node node){
        if(node instanceof Token){
            Token token = (Token)node;
            line = token.getLine();
            pos = token.getPos();
            lines.put(node, line);
            positions.put(node, pos);
        }
    }

    public void defaultOut(Node node) {
        lines.put(node, line);
        positions.put(node, pos);
    }

}
