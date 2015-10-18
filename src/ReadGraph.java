//Class to construct JUNG Graph with partitions
//
//@author Alex Kopen

import edu.uci.ics.jung.graph.Graph;
import edu.wlu.cs.levy.CG.KeyDuplicateException;
import edu.wlu.cs.levy.CG.KeySizeException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import kopen.UI.Edge;

public class ReadGraph {

    //Initialize the list that holds all partitions
    private final ArrayList<ArrayList<Integer>> partitions = new ArrayList<>();

    public ReadGraph(String filePath) throws FileNotFoundException,
            IOException, ClassNotFoundException, KeySizeException, KeyDuplicateException {
        //Begin counting time to process
        long startTime = System.nanoTime();

        //Read graph object from the input file
        FileInputStream fin = new FileInputStream(filePath);
        Graph<Integer, Edge> g;
        try (ObjectInputStream ois = new ObjectInputStream(fin)) {
            g = (Graph<Integer, Edge>) ois.readObject();
        }
        //display the current graph
        kopen.UI.JungGraph.displayGraph(g, true);

        //Begin computing partitions
        //Create map to hold all processed values (nodes) which are initially
        // all set to false
        Map<Integer, Boolean> processedValues = new HashMap<>();
        Collection<Integer> vertices = g.getVertices();

        for (Integer currentVertex : vertices) {
            processedValues.put(currentVertex, Boolean.FALSE);
        }

        //Loop through all nodes and create a new partition with the spider 
        //algorithm to add to the list if the node has not already been
        //processed by the spider algorithm
        for (Map.Entry pairs : processedValues.entrySet()) {
            if (!processedValues.get((Integer) pairs.getKey())) {
                partitions.add(spider(g, (int) pairs.getKey(), processedValues));
            }
        }

        //Begin to count all edges per partition
        //Use hashset to hold edges to prevent duplicates
        HashSet<Integer> edgeCount = new HashSet<>();

        //Loop through all partitions in list
        for (ArrayList<Integer> currentPartion : this.partitions) {
            //Loop through all nodes in each partition and add edges to hashset
            for (Integer currentNode : currentPartion) {
                edgeCount.addAll((Collection<Integer>) (Collection<?>) g.getInEdges(currentNode));
                edgeCount.addAll((Collection<Integer>) (Collection<?>) g.getOutEdges(currentNode));
            }

            //Output edge sizes to console
            System.out.print(edgeCount.size() + " ");
            edgeCount.clear();
        }

        System.out.print("|");

        //End clock count and find entire time to process
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        double seconds = (double) duration / 1000000000.0;
        
        //Output total time to process, number of edges in graph, and total
        //number of partitions
        System.out.print(seconds + " ");
        System.out.print(g.getEdgeCount() + " ");
        System.out.print(this.partitions.size() + "\n");

    }

    public static ArrayList<Integer> spider(Graph<Integer, Edge> g,
            int currentVertex, Map<Integer, Boolean> processedValues) {
        //create lists that will be used.  the partitions returned, the current
        //vertexes being processed, and the temporay verted queue so the
        //iteration over the vertex queue isn't interfered with
        ArrayList<Integer> partition = new ArrayList<>();
        ArrayList<Integer> vertexQueue = new ArrayList<>();
        ArrayList<Integer> tempVertexQueue;

        //add vertex passed to partition as well as all of its successors and
        //predecessors to be processed.  This allows for any vertex in the 
        //partition to be passed to the method
        partition.add(currentVertex);
        processedValues.put(currentVertex, Boolean.TRUE);
        vertexQueue.addAll(g.getSuccessors(currentVertex));
        vertexQueue.addAll(g.getPredecessors(currentVertex));

        //loops through all vertexes in the queue until all of the vertexes as
        //well as their successors and predecessors have been processed
        while (true) {
            if (!vertexQueue.isEmpty()) {
                tempVertexQueue = new ArrayList<>();
                for (Integer currentSpideredVertex : vertexQueue) {
                    if (!processedValues.get(currentSpideredVertex)) {
                        partition.add(currentSpideredVertex);
                        processedValues.put(currentSpideredVertex, Boolean.TRUE);
                        tempVertexQueue.addAll(g.getSuccessors(currentSpideredVertex));
                        tempVertexQueue.addAll(g.getPredecessors(currentSpideredVertex));
                    }
                }

                vertexQueue.clear();

                for (Integer tempVertex : tempVertexQueue) {
                    if (!processedValues.get(tempVertex)) {
                        vertexQueue.add(tempVertex);
                    }
                }

            } else {
                break;
            }
        }

        return partition;
    }
}
