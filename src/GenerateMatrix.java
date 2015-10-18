//Randomly generate a matrix with partitions
//
//@author Alex Kopen


import edu.wlu.cs.levy.CG.KeyDuplicateException;
import edu.wlu.cs.levy.CG.KeySizeException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import kopen.UI.JungGraph;
import kopen.load.ReadGraph;

public class GenerateMatrix {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, KeySizeException, KeyDuplicateException {

        //Location to save matrix result
        try (PrintWriter pw = new PrintWriter(new File("matrixresult.txt"))) {
            int limit = 6; //dimensions i.e. 4 is a 4x4 matrix
            
            //Variables for looping through matrix.  DO NOT CHANGE!
            int min = 1, max;
            int arrayPos = 1;

            //List representing where each partition lays
            ArrayList<Integer> partitionSpots = new ArrayList<>();

            //Limits for each partiton
            partitionSpots.add(3);
            partitionSpots.add(6);

            //Set initial max value
            max = partitionSpots.get(0);

            //Output 1's and 0's in appropriate locations
            for (int row = 0; row < limit; row++) {

                if (row > max) {
                    min = max + 1;
                    if (arrayPos < partitionSpots.size() - 1) {
                        max = partitionSpots.get(arrayPos);
                        arrayPos++;
                    } else {
                        max = limit;
                    }
                }

                for (int column = 0; column < limit; column++) {
                    if (row >= min - 1 && row <= max - 1) {
                        if (column >= min - 1 && column <= max - 1) {
                            pw.print(randomNumber());
                        } else {
                            pw.print("0");
                        }
                    } else {
                        if (max != limit) {
                            pw.print("0");
                        } else {
                            pw.print(randomNumber());
                        }

                    }

                    if (column != limit - 1) {
                        pw.print(" ");
                    }

                }
                if (row != limit - 1) {
                    pw.print("\n");
                }
            }

            pw.close();
        }

        //Create a new JUNG Graph
        JungGraph jg = new JungGraph(new File("matrixresult.txt"), "F:\\GUI\\generatedGraph");

        jg.run();

        //Read Graph
        ReadGraph rg = new ReadGraph("F:\\GUI\\generatedGraph\\result.txt");

    }

    //Method to output a random number
    public static String randomNumber() {
        Double randomNumber = Math.random();
        if (randomNumber > 0.5) {
            return ("1");
        } else {
            return ("0");
        }
    }

}
