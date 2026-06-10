package DaaPbl;

import java.util.*;

public class RoutingProtocoll {

    public static void buildRoutingTables(Network graph){
        for(Node source : graph.getallnodes()) {
            if(!(source instanceof router))
                continue;
            router r=(router) source;
            r.clearRoutingTable();
            for(Node dest : graph.getallnodes()) {
                if (dest == source)
                    continue;

                List<Node> path =Dijkstra.findPath(graph, source, dest);
                if (path.isEmpty())
                    continue;
                if(path.size()>1){
                    Node nextHop=path.get(1);
                    if (nextHop instanceof router){
                        router nextRouter=(router) nextHop;
                        if(!nextRouter.isActive())
                            continue;
       }
                      r.updateRtable(dest.getipadr(),nextHop);
                }}}}}
