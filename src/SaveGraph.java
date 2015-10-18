//Save a graph to a given location
//
//@author Alex Kopen


import edu.uci.ics.jung.graph.Graph;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveGraph{

    public SaveGraph(Graph<Integer, Edge> g, String savePath) 
            throws FileNotFoundException, IOException {
        FileOutputStream fout = new FileOutputStream(savePath+"/result.txt");
        try (ObjectOutputStream oos = new ObjectOutputStream(fout)) {
            oos.writeObject(g);
        }
    }

}
