/*This class is to parse all the vm-code and remove the whitespaces
and to recognise what type of command each one is
* */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    public ArrayList<String> whitespace_handler(String inputfilename)//Handles whitespaces and add it to an arraylist
    {
        ArrayList<String> file_without_whitespaces = new ArrayList<>();

        try {
            BufferedReader reader=new BufferedReader(new FileReader(inputfilename));
            String line;
            while((line = reader.readLine()) != null)
            {
                line = line.trim();
                line = line.replace("\t","");

                if( line.startsWith("/") )
                {
                    continue;
                }
                else
                {
                    if( line.contains("/") )
                    {
                        line = line.substring( 0, line.indexOf("/") );
                        line = line.trim();
                    }
                }
                if(line.isEmpty())
                {
                    continue;
                }

                file_without_whitespaces.add(line);
            }
            reader.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return file_without_whitespaces;
    }

    public ArrayList<String> commandRecogniser(ArrayList<String> File) // Recognises what type of command each line is
    {
        ArrayList<String> Arithmetic = new ArrayList<>(List.of("add", "sub" , "neg","eq", "gt" , "lt","and", "or" , "not"));

        ArrayList<String> Branching = new ArrayList<>(List.of("label","goto","if-goto"));

        ArrayList<String> Commands = new ArrayList<>();

        for (String Line:File)
        {
            String []args = Line.split(" ");
            if(Line.contains("push"))
            {
                Commands.add("c_push");
            }

            else if (Line.contains("pop"))
            {
                Commands.add("c_pop");
            }

            else if (Arithmetic.contains(Line))
            {
                Commands.add("c_arithmetic");
            }

            else if (Branching.contains(args[0]))
            {
                Commands.add("c_conditional");
            }
        }
        return Commands;
    }


}
