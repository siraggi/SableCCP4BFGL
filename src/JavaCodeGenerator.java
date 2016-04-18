/**
 * Created by august on 14/04/16.
 */

import grammar.ini.analysis.*;
import grammar.ini.node.*;

import java.io.*;
import java.util.*;

public class JavaCodeGenerator extends DepthFirstAdapter {

    private Hashtable<Node, String> typeTable;
    private Hashtable<String, String> superTable;
    private File file;
    private FileWriter fw;
    private BufferedWriter bw;


    public JavaCodeGenerator(Hashtable<Node, String> typeTable, Hashtable<String, String> superTable) throws IOException {
        this.typeTable = typeTable;
        this.superTable = superTable;
        file = new File("output.java");
        if (!file.exists()){
            file.createNewFile();
        }
        fw = new FileWriter(file.getAbsoluteFile());
        bw = new BufferedWriter(fw);
        emit("hej ");
        emit("hejhejehej");
    }

    //emitfunctions
    private void emit(String s){

        try {
            bw.write(s + "\\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void emitComment(String s){
        try {

            bw.write("//" + s + "\\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void skip(int i){

    }



    //Prog, inject slick and others libraries here
    public void inAProg(AProg node){

    }

    public void outAProg(AProg node) {
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //main
    public void inAMainPdcl(AMainPdcl node){
    }

    public void outAMainPdcl(AMainPdcl node){

    }


    //class

    //funcs

    //dcls

    //assignments

    //conditionals










}