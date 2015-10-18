package artronics.chapar.gui;

import org.jgraph.JGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.ListenableUndirectedWeightedGraph;

import javax.swing.*;
import java.awt.*;

public class GraphGui<N, L> extends JApplet
{
    private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);
    private static final Color DEFAULT_BG_COLOR = Color.decode("#FAFBFF");

    private final ListenableUndirectedWeightedGraph<N, L> graph;

    private JGraphModelAdapter<N, L> jgAdapter;

    public GraphGui(ListenableUndirectedWeightedGraph<N, L> graph) throws HeadlessException
    {
        this.graph = graph;
    }

    public void start()
    {
        GraphGui applet = new GraphGui(graph);
        applet.init();

        JFrame frame = new JFrame();
        frame.getContentPane().add(applet);
        frame.setTitle("kirrrrr");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    public void init()
    {
//        ListenableUndirectedWeightedGraph<N,L> g = new ListenableUndirectedWeightedGraph(L);

        // create a visualization using JGraph, via an adapter
        jgAdapter = new JGraphModelAdapter<N, L>(graph);
        JGraph jgraph = new JGraph(jgAdapter);
        adjustDisplaySettings(jgraph);
        getContentPane().add(jgraph);
        resize(DEFAULT_SIZE);
    }

    private void adjustDisplaySettings(JGraph jg)
    {
        jg.setPreferredSize(DEFAULT_SIZE);

        Color c = DEFAULT_BG_COLOR;
        String colorStr = null;

        try {
            colorStr = getParameter("bgcolor");
        }catch (Exception e) {
        }

        if (colorStr != null) {
            c = Color.decode(colorStr);
        }

        jg.setBackground(c);
    }
}
