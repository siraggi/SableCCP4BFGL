import grammar.ini.lexer.Lexer;
import grammar.ini.lexer.LexerException;
import grammar.ini.node.Start;
import grammar.ini.parser.Parser;
import grammar.ini.parser.ParserException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;


public class Main {

    public static void main(String[] args)
    throws ParserException, LexerException, IOException
    {
        TypeChecker typeChecker = new TypeChecker();
        File file = new File("test", "BFGLtest.bfgl");
        PushbackReader pushbackReader = new PushbackReader(new FileReader(file));
        Parser parser = new Parser(new Lexer(pushbackReader));
        Start tree = parser.parse();
        tree.apply(typeChecker);


        for (String s: typeChecker.ErrorList)
            System.out.println(s);

        //System.out.println("CSTTree..........\n" + tree.toString());



    }
}
