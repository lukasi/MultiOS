package Hardware;

import rvm.Constants.*;
import rvm.Word;
/**
 *
 * @author ernestas
 */
public class Screen {
    private Word[] screen; // newl newline
    private int curr = 0;
    
    public Screen() {
        screen = new Word[rvm.Constants.SCREEN_SIZE];
    }
    
    public void put(Word output) {
        if (curr < rvm.Constants.SCREEN_SIZE) {
            screen[curr] = output;
            curr++;
        }
    }
    
    public Word get(int index) {
        if (index < curr) {
            return screen[index];
        } else {
            return null;
        }
    }
    
    public int getSize() {
        return curr;
    }
    
    
}
