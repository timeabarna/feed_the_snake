package crawling.entertainment.feed_the_sanke.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
    private boolean[] isKeysPressed;
    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;

    public KeyManager() {
        isKeysPressed = new boolean[256];
    }

    public void gamePlay() {
        up = isKeysPressed[KeyEvent.VK_UP];
        down = isKeysPressed[KeyEvent.VK_DOWN];
        left = isKeysPressed[KeyEvent.VK_LEFT];
        right = isKeysPressed[KeyEvent.VK_RIGHT];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        isKeysPressed[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        isKeysPressed[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
