package DaaPbl;

import com.sun.net.httpserver.*;
import java.io.*;
import java.net.InetSocketAddress;

public class Server {

    public static void main(String[] args) throws Exception {

        HttpServer server =HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/run", exchange -> {

        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Content-Type", "text/plain");

        String response = runSimulation();

        exchange.sendResponseHeaders(200, response.length());

        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();});

        server.setExecutor(null);
        server.start();

        System.out.println("Server running at http://localhost:8000");
    }

    public static String runSimulation() {

        login.clear();

        router r1 = new router("R1", "192.168.1.1");
        router r2 = new router("R2", "192.168.1.2");
        router r3 = new router("R3", "192.168.1.3");
        host h1 = new host("H1", "192.168.1.10");
        host h2 = new host("H2", "192.168.1.20");
        Network graph = new Network();
        graph.addnode(r1);
        graph.addnode(r2);
        graph.addnode(r3);
        graph.addnode(h1);
        graph.addnode(h2);
        graph.connect(r1, r2, 2);
        graph.connect(r2, r3, 3);
        graph.connect(h1, r1, 1);
        graph.connect(h2, r3, 1);
        r1.connect(r2);
        r2.connect(r1);
        r2.connect(r3);
        r3.connect(r2);
        h1.connect(r1);
        r1.connect(h1);
        h2.connect(r3);
        r3.connect(h2);
        RoutingProtocoll.buildRoutingTables(graph);
        login.info("Routing tables built");
       
        r2.disable();
        login.info("Sending packet...");
        h1.sendpak(new paket(h1.getipadr(), h2.getipadr(), "Hello from UI",10));
        return login.getLogs();
    }
}