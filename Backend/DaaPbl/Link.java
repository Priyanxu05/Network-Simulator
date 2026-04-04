package DaaPbl;

public class Link {
    private Node from;
    private Node to;
    private int cost;

    public Link(Node from, Node to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    public Node getFrom() {
        return from;
    }

    public Node getTo() {
        return to;
    }

    public int getCost() {
        return cost;
    }
}