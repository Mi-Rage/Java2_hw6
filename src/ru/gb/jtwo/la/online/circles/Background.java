package ru.gb.jtwo.la.online.circles;

import java.awt.*;

public class Background implements GameObject {
    private float time;
    private static final float AMPLITUDE = 255f / 2f;
    private Color color;

    @Override
    public void update(MainCanvas gameCanvas, float deltaTime) {
        time += deltaTime;
        int red = Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time / 2));
        int green = Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time / 3));
        int blue = Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time));
        color = new Color(red, green, blue);
    }

    @Override
    public void render(MainCanvas gameCanvas, Graphics g) {
        gameCanvas.setBackground(color);
    }
}
