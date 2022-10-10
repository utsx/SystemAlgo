package interfaces.searches;

import classes.City;
import classes.Graph;
import interfaces.Search;
import org.checkerframework.checker.units.qual.C;

import java.util.*;


//Bidirectional search
public class BS implements Search {

    private final Queue<String> startQueue = new LinkedList<>();
    private final Queue<String> endQueue = new LinkedList<>();

    private final Map<String, String> v = new HashMap<>();

    private final Map<String, Long> visited = new HashMap<>();

    @Override
    public void search(Graph graph, String from, String to) {
        visited.putAll(graph.getVisited().keySet().stream().collect(HashMap::new, (m, k) -> m.put(k, 0L), Map::putAll));
        bs(graph, from, to);
        searchPath(from, to);
    }

    private void bs(Graph graph, String from, String to) {
        startQueue.add(from);
        endQueue.add(to);
        visited.put(from, 1L);
        visited.put(to, 2L);
        int counter = 0;
        while (!endQueue.isEmpty() || !startQueue.isEmpty()) {
            String current = startQueue.poll();
            for (City city : graph.getEdges(current)) {
                if (visited.get(city.getName()) == 0) {
                    v.put(city.getName(), current);
                    System.out.println(current + " -> " + city.getName());
                    visited.put(city.getName(), 1L);
                    startQueue.add(city.getName());
                } else if (visited.get(city.getName()) == 2L) {
                    v.put(city.getName(), current);
                    System.out.println(current + " -> " + city.getName());
                    counter = 1;
                }
            }
            current = endQueue.poll();
            for (City city : graph.getEdges(current)) {
                if (visited.get(city.getName()) == 0) {
                    System.out.println(current + " <- " + city.getName());
                    v.put(current, city.getName());
                    visited.put(city.getName(), 2L);
                    endQueue.add(city.getName());
                } else if (visited.get(city.getName()) == 1L) {
                    v.put(city.getName(), current);
                    System.out.println(current + " <- " + city.getName());
                    counter = 1;
                }
            }
            if (counter == 1) {
                return;
            }
        }

    }

    @Override
    public boolean searchPath(String from, String to) {
        if (!v.containsKey(to)) {
            System.out.println("There is no path from " + from + " to " + to);
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
        return true;
    }
}
