//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package sk.stuba.fei.uim.oop.design;

import java.awt.*;
import javax.swing.*;

import sk.stuba.fei.uim.oop.logic.GameLogic;


public class GameNav extends JPanel {
    private GridBagConstraints grid;
    private JPanel resetButton;
    private JPanel validateButton;


    public GameNav(GameLogic logic) {




        this.setLayout(new GridBagLayout());
        this.grid = new GridBagConstraints();
        this.grid.fill = 1;
        JLabel currentlevel = logic.getLevelLabel();
        this.grid.gridx = 0;
        this.grid.gridy = 1;
        this.grid.ipadx = 8;
        this.grid.ipady = 8;
        this.grid.weightx = 0.5;
        this.grid.weighty = 0.5;
        this.add(currentlevel, this.grid);

        JButton resetButton = new JButton("RESET");
        resetButton.addActionListener(logic);
        this.grid.gridx = 1;
        this.grid.gridy = 0;
        this.grid.ipadx = 8;
        this.grid.ipady = 8;
        this.grid.weightx = 0.5;
        this.grid.weighty = 0.5;
        this.add(resetButton, this.grid);

        JLabel currentSize = logic.getSizeLabel();
        this.grid.gridx = 0;
        this.grid.gridy = 2;
        this.grid.ipadx = 8;
        this.grid.ipady = 8;
        this.grid.weightx = 0.5;
        this.grid.weighty = 0.5;
        this.add(currentSize, this.grid);

        JLabel gameTitle = new JLabel("Water Pipes");
        this.grid.gridx = 0;
        this.grid.gridy = 0;
        this.grid.ipadx = 8;
        this.grid.ipady = 8;
        this.grid.weightx = 0.5;
        this.grid.weighty = 0.5;
        this.add(gameTitle, this.grid);

        JButton validateButton = new JButton("VALIDATE");
        validateButton.addActionListener(logic);
        this.grid.gridx = 1;
        this.grid.gridy = 1;
        this.grid.ipadx = 8;
        this.grid.ipady = 8;
        this.grid.weightx = 0.5;
        this.grid.weighty = 0.5;
        this.add(validateButton, this.grid);

        JSlider slider = new JSlider(JSlider.VERTICAL,8,12, 8);
        slider.setPreferredSize(new Dimension(70, 50));
        slider.setMinorTickSpacing(2);
        slider.setMajorTickSpacing(2);
        slider.setSnapToTicks(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(logic);
        this.grid.gridx = 1;
        this.grid.gridy = 2;
        this.grid.ipadx = 8;
        this.grid.ipady = 8;
        this.grid.weightx = 0.5;
        this.grid.weighty = 0.5;
        this.add(slider, this.grid);
        setSize(500, 150);
        setVisible(true);
    }
}
