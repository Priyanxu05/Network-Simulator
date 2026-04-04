package DaaPbl;

import java.util.*;

public class Network{

    private Map<Node,List<Link>> adjlist;

    public Network(){
        adjlist = new HashMap<>();}

    public void addnode(Node node) {
        adjlist.putIfAbsent(node,new ArrayList<>());
    }

    public void connect(Node n1,Node n2,int cost){
        adjlist.get(n1).add(new Link(n1,n2, cost));
        adjlist.get(n2).add(new Link(n2,n1, cost));
    }

    public List<Node> getneighbors(Node node) {
        List<Node>neighbors=new ArrayList<>();
        for (Link l : adjlist.get(node)){
            neighbors.add(l.getTo());
        }
        return neighbors;
    }

    public Set<Node> getallnodes() {
        return adjlist.keySet();
    }

    public int getCost(Node from,Node to) {
        for (Link l : adjlist.get(from)) {
            if (l.getTo()==to) {
                return l.getCost();
            }
        }
        return Integer.MAX_VALUE;
    }
}