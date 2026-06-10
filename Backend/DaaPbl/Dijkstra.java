package DaaPbl;

import java.util.*;

public class Dijkstra {

    public static List<Node> findPath(Network graph, Node source, Node destination){

        Map<Node,Integer> dist =new HashMap<>();
        Map<Node,Node> prev =new HashMap<>();
        PriorityQueue<Node> pq =new PriorityQueue<>(Comparator.comparingInt(dist::get));
        for(Node node : graph.getallnodes()){
            dist.put(node,Integer.MAX_VALUE);
        }
        dist.put(source,0);
        pq.add(source);
        while(!pq.isEmpty()) {
            Node current =pq.poll();
            login.info("Visiting node: " + current.getid());

            if(current==destination) 
                break;

            for (Node neighbor : graph.getneighbors(current)) {
                if (neighbor instanceof router) {
                     router r = (router) neighbor;
                    if (!r.isActive())
                        continue; }
                    
                int newDist=dist.get(current)+graph.getCost(current,neighbor);
                if (newDist<dist.get(neighbor)){
                login.info("Updating path: "+ current.getid()+ " -> "+ neighbor.getid());
                dist.put(neighbor,newDist);
                prev.put(neighbor,current);
                pq.add(neighbor);
    }
}
        }
        List<Node> path=new ArrayList<>();
        Node curr=destination;
        while(curr!=null) {
            path.add(curr);
            curr =prev.get(curr);
        }
        Collections.reverse(path);
        return path;
    }
}
