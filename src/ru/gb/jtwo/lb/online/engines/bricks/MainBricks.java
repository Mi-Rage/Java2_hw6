package ru.gb.jtwo.lb.online.engines.bricks;

import ru.gb.jtwo.lb.online.engines.common.CanvasListener;
import ru.gb.jtwo.lb.online.engines.common.GameObject;
import ru.gb.jtwo.lb.online.engines.common.MainCanvas;
import ru.gb.jtwo.lb.online.engines.common.Sprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainBricks extends JFrame implements CanvasListener {
    private static final int POS_X = 400;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    private GameObject[] gameObjects = new GameObject[1];
    private int gameObjectsCount;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainBricks();
            }
        });
    }

    private MainBricks() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Bricks");
        initApplication();

        MainCanvas canvas = new MainCanvas(this);
        add(canvas);
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    addGameObject(new Brick(e.getX(), e.getY()));
                else if (e.getButton() == MouseEvent.BUTTON3)
                    removeSprite();
            }
        });

        setVisible(true);
    }

    private void addGameObject(Sprite s) {
        if (gameObjectsCount == gameObjects.length) {
            GameObject[] temp = new GameObject[gameObjects.length * 2];
            System.arraycopy(gameObjects, 0, temp, 0, gameObjects.length);
            gameObjects = temp;
        }
        gameObjects[gameObjectsCount++] = s;
    }

    private void removeSprite() {
        if (gameObjectsCount > 1) gameObjectsCount--;
    }

    private void initApplication() {
        gameObjects[0] = new Brick();
        gameObjectsCount++;

    }

    public void onCanvasRepainted(MainCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    private void update(MainCanvas canvas, float deltaTime) {
        for (int i = 0; i < gameObjectsCount; i++) {
            gameObjects[i].update(canvas, deltaTime);
        }
    }

    private void render(MainCanvas canvas, Graphics g) {
        for (int i = 0; i < gameObjectsCount; i++) {
            gameObjects[i].render(canvas, g);
        }
    }
}
