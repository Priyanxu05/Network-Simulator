package DaaPbl;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        login.header("NETWORK INITIALIZATION");
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
        login.success("Routing tables built successfully");

        login.header("PHASE 1: ROUTER FAILURE TEST");

        r2.disable();

        List<paket> packets = new ArrayList<>();

        packets.add(new paket(h1.getipadr(), h2.getipadr(), "Message 1", 10));
        packets.add(new paket(h1.getipadr(), h2.getipadr(), "Message 2", 10));
        packets.add(new paket(h1.getipadr(), h2.getipadr(), "Message 3", 10));

        int count = 1;

        for (paket pkt : packets) {

            login.line();
            login.info("PACKET " + count);
            login.info("Source: " + pkt.getsource());
            login.info("Destination: " + pkt.getdest());
            login.info("Data: " + pkt.getdata());

            h1.sendpak(pkt);

            count++;

            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        login.header("PHASE 2: RECOVERY TEST");

        r2.enable();

        paket recoveryPacket = new paket(
                h1.getipadr(),
                h2.getipadr(),
                "Recovery Message",
                10
        );

        login.line();
        login.info("PACKET (RECOVERY)");
        login.info("Source: " + recoveryPacket.getsource());
        login.info("Destination: " + recoveryPacket.getdest());
        login.info("Data: " + recoveryPacket.getdata());

        h1.sendpak(recoveryPacket);

        login.header("SIMULATION COMPLETE");
    }
}