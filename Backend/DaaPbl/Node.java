package DaaPbl;
import java.util.*;

public abstract class Node {
    protected String id;
    protected String ipadr;
    List<Node> neighbors;

    public Node(String id, String ipadr){
        this.id=id;
        this.ipadr= ipadr;
        this.neighbors=new ArrayList<>();
    }
    public void connect(Node node){
        neighbors.add(node);

    }

    public String getid(){
        return id;
    }

    public String getipadr(){
        return ipadr;
    }
    public List<Node> getneighbors(){
        return neighbors;
    }

    public abstract void receivepak(paket pakt);
    public abstract void sendpak(paket pakt);
    
}
