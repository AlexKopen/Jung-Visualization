//Class to create a JUNG graph object
//
//@author Alex Kopen


import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;

public class JungGraph {

    private final File f;
    private final String savePath;

    public JungGraph(File f, String savePath) {
        this.f = f;
        this.savePath = savePath;
    }

    public void run() throws FileNotFoundException, IOException {
        Graph<Integer, Edge> g;
        g = new SparseMultigraph<>();

        generateGraph(g, this.f);
        //displayGraph(g, true);

        SaveGraph saveGraph = new SaveGraph(g, this.savePath);
    }

    private void generateGraph(Graph<Integer, Edge> g, File f)
            throws FileNotFoundException {
        int currentRow, currentColumn;

        ArrayList<String> lines;
        String[] currentLineArray;
        try (Scanner sc = new Scanner(f)) {
            currentRow = 1;
            currentColumn = 1;
            lines = new ArrayList<>();
            while (sc.hasNext()) {
                lines.add(sc.nextLine());
            }
        }

        for (String line : lines) {
            g.addVertex(currentRow);
            currentLineArray = line.split("\\s+");
            for (String currentLineValue : currentLineArray) {
                if (currentLineValue.equals("1")) {
                    g.addEdge(new Edge(Integer.toString(currentRow),
                            Integer.toString(currentColumn)), currentRow,
                            currentColumn, EdgeType.DIRECTED);
                }
                currentColumn++;
            }

            currentColumn = 1;

            currentRow++;
        }
    }

    public static final void displayGraph(Graph<Integer, Edge> g, boolean labels) {
        Layout<Integer, Edge> layout = new KKLayout<>(g);
        layout.setSize(new Dimension(500, 500));
        VisualizationViewer<Integer, Edge> vv = new VisualizationViewer<>(
                layout);
        vv.setPreferredSize(new Dimension(500, 500));
        if (labels) {
            vv.getRenderContext().setVertexLabelTransformer(
                    new ToStringLabeller<Integer>());
            vv.getRenderContext().setEdgeLabelTransformer(
                    new ToStringLabeller<Edge>());
        }
        DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
        gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
        vv.setGraphMouse(gm);
        JFrame frame = new JFrame("Data Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
