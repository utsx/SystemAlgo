package interfaces.searches;

import classes.City;
import classes.Graph;
import interfaces.Search;

import java.util.*;

public class ASA implements Search {

    private Map<String, Long> distances = new HashMap<>();

    Map<String, String> v = new HashMap<>();

    @Override
    public void search(Graph graph, String from, String to) {
        asa(graph, from, to);
        searchPath(from, to);
    }

    public void asa(Graph graph, String from, String to) {
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (int) (graph.getHeuristicMap().get(o1) + distances.getOrDefault(o1, 0L)
                                        - (graph.getHeuristicMap().get(o2) + distances.getOrDefault(o2, 0L)));
            }
        };

        PriorityQueue<String> queue = new PriorityQueue<>(comparator);

        graph.getVisited().put(from, true);
        queue.add(from);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            for (City city : graph.getEdges(current)) {
                if (!graph.getVisited().get(city.getName())) {
                    graph.getVisited().put(city.getName(), true);
                    distances.put(city.getName(), distances.getOrDefault(current, 0L) + city.getDistance());
                    System.out.println(current + " -> " + city.getName() + " : " + distances.get(city.getName()));
                    queue.add(city.getName());
                    v.put(city.getName(), current);
                }
                if (city.getName().equals(to)) {
                    return;
                }
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
                System.out.print(current + " <- " + distances.get(current) + " <- ");
                current = v.get(current);
            }
            System.out.println(from + " : " + distances.get(to));
        }
        return true;
    }
}
