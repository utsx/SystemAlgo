import classes.Variant;
import classes.Graph;
import interfaces.Search;
import interfaces.searches.*;
import readers.MyCSVReader;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {


    static Path pathData = Paths.get("src", "main", "resources", "data.csv");
    static Path pathVariant = Paths.get("src", "main", "resources", "variant.csv");
    static Path pathHeuristic = Paths.get("src", "main", "resources", "distance.csv");
    static private final Map<Long, Variant> variantMap = new HashMap<>();


    static private final Map<String, Search> searchMap = new HashMap<>();
    static private final MyCSVReader myCSVReader = new MyCSVReader();


    public static void main(String[] args) {
        run();
    }


    public static void run(){
        Graph graph = new Graph(myCSVReader.readCSV(pathData), myCSVReader.readCSV(pathHeuristic));
        createVariantMap();
        //Scanner scanner = new Scanner(System.in);
        long variant = 2L;
        Variant variant1 = variantMap.get(variant);
        System.out.println(variant1.getFrom() + " -> " + variant1.getTo());
        System.out.println();
        startAllSearches(graph, variant1.getFrom(), variant1.getTo());
    }

    public static void createMap(){
        searchMap.put("BFS", new BFS());
        searchMap.put("DFS", new DFS());
        searchMap.put("DLS", new DLS());
        searchMap.put("IDS", new IDS());
        searchMap.put("BS", new BS());
        searchMap.put("BestFS", new BestFS());
        searchMap.put("ASA", new ASA());
    }

    public static void startAllSearches(Graph graph, String from, String to) {
            createMap();
            searchMap.forEach((key, value) -> {
                System.out.println(key);
                value.search(graph, from, to);
                graph.clear();
                System.out.println();
            });
    }


    public static void createVariantMap() {
        List<String[]> list = myCSVReader.readCSV(pathVariant);
        for (String[] strings : list) {
            variantMap.put(Long.parseLong(strings[0]), new Variant(Long.parseLong(strings[0]), strings[1], strings[2]));
        }
    }
}
