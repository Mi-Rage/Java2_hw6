package ru.gb.jtwo.lb.online.engines.common;


import ru.gb.jtwo.lb.online.engines.circles.MainCircles;

import javax.swing.*;
import java.awt.*;

public class MainCanvas extends JPanel {

    long lastFrameTime;
    private final CanvasListener controller;

    public MainCanvas(CanvasListener controller) {
        lastFrameTime = System.nanoTime();
        this.controller = controller;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        long currentTime = System.nanoTime();
        float deltaTime = (currentTime - lastFrameTime) * 0.000000001f;
        controller.onCanvasRepainted(this, g, deltaTime);

        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lastFrameTime = currentTime;
        repaint();
    }

    public int getLeft() { return 0; }
    public int getRight() { return getWidth() - 1; }
    public int getTop() { return 0; }
    public int getBottom() { return getHeight() - 1; }

}
