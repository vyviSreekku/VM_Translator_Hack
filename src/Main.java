import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
public class Main {
    //int SP=256;
    private static void generator(ArrayList<String> No_whitespaces, ArrayList<String> Commands, String filename , String Outputfilename)//Generator writes the code into an asm file
    {
        ArrayList<String> code = new ArrayList<>();
        CodeWriter cb = new CodeWriter();//Creates object of codewriter class
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(Outputfilename));//writer
            for (int i = 0; i < No_whitespaces.size(); i++) {
                code = cb.codeWriter(No_whitespaces.get(i),Commands.get(i),filename);//Calls the method which returns the assembly code
                for (String x:code) {
                    writer.write(x + "\n");
                }
            }
            writer.write("(STOP)\n"+"@STOP\n"+"0;JMP");
            writer.close();

        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }

    public static void main(String[] args) {
        Parser parse=new Parser();          //Object of parser class
        ArrayList<String> No_whitespaces = new ArrayList<>();
        ArrayList<String> Commands = new ArrayList<>();

        String Inputfilename = "D:/nand2tetris/projects/07/MemoryAccess/BasicTest/BasicTest.vm";
        String Outputfilename = "D:/nand2tetris/projects/07/MemoryAccess/BasicTest/BasicTest.asm";

        No_whitespaces = parse.whitespace_handler(Inputfilename);   // Arraylist of file with no whitespaces
        Commands = parse.commandRecogniser(No_whitespaces);     //Arraylist of commands wrt to file

        String filename = Inputfilename.substring(Inputfilename.indexOf("/",-1),Inputfilename.indexOf(".",-1));

        generator(No_whitespaces,Commands, filename ,Outputfilename);//method of smae class for writing code

    }
}