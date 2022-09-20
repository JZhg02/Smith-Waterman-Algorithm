import jdk.swing.interop.SwingInterOpUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class Matrix {

    Character[][] M = new Character[12][12];

    Matrix(String str1, String str2){


        M[0][0] = 'S';
        for(int i=2; i<12; i++){
            M[0][i] = str1.charAt(i-2); // fill the 1st line starting from the 3rd column
        }
        for(int i=2; i<12; i++){
            M[i][0] = str2.charAt(i-2); // fill the 1st column starting from the 3rd line
        }
        // initialization of the 0s
        for(int i=0; i<12; i++){
            M[1][i] = '0';
        }
        for(int i=0; i<12; i++){
            M[i][1] = '0';
        }

    }

    // Search for max of 3 given values
    public int max(int x, int y, int z) {
        int max = Math.max(x, Math.max(y, z));
        if (max < 0)
            return(0); // return 0 if number is negative
        return(max);
    }

    // Algorithm that fill the table with the equations
    public void procedure() {
        int score = 0 ;
        for (int row = 2 ; row < 12 ; row++) {
            for (int col = 2 ; col < 12 ; col++) {
                if (M[0][col] == M[row][0])
                    score = 1 ;
                else
                    score = 0 ;
                // transform into char because M is an array of char
                M[row][col] = (char) max(Math.max(M[row-1][col-1]+score,0), Math.max(M[row-1][col]-2, -2), Math.max(M[row][col-1]-2, -2));
            }
        }
    }

    // TRACEBACK PART
    // Return traceback
    public String traceback(int row, int col) {
        StringBuilder traceBack = new StringBuilder();
        int i = row;
        int j = col;
        String valueUp;
        String valueDiag;
        String valueLeft;
        int max;
        traceBack.append(M[i][j]).append(" ");
        // Loop until we reach a 0
        while(i>1 && j>1 || M[i][j] != '0'){
            valueUp = String.valueOf(M[i][j-1]);
            valueDiag = String.valueOf(M[i-1][j-1]);
            valueLeft = String.valueOf(M[i-1][j]);
            max = max(Integer.parseInt(valueUp), Integer.parseInt(valueDiag), Integer.parseInt(valueLeft));
            if(max == Integer.parseInt(valueDiag)){
                traceBack.append(valueDiag).append(" ");
                i--;
                j--;
            }
            else if(max == Integer.parseInt(valueUp)){
                traceBack.append(valueUp).append(" ");
                j--;
            }
            else{
                traceBack.append(valueLeft).append(" ");
                i--;
            }
        }
        return(traceBack.toString());
    }
    // Get the highest value
    public int highestValue(){
        int max = 0;
        for(int i=2; i<12; i++){
            for(int j=2; j<12; j++){
                if(this.M[i][j] == ':'){
                    return(10);
                }
                else if(max < Integer.parseInt(String.valueOf(this.M[i][j]))){
                    max = Integer.parseInt(String.valueOf(this.M[i][j]));
                }
            }
        }
        return(max);
    }
    // Return ArrayList of the tracebacks
    public ArrayList<String> ATCG_Traceback() {
        ArrayList<String> tracebacksArray = new ArrayList<>();
        int matrixMax = highestValue();
        System.out.println("This is highest value: "+matrixMax);
        for(int row=2; row<12; row++){
            for(int col=2; col<12; col++){
                if(matrixMax == 10){
                    tracebacksArray.add(traceback(11, 11));
                    return(tracebacksArray);
                }
                else if(matrixMax == Integer.parseInt(String.valueOf(M[row][col]))){
                    tracebacksArray.add(traceback(row, col));
                }
            }
        }
        return(tracebacksArray);
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder(" ");
        for(int i=0; i<49; i++){
            str.append("-");
        }
        str.append("\n");
        for(int i=0; i<12; i++){
            for(int j=0; j<12; j++){
                str.append(" | "+M[i][j]);
            }
            str.append(" |");
            str.append("\n ");

            for(int k=0; k<49; k++){
                str.append("-");
            }
            str.append("\n");
        }
        return(str.toString());
    }
}
