package DaaPbl;

import com.sun.net.httpserver.*;
import java.io.*;
import java.net.InetSocketAddress;

public class Server {

    static router r1;
    static router r2;
    static router r3;
    static router r4;
    static router r5;
    static router r6;
    static router r7;
        static router r8;
        static router r9;
        static router r10;
        static router r11;
        static router r12;

    static host h1;
    static host h2;

    static Network graph;

    public static void main(String[] args) throws Exception {

        initializeNetwork();

        HttpServer server = HttpServer.create(
                new InetSocketAddress(8000),
                0
        );

        server.createContext("/run", exchange -> {

            exchange.getResponseHeaders().add(
                    "Access-Control-Allow-Origin",
                    "*"
            );

            exchange.getResponseHeaders().add(
                    "Content-Type",
                    "text/plain"
            );

            login.clear();

            login.header("UI NETWORK SIMULATION");

            graph.showTopology();

            r1.showRoutingTable();
            r2.showRoutingTable();
            r3.showRoutingTable();
            r4.showRoutingTable();
            r5.showRoutingTable();

            for (int i = 1; i <= 3; i++) {

                paket pkt = new paket(
                        h1.getipadr(),
                        h2.getipadr(),
                        "UI Packet " + i,
                        10
                );

                login.line();

                login.info(
                        "PACKET #" + pkt.getPacketId()
                );

                login.info(
                        "Source : " + pkt.getsource()
                );

                login.info(
                        "Destination : " + pkt.getdest()
                );

                login.info(
                        "Data : " + pkt.getdata()
                );

                h1.sendpak(pkt);

                try {

                    Thread.sleep(800);

                }
                catch (InterruptedException e) {

                    e.printStackTrace();
                }
            }

            login.header("SIMULATION COMPLETE");

            String response = login.getLogs();

            exchange.sendResponseHeaders(
                    200,
                    response.length()
            );

            OutputStream os = exchange.getResponseBody();

            os.write(response.getBytes());

            os.close();
        });

        server.createContext("/disableR5", exchange -> {

            r5.disable();

            RoutingProtocoll.buildRoutingTables(graph);

            login.clear();

            login.header("ROUTER FAILURE");

            login.error("Router R5 Disabled");

            graph.showTopology();

            r1.showRoutingTable();
            r3.showRoutingTable();
            r4.showRoutingTable();
            r5.showRoutingTable();

            String response = login.getLogs();

            exchange.sendResponseHeaders(
                    200,
                    response.length()
            );

            OutputStream os = exchange.getResponseBody();

            os.write(response.getBytes());

            os.close();
        });

        server.createContext("/enableR5", exchange -> {

            r5.enable();

            RoutingProtocoll.buildRoutingTables(graph);

            login.clear();

            login.header("NETWORK RECOVERY");

            login.success("Router R5 Enabled");

            graph.showTopology();

            r1.showRoutingTable();
            r2.showRoutingTable();
            r3.showRoutingTable();
            r4.showRoutingTable();
            r5.showRoutingTable();

            String response = login.getLogs();

            exchange.sendResponseHeaders(
                    200,
                    response.length()
            );

            OutputStream os = exchange.getResponseBody();

            os.write(response.getBytes());

            os.close();
        });

        server.setExecutor(null);

        server.start();

        System.out.println(
                "Server running at http://localhost:8000"
        );

        server.createContext("/disableR6", exchange -> {

    r6.disable();

    RoutingProtocoll.buildRoutingTables(graph);

    String response = "Router R6 Disabled";

    exchange.sendResponseHeaders(
        200,
        response.length()
    );

    OutputStream os =
        exchange.getResponseBody();

    os.write(response.getBytes());

    os.close();
});
    }


    public static void initializeNetwork() {

        r1 = new router("R1", "192.168.1.1");
        r2 = new router("R2", "192.168.1.2");
        r3 = new router("R3", "192.168.1.3");
        r4 = new router("R4", "192.168.1.4");
        r5 = new router("R5", "192.168.1.5");
        r6 = new router("R6","192.168.1.6");
        r7 = new router("R7","192.168.1.7");
        r8 = new router("R8","192.168.1.8");
        r9 = new router("R9","192.168.1.9");
        r10 = new router("R10","192.168.1.10");
        r11 = new router("R11","192.168.1.11");
        r12 = new router("R12","192.168.1.12");

        h1 = new host("H1", "192.168.1.10");
        h2 = new host("H2", "192.168.1.20");

        graph = new Network();

        graph.addnode(r1);
        graph.addnode(r2);
        graph.addnode(r3);
        graph.addnode(r4);
        graph.addnode(r5);
        graph.addnode(r6);
        graph.addnode(r7);
        graph.addnode(r8);
        graph.addnode(r9);
        graph.addnode(r10);
        graph.addnode(r11);
        graph.addnode(r12);

        graph.addnode(h1);
        graph.addnode(h2);

        graph.connect(r1,r2,2);
        graph.connect(r2,r3,2);
        graph.connect(r3,r4,2);
        graph.connect(r4,r5,2);
        graph.connect(r5,r8,2);
        graph.connect(r8,r9,2);
        graph.connect(r9,r10,2);
        graph.connect(r4,r6,1);
        graph.connect(r6,r7,1);
        graph.connect(r7,r8,1);
        graph.connect(r2,r11,3);
        graph.connect(r11,r12,2);
        graph.connect(r12,r8,2);
        graph.connect(h1,r1,1);
        graph.connect(h2,r10,1);

       r1.connect(r2);
r2.connect(r1);

r2.connect(r3);
r3.connect(r2);

r3.connect(r4);
r4.connect(r3);

r4.connect(r5);
r5.connect(r4);

r5.connect(r8);
r8.connect(r5);

r8.connect(r9);
r9.connect(r8);

r9.connect(r10);
r10.connect(r9);

r4.connect(r6);
r6.connect(r4);

r6.connect(r7);
r7.connect(r6);

r7.connect(r8);
r8.connect(r7);

r2.connect(r11);
r11.connect(r2);

r11.connect(r12);
r12.connect(r11);

r12.connect(r8);
r8.connect(r12);

h1.connect(r1);
r1.connect(h1);

h2.connect(r10);
r10.connect(h2);

        RoutingProtocoll.buildRoutingTables(graph);
    }
    
}
