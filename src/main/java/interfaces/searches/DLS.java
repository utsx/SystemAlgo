package interfaces.searches;

import classes.City;
import classes.Graph;
import interfaces.Search;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DLS implements Search {

    private final Map<String, String> v = new HashMap<>();

    private int buffer = 1;

    @Override
    public void search(Graph graph, String from, String to) {

        System.out.println("Depth limit: " + 8);
        dls(graph, from, 1, 8);
        searchPath(from, to);
    }

    public void dls(Graph graph, String from, int currentDepth, int maxDepth) {
        if (maxDepth == currentDepth) {
            return;
        }
        graph.getVisited().put(from, true);
        for (City city : graph.getEdges(from)) {
            if (!graph.getVisited().get(city.getName())) {
                v.put(city.getName(), from);
                System.out.println(from + " -> " + city.getName());
                dls(graph, city.getName(), currentDepth + 1, maxDepth);
            }
        }
    }

    @Override
    public boolean searchPath(String from, String to) {
        if (!v.containsKey(to)) {
            System.out.println("There is no path from " + from + " to " + to + " with depth " + this.buffer + "\n");
            return false;
        } else {
            System.out.println();
            System.out.println();
            System.out.println("You have reached your destination");
            System.out.println("Path: ");
            String current = to;
            while (!Objects.equals(current, from)) {
                System.out.print(current + " <- ");
                current = v.get(current);
            }
            System.out.println(from);
        }
        System.out.println();
        return true;
    }
}
