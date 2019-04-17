package game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by sorooshbagheri on 19/03/13.
 */

public class Main {

    public static void main(String[] args) {

        //EDT thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame window = new JFrame("Game");
        window.setSize(new Dimension(1400 ,700));
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Game game = new Game();
        window.add(game);

        game.start();
        window.setVisible(true);

    }
}


