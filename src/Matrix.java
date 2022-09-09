import java.util.ArrayDeque;
import java.util.ArrayList;

public class Matrix {

    Character[][] M = new Character[12][12];

    Matrix(String str1, String str2){

        for(int i=0; i<12; i++){
            for(int j=0; j<12; j++){
                M[i][j] = '0';
            }
        }
        M[0][0] = 'S';
        for(int i=2; i<12; i++){
            M[i][0] = str1.charAt(i-2);
        }
        for(int i=2; i<12; i++){
            M[0][i] = str2.charAt(i-2);
        }

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
