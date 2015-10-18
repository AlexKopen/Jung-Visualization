//An object representing an individual node in the KD-Tree
//
//@author Alex Kopen

import java.util.ArrayList;

public class Node {

    //Fields for each node.  Every node contains an id, a partition id
    //representing which partition the node is in, and a list of connections
    //which the node is linked to
    private int id;
    private int partitionID;
    private ArrayList<Integer> connections;

    //Constructor
    public Node(int id, int partitionID, ArrayList<Integer> connections) {
        this.id = id;
        this.partitionID = partitionID;
        this.connections = connections;
    }

    //All getters and setters for private variables
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPartitionID() {
        return partitionID;
    }

    public void setPartitionID(int partitionID) {
        this.partitionID = partitionID;
    }

    public ArrayList<Integer> getConnections() {
        return connections;
    }

    public void setConnections(ArrayList<Integer> connections) {
        this.connections = connections;
    }

}
