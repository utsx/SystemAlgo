package classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Graph {

    private Map<String, List<City>> adjCities;

    private Map<String, Boolean> visited;

    Map<String, Long> heuristicMap;

    public Graph(List<String[]> list, List<String[]> heuristic) {
        adjCities = new HashMap<>();
        visited = new HashMap<>();
        heuristicMap = heuristic.stream().collect(Collectors.toMap(s -> s[1], s -> Long.parseLong(s[2])));
        list.forEach(strings -> {
            addCity(strings[0]);
            visited.put(strings[0], false);
            addCity(strings[1]);
            visited.put(strings[1], false);
        });
        list.forEach(strings -> addEdge(strings[0], strings[1], Long.parseLong(strings[2]), heuristicMap.get(strings[1])));
    }


    public void addEdge(String city1, String city2, Long distance, Long heuristic) {
        adjCities.get(city1).add(new City(city2, distance, heuristic));
        adjCities.get(city2).add(new City(city1, distance, heuristic));
    }

    public void addCity(String city) {
        adjCities.put(city, new ArrayList<>());
    }

    public Map<String, Boolean> getVisited() {
        return visited;
    }

    public Long adjSize(){
        return (long) adjCities.keySet().size();
    }

    public Long getDistance(String from, String to) {
        return adjCities.get(from).stream().filter(city -> city.getName().equals(to)).findFirst().get().getDistance();
    }

    public List<City> getEdges(String city) {
        return adjCities.get(city);
    }

    public void clear(){
        visited.forEach((key, value) -> visited.put(key, false));
    }


    public Map<String, Long> getHeuristicMap() {
        return heuristicMap;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "adjCities=" + adjCities +
                '}';
    }
}

