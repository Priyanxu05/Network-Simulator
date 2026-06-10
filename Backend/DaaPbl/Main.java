package DaaPbl;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        login.header("NETWORK INITIALIZATION");
        router r1 = new router("R1", "192.168.1.1");
        router r2 = new router("R2", "192.168.1.2");
        router r3 = new router("R3", "192.168.1.3");
        router r4 = new router("R4", "192.168.1.4");
        router r5 = new router("R5", "192.168.1.5");
        router r6 = new router("R6","192.168.1.6");
        router r7 = new router("R7","192.168.1.7");
        router r8 = new router("R8","192.168.1.8");
        router r9 = new router("R9","192.168.1.9");
        router r10 = new router("R10","192.168.1.10");
        router r11 = new router("R11","192.168.1.11");
        router r12 = new router("R12","192.168.1.12");
        host h1 = new host("H1", "192.168.1.10");
        host h2 = new host("H2", "192.168.1.20");
        Network graph = new Network();
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
        graph.showTopology();
        RoutingProtocoll.buildRoutingTables(graph);
        login.success("Routing tables built successfully");
        r1.showRoutingTable();
        r2.showRoutingTable();
        r3.showRoutingTable();
        r4.showRoutingTable();
        r5.showRoutingTable();
        r6.showRoutingTable();
        r7.showRoutingTable();
        r8.showRoutingTable();
        r9.showRoutingTable();
        r10.showRoutingTable();
        r11.showRoutingTable();
        r12.showRoutingTable();

        login.header("PHASE 1 : NORMAL PACKET TRANSFER");
        List<paket> packets = new ArrayList<>();
        packets.add(new paket(h1.getipadr(),h2.getipadr(),"Normal Message 1",10));

        packets.add(new paket(h1.getipadr(),h2.getipadr(), "Normal Message 2", 10));

        for (paket pkt : packets) {
            login.line();
            login.info(
                    "PACKET #" + pkt.getPacketId());

            login.info(
                    "Source : " + pkt.getsource());

            login.info(
                    "Destination : " + pkt.getdest());

            login.info(
                    "Data : " + pkt.getdata());

            h1.sendpak(pkt);
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        login.header("PHASE 2 : ROUTER R5 FAILURE");
        r5.disable();
        RoutingProtocoll.buildRoutingTables(graph);
        r1.showRoutingTable();
        r3.showRoutingTable();
        r4.showRoutingTable();
        r5.showRoutingTable();

        paket failPacket = new paket(h1.getipadr(),h2.getipadr(),"Packet During R2 Failure",10);
        login.line();
        login.info("PACKET #" + failPacket.getPacketId());
        h1.sendpak(failPacket);
        login.header("PHASE 3 : ROUTER R6 FAILURE");
        r6.disable();
        RoutingProtocoll.buildRoutingTables(graph);
        r1.showRoutingTable();
        r3.showRoutingTable();
        r5.showRoutingTable();
        paket failPacket2 = new paket(h1.getipadr(), h2.getipadr(), "Packet During R2 + R4 Failure",10);

        login.line();
        login.info("PACKET #" + failPacket2.getPacketId());
        h1.sendpak(failPacket2);
        login.header("PHASE 4 : TOTAL NETWORK FAILURE");
        r5.disable();
        RoutingProtocoll.buildRoutingTables(graph);

        paket failPacket3 = new paket(h1.getipadr(), h2.getipadr(),"Packet During Total Failure",10);

        login.line();
        login.info("PACKET #" + failPacket3.getPacketId());

        h1.sendpak(failPacket3);

        login.header("PHASE 5 : NETWORK RECOVERY");

        r5.enable();
r6.enable();

        RoutingProtocoll.buildRoutingTables(graph);

        r1.showRoutingTable();
        r2.showRoutingTable();
        r3.showRoutingTable();
        r4.showRoutingTable();
        r5.showRoutingTable();

        paket recoveryPacket = new paket(h1.getipadr(),h2.getipadr(),"Recovery Packet",10);

        login.line();
        login.info("PACKET #" + recoveryPacket.getPacketId());
        h1.sendpak(recoveryPacket);
        login.header("SIMULATION STATISTICS");
        login.info("Packets Sent : 5");
        login.info("Routers Used : 12");
        login.info("Network Hosts : 2");
        login.info("Routing Algorithm : Dijkstra");
        login.info("Dynamic Routing : Enabled");
        login.info("Router Failure Recovery : Enabled");

        login.header("SIMULATION COMPLETE");
    }
}
