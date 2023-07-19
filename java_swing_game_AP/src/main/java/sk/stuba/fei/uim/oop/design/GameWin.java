package sk.stuba.fei.uim.oop.design;

import javax.swing.*;
import java.awt.*;

public class GameWin extends JFrame {
    private GridBagConstraints grid;
    public GameWin() {
        super("Thank You for playing <3");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.grid = new GridBagConstraints();
        JFrame win = new JFrame();
        this.grid.gridx = 0;
        setSize(400, 200);
        this.setResizable(false);
        JLabel uWonLabel = new JLabel("U WON ! Congratulations!");
        this.add(uWonLabel, this.grid);
        this.grid.gridy = 2;
        JLabel exitLabel = new JLabel("Press X to exit!");
        this.add(exitLabel, this.grid);
        this.setLocationRelativeTo(null);
        setVisible(true);
    }
}
