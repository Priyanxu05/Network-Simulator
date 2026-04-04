package DaaPbl;

import java.util.*;

public class paket {
    private String source;
    private String dest;
    private String data;
    private int time;
    private List<String> path;

    public paket(String source,String dest,String data,int time) {
    this.source=source;
    this.dest=dest;
    this.data=data;
    this.time=time;
    this.path=new ArrayList<>();
}

    public void timedecrement(){
    time--;
}

    public boolean expire(){
    return time<=0;
}
    public void addpkt(String nodeid) {
        path.add(nodeid);
    }
    public String getdest() {
        return dest;
    }
    public String getsource() {
        return source;
    }
    public List<String> getPath() {
        return path;
    }
    public int gettime() {
        return time;
    }
    public String getdata() {
        return data;
    }
    
}
