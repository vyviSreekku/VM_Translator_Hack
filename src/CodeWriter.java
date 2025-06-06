import java.util.ArrayList;
import java.util.List;

public class CodeWriter {
    static int i=0,j=0,k=0;
    public ArrayList<String> standard(String Segment, String Command)//It handles local, argument , this and that.  Here segment de=oesn't have the command with it
    {
        String[] args = Segment.split(" ");

        ArrayList<String> Code = new ArrayList<>();

        if (Command.equals("c_push"))//Checks if the command is push
        {
            Code.add("@" + args[1]);
            Code.add("D=A");

            if (args[0].equals("local")) {
                Code.add("@LCL");
                Code.add("A=M+D");
                Code.add("D=M");
            } else if (args[0].equals("argument")) {
                Code.add("@ARG");
                Code.add("A=M+D");
                Code.add("D=M");
            } else if (args[0].equals("this")) {
                Code.add("@THIS");
                Code.add("A=M+D");
                Code.add("D=M");
            } else if (args[0].equals("that")) {
                Code.add("@THAT");
                Code.add("A=A+D");
                Code.add("D=M");
            }

            Code.add("@SP");
            Code.add("A=M");
            Code.add("M=D");
            Code.add("@SP");
            Code.add("M=M+1");
            return Code;
        }

        else if (Command.equals("c_pop"))//Not necessary, else can be used but for making sure it is pop command
        {
            Code.add("@SP");
            Code.add("M=M-1");
            Code.add("@"+args[1]);
            Code.add("D=A");

            if (args[0].equals("local")) {
                Code.add("@LCL");
                Code.add("A=M+D");
                Code.add("D=A");

            } else if (args[0].equals("argument")) {
                Code.add("@ARG");
                Code.add("A=M+D");
                Code.add("D=A");

            } else if (args[0].equals("this")) {
                Code.add("@THIS");
                Code.add("A=M+D");
                Code.add("D=A");

            } else if (args[0].equals("that")) {
                Code.add("@THAT");
                Code.add("A=M+D");
                Code.add("D=A");
            }

            Code.add("@13");
            Code.add("M=D");
            Code.add("@SP");
            Code.add("A=M");
            Code.add("D=M");
            Code.add("@13");
            Code.add("A=M");
            Code.add("M=D");

        }

        return Code;
    }


    public ArrayList<String> constantHandler(String Segment, String Command)//To handle constant segment
    {
        ArrayList<String> Code = new ArrayList<>();

        String[] args = Segment.split(" ");

        if(Command.equals("c_push"))
        {
            Code.add("@"+args[1]);
            Code.add("D=A");
        }
        Code.add("@SP");
        Code.add("A=M");
        Code.add("M=D");
        Code.add("@SP");
        Code.add("M=M+1");

        return Code;

    }

    public ArrayList<String> staticHandler(String Segment, String Command,String filename)//to handle static
    {
        String[] args = Segment.split(" ");

        ArrayList<String> Code = new ArrayList<>();

        if(Command.equals("c_push"))
        {
            Code.add("@"+filename+"."+args[1]);// create an a_instruction @filename.i
            Code.add("D=M");
            Code.add("@SP");
            Code.add("A=M");
            Code.add("M=D");
            Code.add("@SP");
            Code.add("M=M+1");
        }
        else if(Command.equals("c_pop"))
        {
            Code.add("@SP");
            Code.add("M=M-1");
            Code.add("@SP");
            Code.add("A=M");
            Code.add("D=M");
            Code.add("@"+filename+"."+args[1]);
            Code.add("M=D");
        }
        return Code;
    }

    public ArrayList<String> tempHandler(String Segment, String Command) //Handles temp memory segments
    {
        ArrayList<String> Code = new ArrayList<>();

        String[] args = Segment.split(" ");
        if (Command.equals("c_push")) {
            Code.add("@" + args[1]);
            Code.add("D=A");
            Code.add("@5");
            Code.add("A=A+D");
            Code.add("D=M");
            Code.add("@SP");
            Code.add("A=M");
            Code.add("M=D");
            Code.add("@SP");
            Code.add("M=M+1");
        } else if (Command.equals("c_pop")) {
            Code.add("@SP");
            Code.add("M=M-1");
            Code.add("@"+args[1]);
            Code.add("D=A");
            Code.add("@5");
            Code.add("A=A+D");
            Code.add("D=A");
            Code.add("@13");
            Code.add("M=D");
            Code.add("@SP");
            Code.add("A=M");
            Code.add("D=M");
            Code.add("@13");
            Code.add("A=M");
            Code.add("M=D");
        }
        return Code;
    }

    public ArrayList<String> pointerHandler(String Segment, String Command) //Handle pointerss
    {
        ArrayList<String> Code = new ArrayList<>();

        String[] args = Segment.split(" ");

        if (Command.equals("c_push")) {
            if (args[1].equals("0")) {
                Code.add("@THIS");
                Code.add("D=M");
            } else {
                Code.add("@THAT");
                Code.add("D=M");
            }
            Code.add("@SP");
            Code.add("A=M");
            Code.add("M=D");
            Code.add("@SP");
            Code.add("M=M+1");
        }
        else if (Command.equals("c_pop")) {
            Code.add("@SP");
            Code.add("M=M-1");
            Code.add("@SP");
            Code.add("A=M");
            Code.add("D=M");
            if (args[1].equals("0")) {
                Code.add("@THIS");
                Code.add("M=D");
            }
            else {
                Code.add("@THAT");
                Code.add("M=D");
            }
        }
        return Code;
    }


    public ArrayList<String> segmentBreaker(String Segment, String Command, String Filename)// To deal with push and pop of memory segments
    {
        ArrayList<String> standard = new ArrayList<>(List.of("local","argument","this", "that"));

        ArrayList<String> Code = new ArrayList<>();

        if(standard.contains(Segment.substring(0,Segment.indexOf(" "))))
        {
            Code = standard(Segment,Command);
        }

        else if (Segment.contains("constant"))
        {
            Code = constantHandler(Segment,Command);
        }

        else if (Segment.contains("static"))
        {
            Code = staticHandler(Segment,Command,Filename);
        }

        else if (Segment.contains("temp"))
        {
            Code = tempHandler(Segment,Command);
        }

        else if (Segment.contains("pointer"))
        {
            Code = pointerHandler(Segment,Command);
        }
        return Code;
    }


    public ArrayList<String> arithmeticHandler(String line)//Handles all arithmetic and logical commands
    {
        ArrayList<String> Code = new ArrayList<>();

        if(line.equals("neg")) //Specially handle negation since only one member can be negated
        {
            Code.add("@SP");
            Code.add("A=M-1");
            Code.add("M=-M");
            return Code;
        }

        if(line.equals("not"))//Specially handle not operation since only one member can have not operation
        {
            Code.add("@SP");
            Code.add("A=M-1");
            Code.add("M=!M");
            return Code;
        }
        /*Since some code is same for add, sub ,eq , lt, gt ,and ,or */
        Code.add("@SP");
        Code.add("M=M-1");
        Code.add("@SP");
        Code.add("A=M");
        Code.add("D=M");
        Code.add("@SP");
        Code.add("A=M-1");

        if(line.equals("add"))
        {
            Code.add("M=M+D");
        }
        else if (line.equals("sub"))
        {
            Code.add("M=M-D");
        }
        else if (line.equals("eq"))
        {
            Code.add("D=M-D");
            Code.add("@EQ.true"+"."+i);//static variable i to differentiate labels
            Code.add("D;JEQ");
            Code.add("@SP");
            Code.add("A=M-1");
            Code.add("M=0");
            Code.add("@EQ.after"+"."+i);
            Code.add("0;JMP");
            Code.add("(EQ.true"+"."+i+")");
            Code.add("@SP");
            Code.add("A=M-1");
            Code.add("M=-1");
            Code.add("(EQ.after"+"."+i+")");
            i+=1;
        }
        else if (line.equals("lt"))
        {
            Code.add("D=M-D");
            Code.add("@LT.true"+"."+j);//static variable j to differentiate labels
            Code.add("D;JLT");
            Code.add("@SP");
            Code.add("A=M-1");
            Code.add("M=0");
            Code.add("@LT.after"+"."+j);
            Code.add("0;JMP");
            Code.add("(LT.true"+"."+j+")");
            Code.add("@SP");
            Code.add("A=M-1");
            Code.add("M=-1");
            Code.add("(LT.after"+"."+j+")");
            j+=1;
        }
        else if (line.equals("gt"))
        {
            Code.add("D=M-D");
            Code.add("@GT.true"+"."+k);//static variable k to differentiate labels
            Code.add("D;JGT");
            Code.add("@SP");
            Code.add("A=M-1");
            Code.add("M=0");
            Code.add("@GT.after"+"."+k);
            Code.add("0;JMP");
            Code.add("(GT.true"+"."+k+")");
            Code.add("@SP");
            Code.add("A=M-1");
            Code.add("M=-1");
            Code.add("(GT.after"+"."+k+")");
            k+=1;
        }
        else if (line.equals("and")) {
            Code.add("M=M&D");
        }
        else if (line.equals("or")) {
            Code.add("M=M|D");
        }
        return Code;
    }

    public ArrayList<String> conditionHandler(String Line)//VM branching handler
    {
        ArrayList<String> Code = new ArrayList<>();

        String []args= Line.split(" ");//arg contains ['command' 'label']

        if(args[0].equals("label"))
        {
            Code.add("("+args[1]+")");
        }

        else if (args[0].equals("goto"))
        {
            Code.add("@"+args[1]);
            Code.add("0;JMP");
        }

        else if(args[0].equals("if-goto"))
        {
            Code.add("@SP");
            Code.add("M=M-1");
            Code.add("A=M");
            Code.add("D=M");
            Code.add("@"+args[1]);
            Code.add("D;JNE");
        }
        return Code;
    }


    public ArrayList<String> codeWriter(String Line, String Command,String filename)//Take the input line, command and filename.  Returns an arraylist with assembly code
    {
        ArrayList<String> Code = new ArrayList<>();

        if(Command.equals("c_arithmetic"))
        {
            Code = arithmeticHandler(Line);//Passes the line only because the line is the command
        }
        else if (Command.equals("c_conditional"))
        {
            Code = conditionHandler(Line); // Passes the line
        }
        else if(Command.equals("c_push") || Command.equals("c_pop"))
        {
            String[] args = Line.split(" ");
            Code = segmentBreaker(args[1]+" "+args[2], Command , filename); // Passes the segment name with no. and command and filename(for static handler)
        }
        return Code;//The produced code in the form of an arraylist is returned
    }
}
