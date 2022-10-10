package interfaces.searches;

import classes.City;
import classes.Graph;
import interfaces.Search;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


//Depth First Search
public class DFS implements Search {

    private Map<String, String> v = new HashMap<>();

    @Override
    public void search(Graph graph, String from, String to) {
        dfs(graph, from, to);
        searchPath(from, to);
    }

    private void dfs(Graph graph, String from, String to){
        graph.getVisited().put(from, true);
        for(City city : graph.getEdges(from)){
            if (!graph.getVisited().get(city.getName())){
                v.put(city.getName(), from);
                System.out.println(from + " -> " + city.getName());
                dfs(graph, city.getName(), to);
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

