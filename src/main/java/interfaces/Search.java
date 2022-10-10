package interfaces;

import classes.Graph;

import java.util.Map;

public interface Search {
    void search(Graph graph, String from, String to);

    boolean searchPath(String from, String to);
}

