//Simulates the KD-Tree as seen on the first page of this PDF:
//http://web.stanford.edu/class/cs106l/handouts/assignment-3-kdtree.pdf
//
//@author Alex Kopen


//All imports from kd.jar
//http://home.wlu.edu/~levys/software/kd/
import edu.wlu.cs.levy.CG.KDTree;
import edu.wlu.cs.levy.CG.KeyDuplicateException;
import edu.wlu.cs.levy.CG.KeySizeException;

public class KDSimulation {

    public static void main(String[] args) {

        //Create multiple 3-dimensional nodes
        double[] A = {3, 1, 4};
        double[] B = {2, 3, 7};
        double[] C = {4, 3, 4};
        double[] D = {2, 1, 3};
        double[] E = {2, 4, 5};
        double[] F = {6, 1, 4};
        double[] G = {1, 4, 4};
        double[] H = {0, 5, 7};
        double[] I = {5, 2, 5};
        double[] J = {4, 0, 6};
        double[] K = {7, 1, 6};

        //Initialize a 3-dimensional KD-Tree
        KDTree<String> kd = new KDTree<>(3);
        try {
            //Insert all nodes into tree with a lookup value
            kd.insert(A, "3, 1, 4");
            kd.insert(B, "2, 3, 7");
            kd.insert(C, "4, 3, 4");
            kd.insert(D, "2, 1, 3");
            kd.insert(E, "2, 4, 5");
            kd.insert(F, "6, 1, 4");
            kd.insert(G, "1, 4, 4");
            kd.insert(H, "0, 5, 7");
            kd.insert(I, "5, 2, 5");
            kd.insert(J, "4, 0, 6");
            kd.insert(K, "7, 1, 6");

        } catch (KeySizeException | KeyDuplicateException e) {
            System.err.println(e);
        }

        try {
            //Example of searching for node 'A'
            String n = kd.search(A);
            System.out.println("Search results for key 'A': " + n);

        } catch (KeySizeException e) {
            System.err.println(e);
        }

    }

}
