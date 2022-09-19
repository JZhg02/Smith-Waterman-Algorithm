import jdk.swing.interop.SwingInterOpUtils;

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

    public int max(int x, int y, int z) {
        int max = Math.max(x, Math.max(y, z));
        if (max < 0)
            return(0); // return 0 if number is negative
        return(max);
    }

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

    public int rowMax(){
        int max = Integer.parseInt(String.valueOf(M[2][2]));
        int theRow = 11;
        for(int row=2; row<12; row++){
            for(int col=2; col<12; col++){
                if(M[row][col] == ':'){
                    max = 10;
                    theRow = 11;
                }
                else if(max <= Integer.parseInt(String.valueOf(M[row][col]))){
                    max = Integer.parseInt(String.valueOf(M[row][col]));
                    theRow = row;
                }
            }
        }
        return(theRow);
    }
    public int colMax(){
        int max = Integer.parseInt(String.valueOf(M[2][2]));
        int theCol = 11;
        for(int row=2; row<12; row++){
            for(int col=2; col<12; col++){
                if(M[row][col] == ':'){
                    max = 10;
                    theCol = 11;
                }
                else if(max <= Integer.parseInt(String.valueOf(M[row][col]))){
                    max = Integer.parseInt(String.valueOf(M[row][col]));
                    theCol = col;
                }
            }
        }
        return(theCol);
    }

    public String traceback() {
        StringBuilder traceBack = new StringBuilder("This is one of the possible tracebacks: ");
        int i = rowMax();
        int j = colMax();
        String valueUp;
        String valueDiag;
        String valueLeft;
        int max;
        traceBack.append(M[i][j]).append(" ");
        while(i>2 && j>2){
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

    /*
    // TO MODIFY
    public String ATCG_Traceback() {
        StringBuilder traceBack = new StringBuilder("");
        int i = rowMax();
        int j = colMax();
        String valueUp;
        String valueDiag;
        String valueLeft;
        int max;
        traceBack.append(M[i][j]).append(" ");
        while(i>2 && j>2){
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
    }*/

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
