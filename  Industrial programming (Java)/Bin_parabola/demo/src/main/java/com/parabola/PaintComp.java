package com.parabola;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

class PaintComp extends JComponent{
    Graphics2D gr;
    double x, y, x1, y1;
    private double coef = 10; // шаг сетки
    private double step = 0.05; // шаг сетки // изменять здесь
    double ox = 350, oy = 300; // ось симметрии параболы
    double start = -1, end = 2; // интервалы

    public void paintComponent(Graphics g){
        gr = (Graphics2D) g;
        coef = this.getWidth() / 70;
        end *= coef;
        start *= coef;
        ox = (int)(this.getWidth() / 2);
        oy = (int)(this.getHeight() / 2 + this.getHeight() / 28);
    
        // Делаем белый фон
        Rectangle2D rect = new Rectangle2D.Double(0, 0, this.getWidth(), this.getHeight());
        gr.setPaint(Color.white);
        gr.fill(rect);
        gr.draw(rect);
        
        // Рисуем сетку
        gr.setPaint(Color.LIGHT_GRAY);
        gr.setStroke(new BasicStroke((float) 0.2));
        for(y = 0; y <= this.getWidth(); y += coef) {
            gr.draw(new Line2D.Double(0, y, this.getWidth(), y));
            gr.draw(new Line2D.Double(y, 0, y, this.getHeight()));
        }

        // Рисуем оси
        gr.setPaint(Color.GREEN);
        gr.setStroke(new BasicStroke((float) 1));
        gr.draw(new Line2D.Double(ox, 0, ox, this.getHeight()));
        gr.draw(new Line2D.Double(0, oy, this.getWidth(), oy));
        
        // Рисуем отрезок интервала
        gr.setPaint(Color.RED);
        gr.setStroke(new BasicStroke((float) 3));
        gr.draw(new Line2D.Double(ox - Math.abs(start), oy, ox + Math.abs(end), oy));
        
        // Рисуем параболу
        gr.setPaint(Color.BLACK);
        gr.setStroke(new BasicStroke((float) 3));
        
        for(x = 0; x <= Math.abs(start); x += step) { // левая ветвь
            y = -(x * x) / coef + 1 * coef; // изменять здесь сейчас график у = х^2 - 1
            x1 = x - step;
            y1 = -(x1 * x1) / coef + 1 * coef; // здесь
            gr.draw(new Line2D.Double(ox - x, y + oy, ox - x1, y1 + oy));
        }   
        
        for(x = 0; x <= Math.abs(end); x += step) { // правая ветвь
            y = -(x * x) / coef + 1 * coef; // здесь
            x1 = x - step;
            y1 = -(x1 * x1) / coef + 1 * coef; // здесь
            gr.draw(new Line2D.Double(x + ox, y + oy, x1 + ox, y1 + oy));
        }
    }
}