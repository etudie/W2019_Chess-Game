package chess;
/*	ChessGUI.java
This class is responsible for
1. Contains the main method
2. creates and displays chess game GUI
3. uses chess piece icons
 */
import java.awt.Dimension;

import javax.swing.JFrame;

public class ChessGUI {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ChessPanel panel = new ChessPanel();
        frame.getContentPane().add(panel);

        frame.setResizable(true);
        frame.setPreferredSize(new Dimension(800, 800));
        frame.pack();
        frame.setVisible(true);
    }
}
