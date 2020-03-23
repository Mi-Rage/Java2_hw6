package ru.gb.jtwo.la.online.circles;

import java.awt.*;

public interface GameObject {
    void update(MainCanvas canvas, float deltaTime);
    void render(MainCanvas canvas, Graphics g);
}
