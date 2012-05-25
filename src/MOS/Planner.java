package MOS;

import MOS.Process.ProcessState;
import Hardware.RealMachine;

/**
 * Realizuotas per PriorityQueue (nesynchronized) ir Comparatoriu (Realiai jame ir yra palyginimo logika)
 * Alternatyva:
 *   ArrayList
 *   foreach'as ieskantis geriausio
 * @author ernestas
 */
public class Planner { 
    private RealMachine machine;
    
    /**
     * Constructs the Planner for RM.
     * @param machine used to reach processes of RM.
     */
    protected Planner(RealMachine machine) {
        this.machine = machine;
    }
    
    /**
     * Returns Process to be executed next.
     * @return process having highest priority and is ready to work. Null if there are no processes or all are blocked
     */
    private Process getNextProcess() {
        // TODO : Grazins is listo tinkamiausia net jei jis negali dirbti del resursu trukumo.
        // SPRENDIMAS: Arba du listai stabdyti ir galintys dirbti, arba begti per pqueue ir paimti galinti veikti (comparatoriui cia)
        for (Process p : machine.processes) { 
            if (p.state == ProcessState.READY || p.state == ProcessState.READYS) {
                return p;
            }
        }
        return null;
    }
    public void run(){
        getNextProcess().run();
    }
    
}
