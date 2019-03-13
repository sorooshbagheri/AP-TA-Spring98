import javax.swing.*;
import java.awt.*;

/**
 * Created by sorooshbagheri on 19/03/13.
 */

public class Game extends JPanel implements Runnable {

    private boolean running = false;
    private int repaints = 0;
    private Thread gameThread;

    public Game() {
        setBackground(Color.BLACK);
        gameThread = new Thread(this);
    }

    @Override
    public void run(){
        //The Game Loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0; //per second
        double ns_per_tick = 1000000000 / amountOfTicks; //results in almost: 16 millions
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns_per_tick;
            lastTime = now;
            while (delta >= 1){
                tick(); //must take less than 16 million nano seconds
                frames++;
                delta --;
            }
            if (running) {
//                render();
                repaint();
//                paintImmediately(0,0,Game.getGameWidth(),Game.getGameHeight());
            }

//            try {
//                Thread.sleep(frames>65 ? 30 : 0);
//                Thread.sleep((long) Math.max(System.nanoTime() - lastTime - ns_per_tick, 1));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            if (frames>60){
//                try {
//                    Thread.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }

            if (System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.format("FPS: %d repaints: %d%n", frames, repaints);
                frames = 0;
                repaints = 0;
            }
        }
        stop();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        repaints++;
        g.setColor(Color.white);
        g.fillRect(x,y,60,60);
    }

    int x = 30,y = 30;
    private void tick(){
        x++;
        y++;
    }

    void start(){
        running = true;
        System.out.println("Start");
        gameThread.start();
    }

    void stop(){
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}


