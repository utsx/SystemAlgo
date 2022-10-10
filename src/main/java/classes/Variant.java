package classes;

import java.util.List;

public class Variant {

    private Long id;
    private String from;
    private String to;

    public Variant(Long id, String from, String to) {
        this.id = id;
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "Variant{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
