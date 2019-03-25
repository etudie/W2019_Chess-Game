package ChessVersion4;

import java.awt.Dimension;

import javax.swing.JFrame;

/**********************************************************************
 * A graphical representation of a chess game.
 *
 * @author Amela Aganovic, Emily Linderman, Xue Hua
 * @version Winter 2019
 *********************************************************************/
public class ChessGUI {

    /******************************************************************
     * Main method that sets up the chess game.
     *****************************************************************/
    public static void main(String[] args) {
        JFrame frame = new JFrame("Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ChessPanel panel = new ChessPanel();
        frame.getContentPane().add(panel);

        frame.setResizable(true);
        frame.setPreferredSize(new Dimension(1000, 900));
        frame.pack();
        frame.setVisible(true);
    }
}
