package DaaPbl;

import java.util.*;

public class RoutingProtocoll {

    public static void buildRoutingTables(Network graph) {

        for (Node source : graph.getallnodes()) {

            if (!(source instanceof router)) continue;
            router r = (router) source;

            Map<Node, Integer> dist = new HashMap<>();
            Map<Node, Node> prev = new HashMap<>();

            Queue<Node> queue = new LinkedList<>();

            for (Node node : graph.getallnodes()) {
                dist.put(node, Integer.MAX_VALUE);
            }

            dist.put(source, 0);
            queue.add(source);

            while (!queue.isEmpty()) {
                Node current = queue.poll();

                for (Node neighbor : graph.getneighbors(current)) {

                    if (dist.get(neighbor) == Integer.MAX_VALUE) {
                        dist.put(neighbor, dist.get(current) + 1);
                        prev.put(neighbor, current);
                        queue.add(neighbor);
                    }
                }
            }
            for (Node dest : graph.getallnodes()) {

                if (dest == source) continue;

                Node nextHop = getNextHop(source, dest, prev);

                if (nextHop != null) {
                    r.updateRtable(dest.getipadr(), nextHop);
                }
            }
        }
    }

    private static Node getNextHop(Node source, Node dest, Map<Node, Node> prev) {

        Node current = dest;
        Node previous = null;

        while (current != null && current != source) {
            previous = current;
            current = prev.get(current);
        }

        return previous;
    }
}
