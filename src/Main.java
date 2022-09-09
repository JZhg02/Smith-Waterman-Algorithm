import java.io.*;

public class Main {
    public static void main(String []args){

        int lineNumber = 4;

        int arrowCounter = 0;

        try{
            // File
            File file = new File("DNA_data.txt");
            // Create a file reader
            FileReader fr = new FileReader(file);
            // Create a buffered reader
            BufferedReader br = new BufferedReader(fr);

            String line;
            // While we are not at the end of the file
            while((line = br.readLine()) != null){
                // If the line is not empty
                if(!line.isEmpty()){
                    // arrow = first character of the line
                    String arrow = line.charAt(0)+"";
                    if(arrow.equals(">")){
                        arrowCounter++;
                        if(arrowCounter == lineNumber){
                            System.out.println(br.readLine());
                        }
                    }
                }
            }
            fr.close();
        }
        catch(IOException e){
            System.out.println("File does not exist.");
        }

        Matrix M = new Matrix("GTCGATTTGA", "ACGAAAGAGG");
        System.out.println(M);
    }
}
