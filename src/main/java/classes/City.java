package classes;

public class City {

    private String name;
    private Long distance;


    public City(String name) {
        this.name = name;
        this.distance = 0L;
    }


    public City(String name, Long distance) {
        this.name = name;
        this.distance = distance;
    }

    public City(String name, Long distance, Long heuristic) {
        this.name = name;
        this.distance = distance;
    }

    public Long getDistance() {
        return distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", distance=" + distance +
                '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((City) obj).getName());
    }
}
