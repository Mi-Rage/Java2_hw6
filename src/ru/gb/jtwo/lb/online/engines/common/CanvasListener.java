package ru.gb.jtwo.lb.online.engines.common;

import java.awt.*;

public interface CanvasListener {
    void onCanvasRepainted(MainCanvas canvas, Graphics g, float deltaTime);
}
