package MOS.Resources;

import MOS.Process;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *  NELIESTI BLET VISKAS KOLKAS GERAI
 * @author ernestas
 */
public class Resource {
    private static int newID = 0; // globalus naujas id
    
    private int id; // vidinis id
    private String name; // isorinis vardas
    
    private boolean reusable; // ar pakartotinio naudojimo
    private boolean free; // ar mires resursas
    
    private Process creator; // kurejas
    public Queue<Process> processQueue; // laukiantys procesai
    public Object element; // data 
    
    
    public Resource(Process creator, String name) {
        this.creator = creator;
        this.name = name;
        this.id = newID++;
        processQueue = new PriorityQueue<>();
    }
    
    public int getId() {
        return id;
    }
    
    public boolean isReusable() {
        return reusable;
    }
    
    public boolean isFree() {
        return free;
    }
    
    public void setFree() {
        free = true;
    }
    
    public String getName() {
        return name;
    }
    
    public Process getCreator() {
        return creator;
    }
        
}
