import grammar.ini.lexer.Lexer;
import grammar.ini.lexer.LexerException;
import grammar.ini.node.Start;
import grammar.ini.parser.Parser;
import grammar.ini.parser.ParserException;

import javax.xml.transform.ErrorListener;
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

        if(typeChecker.ErrorList.isEmpty()){
            typeChecker.ErrorList.forEach(System.out::println);
            return;
        }

        CodeGenerator codeGenerator = new CodeGenerator(typeChecker.typeTable, typeChecker.superTable);
        tree.apply(codeGenerator);
    }
}
