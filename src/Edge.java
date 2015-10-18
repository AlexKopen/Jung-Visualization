//Class representing an endge value between two nodes
//
//@author Alex Kopen

import java.io.Serializable;

public class Edge implements Serializable{

    private final String start, end;

    public Edge(String start, String end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return this.start + ", " + this.end;
    }

    public boolean equals(Edge edge) {
        return (this.start == null ? edge.getStart() == null : 
                this.start.equals(edge.getStart())) 
                && (this.end == null ? edge.getEnd() == null : 
                this.end.equals(edge.getEnd()));

    }

    public String getStart() {
        return this.start;
    }

    public String getEnd() {
        return this.end;
    }

}
