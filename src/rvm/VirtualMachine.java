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

    public VirtualMachine(RM realMachine, Byte[] registers) {
        DS = registers[0];
        CS = registers[1];
        SS = registers[2];
        this.realMachine = realMachine;
        this.memory = this.realMachine.getVirtualMemory();

    }

    public void step() {
        memory.writeWord((int) CS + (int) IP, new Word("AD12"));
        executeCommand(memory.readWord((int) CS + (int) IP));
        IP++;
        realMachine.interruptCheck();
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
                        getDat(arg);
                        break;
                    default:
                        throw new RuntimeException("Pisk ožiuką");
                        //break;
                        
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
        
    }
    
    private void jump() {
        
    }
    
    private void jumpIfEqual() {
        
    }
    
    private void jumpIfLess() {
        
    }
    
    private void jumpIfGreater() {
        
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

    private void halt() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void putData(Word value) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void getDat(Word adress) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
