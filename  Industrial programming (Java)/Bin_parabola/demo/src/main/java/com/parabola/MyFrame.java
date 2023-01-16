package com.parabola;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MyFrame extends JFrame {
    JButton but;
    JTextField tf_end;
    PaintComp component;
    JTextField tf_start;
    JPanel pan = new JPanel();
    
    MyFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 200, 700, 500);
        component = new PaintComp();
        tf_start = new JTextField("5", 5);
        tf_end = new JTextField("5", 5);
        but = new JButton("Построить");
        
        pan.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan.add(tf_start);
        pan.add(tf_end);
        pan.add(but);
        
        add(component, BorderLayout.CENTER);
        add(pan, BorderLayout.SOUTH);
        
        // Событие кнопки
        but.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                component.start = Double.parseDouble(tf_start.getText()); // первое значение из текстового поля (левое)
                component.end = Double.parseDouble(tf_end.getText()); // второе значение из текстового поля (правое)
                component.repaint();
            }
        });
    }
}
