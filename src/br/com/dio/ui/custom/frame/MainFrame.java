package br.com.dio.ui.custom.frame;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame(final Dimension dimension, final JPanel mainPanel) {
        super("Sudoku");
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(mainPanel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);


    }
}
