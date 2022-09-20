import jdk.swing.interop.SwingInterOpUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String []args){

        int arrowCounter = 0; // to count the number of >
        try{
            String line;
            // File
            File file = new File("DNA_data.txt");
            // Create a file reader
            FileReader fr = new FileReader(file);
            // Create a buffered reader
            BufferedReader br = new BufferedReader(fr);
            while((line = br.readLine()) != null) { // will always read the next line
                // If the line is not empty
                if (!line.isEmpty()) {
                    // arrow = first character of the line
                    String arrow = line.charAt(0) + "";
                    if (arrow.equals(">")) {
                        arrowCounter++;
                        System.out.println(arrowCounter+". "+br.readLine()); // print the next line
                    }
                }
            }
            fr.close();
        }
        catch(IOException e){
            System.out.println("File does not exist.");
        }

        arrowCounter = 0;
        String line1 = null;
        String line2 = null;
        try {
            String line;
            // File
            File file = new File("DNA_data.txt");
            // Create a file reader
            FileReader fr = new FileReader(file);
            // Create a buffered reader
            BufferedReader br = new BufferedReader(fr);

            Scanner scan = new Scanner(System.in);
            // First sequence
            int lineNumber1 = 0;
            // Loop until user entered int between 0 and 10
            do{
                System.out.println("Please enter a number between 1 and 10 for the 1st sequence.");
                // Loop until user entered int
                while(!scan.hasNextInt()){
                    String str = scan.next();
                    System.out.println("Please enter a number between 1 and 10 for the 1st sequence.");
                }
                lineNumber1 = scan.nextInt();
            }while(lineNumber1>10 || lineNumber1<0);

            // Second sequence
            int lineNumber2 = 0;
            do{
                System.out.println("Please enter a number between 1 and 10 for the 2nd sequence.");
                while(!scan.hasNextInt()){
                    String str = scan.next();
                    System.out.println("Please enter a number between 1 and 10 for the 2nd sequence.");
                }
                lineNumber2 = scan.nextInt();
            }while(lineNumber2>10 || lineNumber2<0);

            // While we are not at the end of the file
            while((line = br.readLine()) != null){
                // If the line is not empty
                if(!line.isEmpty()){
                    // arrow = first character of the line
                    String arrow = line.charAt(0)+"";
                    if(arrow.equals(">")){
                        arrowCounter++;
                        if(arrowCounter == lineNumber1 && lineNumber1 == lineNumber2){
                            line1 = br.readLine();
                            line2 = line1;
                        }
                        else if(arrowCounter == lineNumber1){
                            line1 = br.readLine(); // next line
                        }
                        else if(arrowCounter == lineNumber2){
                            line2 = br.readLine();
                        }
                    }
                }
            }
            fr.close();
        }
        catch (IOException e){
            System.out.println("File does not exist.");
        }

        // Traceback Array
        ArrayList<String> tracebacks = new ArrayList<>();

        try {
            Matrix M = new Matrix(line1, line2); // creation of a matrix with the sequences/strings asked
            M.procedure();
            System.out.println(M);
            tracebacks = M.ATCG_Traceback();
            System.out.println(tracebacks);
        }
        catch (NullPointerException npe){
            System.out.println("Lines are null.");
        }
    }
}
