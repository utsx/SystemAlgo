package interfaces.searches;

import classes.City;
import classes.Graph;
import interfaces.Search;

import java.util.*;


//Breadth-first search
public class BFS implements Search {

    Map<String, String> v = new HashMap<>();
    Queue<String> queue = new LinkedList<>();
    @Override
    public void search(Graph graph, String from, String to) {
        bfs(graph, from, to);
        searchPath(from, to);
    }

    public void bfs(Graph graph, String from, String to){
        queue.add(from);
        graph.getVisited().put(from, true);
        v.put(from, null);
        while (!queue.isEmpty()){
            String current = queue.poll();
            for (City city : graph.getEdges(current)){
                if (!graph.getVisited().get(city.getName())){
                    System.out.println(current + " -> " + city.getName());
                    graph.getVisited().put(city.getName(), true);
                    queue.add(city.getName());
                    v.put(city.getName(), current);
                }
                if(city.getName().equals(to)){
                    return;
                }
            }
        }
    }

    @Override
    public boolean searchPath(String from, String to){
        if(!v.containsKey(to)){
            System.out.println("There is no path from " + from + " to " + to);
            return false;
        }
        else{
            System.out.println();
            System.out.println();
            System.out.println("You have reached your destination");
            System.out.println("Path: ");
            String current = to;
            while (!Objects.equals(current, from)){
                System.out.print(current + " <- ");
                current = v.get(current);
            }
            System.out.println(from);
        }
        return true;
    }
}
