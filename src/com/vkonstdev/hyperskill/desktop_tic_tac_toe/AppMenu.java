package com.vkonstdev.hyperskill.desktop_tic_tac_toe;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class AppMenu extends JMenuBar {

    public AppMenu(final ActionListener actionListener) {
        super();
        final var menuItemHH = new JMenuItem("Human vs Human", KeyEvent.VK_H);
        menuItemHH.setName("MenuHumanHuman");
        menuItemHH.addActionListener(actionListener);

        final var menuItemHR = new JMenuItem("Human vs Robot", KeyEvent.VK_R);
        menuItemHR.setName("MenuHumanRobot");
        menuItemHR.addActionListener(actionListener);

        final var menuItemRH = new JMenuItem("Robot vs Human", KeyEvent.VK_U);
        menuItemRH.setName("MenuRobotHuman");
        menuItemRH.addActionListener(actionListener);

        final var menuItemRR = new JMenuItem("Robot vs Robot", KeyEvent.VK_O);
        menuItemRR.setName("MenuRobotRobot");
        menuItemRR.addActionListener(actionListener);

        final var menuItemExit = new JMenuItem("Exit", KeyEvent.VK_X);
        menuItemExit.setName("MenuExit");
        menuItemExit.addActionListener(actionListener);

        final var menuGame = new JMenu("Game");
        menuGame.setName("MenuGame");
        menuGame.setMnemonic(KeyEvent.VK_G);

        menuGame.add(menuItemHH);
        menuGame.add(menuItemHR);
        menuGame.add(menuItemRH);
        menuGame.add(menuItemRR);
        menuGame.addSeparator();
        menuGame.add(menuItemExit);

        add(menuGame);
    }
}