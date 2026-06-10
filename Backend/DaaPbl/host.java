package DaaPbl;

public class host extends Node {

    public host(String id,String ipadr){
        super(id,ipadr);
    }

    @Override
    public void receivepak(paket pkt){

        login.success("Packet received at Host "+id);
        pkt.addpkt(id);
        login.line();
        login.info("MESSAGE RECEIVED");
        login.info("Data: " +pkt.getdata());

        login.success("FINAL PATH: "+String.join(" -> ", pkt.getPath()));
        login.line();
    }

    @Override
    public void sendpak(paket pkt){
        login.info("Host " + id + " sending packet...");
        if(neighbors.isEmpty()){
            login.error("No router connected to host "+id);
            return;
        }
        Node next=neighbors.get(0);
        next.receivepak(pkt);
    }
}
