package rvm;

/**
 *
 * @author ernestas
 */
public class VirtualMachine {

    private VirtualMemory memory;
    private RM realMachine;
    private Word R = new Word();
    private final Byte CS; //TODO jo manau reik replace Byte[] į int ar pan. Arba kurt savo TwoByteRegister kur yra ++ ir dar ko reik
    private final Byte DS;
    private final Byte SS;
    private short IP = 0;
    private short SP = 0;
    private Byte SF = 0;

    public VirtualMachine(VirtualMemory mem, Byte[] registers) {
        this.memory = mem;
        this.DS = registers[0];
        this.CS = registers[1];
        this.SS = registers[2];

    }

    public void step() {
        executeCommand(memory.readWord((int) CS + (int) IP, SF));
        IP++;
        realMachine.interruptCheck();
    }
    //TODO reik krc gi programoj atsizvelgt .DATA .CODE sintaksinius dalykus
    private boolean artvarkabendraisuprograma() {
        int segment = 
    }
    
    private void executeCommand(Word op) {
        String opcode = op.get().toString().toUpperCase();
        switch (opcode) {
            case "HALT":
                halt();

                break;
            case "PUSH":
                break;
            case "POP ":
                break;
            default:
                Word arg = new Word(opcode.substring(2, 4));
                
                switch (opcode.substring(0, 2)) {
                    case "AD": // ADD, AD D ar ADD?
                        add(memory.readWord(arg));
                        break;
                    case "SB":
                        break;
                    case "LR":
                        break;
                    case "SR":
                        break;
                    case "CM":
                        break;
                    case "JP":
                        break;
                    case "JE":
                        break;
                    case "JL":
                        break;
                    case "JG":
                        break;
                    case "PD":
                        putData(memory.readWord(arg));
                        break;
                    case "GD":
                        getData(arg);
                        break;
                    default:
                        // interrupt. unknown opcode
                        break;
                        
                }
        }



    }

    // operations
    private void add(Word value) {
        R = new Word(R.toInt() + value.toInt());
    }
    
    private void sub(Word value) {
        R = new Word(R.toInt() - value.toInt());
    }
    
    private void loadRegister(Word addr) {
        memory.writeWord(DS, addr.toInt(), new Word(R.toInt()));
    }
        
    
    private void saveRegister(Word addr) {
        R = memory.readWord(DS, addr.toInt());
    }
    
    private void compare(Word value) {
        if (R.toInt() < value.toInt()) {
            SF = 0;
        } else if (R.toInt() == value.toInt()) {
            SF = 1;
        } else {
            SF = 2;
        }
    }
    
    private void jump(Word addr) {
        IP = (short) addr.toInt();
    }
    
    private void jumpIfEqual(Word addr) {
        if (SF == 1) {
            IP = (short) addr.toInt();
        }
    }
    
    private void jumpIfLess(Word addr) {
        if (SF == 0) {
            IP = (short) addr.toInt();
        }  
    }
    
    private void jumpIfGreater(Word addr) {
        if (SF == 2) {
            IP = (short) addr.toInt();
        } 
    }
    
    private void push() {
        if (SP == SS) {
            SP = (short) (SS + 0x1F);
        } else {
            SP--;
        }
        memory.writeWord(SS, SP, R);
    }
    
    private void pop() {
        R = memory.readWord(SS, SP);
        if (SP == SS + 1F) {
            SP = SS;
        } else {
            SP++;
        }
        
    }
    /*
    public void setCodeSeg(byte addr) {
        CS = addr;
        IP = CS;
    }

    public void setDataSeg(byte addr) {
        DS = addr;
    }

    public void setStackSeg(byte addr) {
        SS = addr;
    */

    private void halt() { // tas pats kas destruktorius cj isvis gali nieko nedaryt tik informuot realia mashina
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void putData(Word value) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void getData(Word adress) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
