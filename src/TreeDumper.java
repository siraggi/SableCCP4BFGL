
import grammar.ini.analysis.DepthFirstAdapter;
import grammar.ini.lexer.Lexer;
import grammar.ini.node.Node;
import grammar.ini.node.Start;
import grammar.ini.node.Token;
import grammar.ini.parser.Parser;

import java.io.*;

public class TreeDumper extends DepthFirstAdapter {
    private int depth = 0;
    private PrintWriter out;

    public TreeDumper(PrintWriter out) {
        this.out = out;
    }

    public void defaultCase(Node node) {
        indent();
        out.println(((Token)node).getText());
    }

    public void defaultIn(Node node) {
        indent();
        printNodeName(node);
        out.println();

        depth = depth+1;
    }

    public void defaultOut(Node node) {
        depth = depth-1;
        out.flush();
    }

    private void printNodeName(Node node) {
        String fullName = node.getClass().getName();
        String name = fullName.substring(fullName.lastIndexOf('.')+1);

        out.print(name);
    }

    private void indent() {
        for (int i = 0; i < depth; i++) out.write("   ");
    }

    public static void main(String[] args) {

        try {
            File file = new File("test", "BFGLtest.bfgl");
            PushbackReader pushbackReader = new PushbackReader(new FileReader(file));
            Parser parser = new Parser(new Lexer(pushbackReader));

            Start start = parser.parse();
            start.getPProg().apply(new TreeDumper(new PrintWriter(System.out)));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
