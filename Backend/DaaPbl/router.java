package DaaPbl;
import java.util.*;
public class router extends Node{

    private Map<String,Node> routingtable;
    private Queue<paket> paketque;
    private boolean active=true;

    public router(String id, String ipadr) {
        super(id, ipadr);
        routingtable =new HashMap<>();
        paketque =new LinkedList<>();
    }

    public void disable(){
        active=false;
        login.error("Router " +id +" is DOWN");
    }

    public void enable(){
        active=true;
        login.success("Router " + id + " is UP");
    }

    public void updateRtable(String destip,Node nextNode) {
        routingtable.put(destip,nextNode);
    }

    @Override
    public void receivepak(paket pkt) {

        if(!active){
            login.error("Packet DROPPED at Router " +id+ " (Router DOWN)");
            return;
        }

        login.info("Packet received at Router "+id);

        pkt.addpkt(id);
        pkt.timedecrement();

        if(pkt.expire()){
            login.error("Packet DROPPED at " +id+ " (TTL expired)");
            return;
        }
        if (pkt.getdest().equals(this.ipadr)){
            login.success("Packet reached destination at Router "+id);
            return;
        }

        for (Node n : neighbors){
            if (n.getipadr().equals(pkt.getdest())){
                login.info("Router " +id+ " sending packet to Host");
                n.receivepak(pkt);
                return;
            }}
        paketque.add(pkt);
        processQueue();
    }

    private void processQueue(){
        while (!paketque.isEmpty()){
            paket pkt=paketque.poll();
            forwardpkt(pkt);
        } }

    private void forwardpkt(paket pkt){
        String dest = pkt.getdest();
        if (routingtable.containsKey(dest)){
            Node nextnode=routingtable.get(dest);
            login.info("Router " + id + " -> Forwarding packet to " + nextnode.getid());
            nextnode.receivepak(pkt);
        } 
        else {
            login.error("No route found at Router " + id + " for destination " + dest);
        }}

    @Override
    public void sendpak(paket pkt){
        receivepak(pkt);
    }
}