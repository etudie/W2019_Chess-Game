package Project3;

import jdk.nashorn.internal.scripts.JO;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChessPanel extends JPanel {

    private JButton[][] board;
    private ChessModel model;

    private JButton undo;
    private JButton newGame;
    private JLabel lastMove;

    private ImageIcon wRook;
    private ImageIcon wBishop;
    private ImageIcon wQueen;
    private ImageIcon wKing;
    private ImageIcon wPawn;
    private ImageIcon wKnight;

    private ImageIcon bRook;
    private ImageIcon bBishop;
    private ImageIcon bQueen;
    private ImageIcon bKing;
    private ImageIcon bPawn;
    private ImageIcon bKnight;

    private boolean firstTurnFlag;
    private int fromRow;
    private int toRow;
    private int fromCol;
    private int toCol;
    // declare other instance variables as needed

    private listener listener;

    public ChessPanel() {
        model = new ChessModel();
        board = new JButton[model.numRows()][model.numColumns()];
        listener = new listener();

        undo = new JButton("Undo");
        undo.addActionListener(listener);
        newGame = new JButton("New Game");
        newGame.addActionListener(listener);

        createIcons();

        JPanel boardpanel = new JPanel();
        JPanel buttonpanel = new JPanel();
        boardpanel.setLayout(new GridLayout(model.numRows(), model.numColumns(), 1, 1));
        buttonpanel.setLayout(new GridLayout(1,3));
        buttonpanel.add(undo);
        buttonpanel.add(newGame);

        for (int r = 0; r < model.numRows(); r++) {
            for (int c = 0; c < model.numColumns(); c++) {
                if (model.pieceAt(r, c) == null) {
                    board[r][c] = new JButton("", null);
                    board[r][c].addActionListener(listener);
                    }
                else if (model.pieceAt(r, c).player() == Player.BLACK) {
                    placeBlackPieces(r,c);
                }
                else if (model.pieceAt(r, c).player() == Player.WHITE)
                    placeWhitePieces(r, c);

                setBackGroundColor(r, c);
                boardpanel.add(board[r][c]);
            }
        }

        setLayout(new BorderLayout());
        add(boardpanel, BorderLayout.CENTER);
        boardpanel.setPreferredSize(new Dimension(800, 800));
        add(buttonpanel, BorderLayout.SOUTH);
        firstTurnFlag = true;
    }

    private void setBackGroundColor(int r, int c) {
        if ((c % 2 == 1 && r % 2 == 0) || (c % 2 == 0 && r % 2 == 1)) {
            board[r][c].setBackground(Color.LIGHT_GRAY);
        } else if ((c % 2 == 0 && r % 2 == 0) || (c % 2 == 1 && r % 2 == 1)) {
            board[r][c].setBackground(Color.WHITE);
        }
    }

    private void placeWhitePieces(int r, int c) {
        if (model.pieceAt(r, c).type().equals("Pawn")) {
            board[r][c] = new JButton(null, wPawn);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Rook")) {
            board[r][c] = new JButton(null, wRook);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Knight")) {
            board[r][c] = new JButton(null, wKnight);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Bishop")) {
            board[r][c] = new JButton(null, wBishop);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Queen")) {
            board[r][c] = new JButton(null, wQueen);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("King")) {
            board[r][c] = new JButton(null, wKing);
            board[r][c].addActionListener(listener);
        }
    }

    private void placeBlackPieces(int r, int c) {
        if (model.pieceAt(r, c).type().equals("Pawn")) {
            board[r][c] = new JButton(null, bPawn);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Rook")) {
            board[r][c] = new JButton(null, bRook);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Knight")) {
            board[r][c] = new JButton(null, bKnight);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Bishop")) {
            board[r][c] = new JButton(null, bBishop);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Queen")) {
            board[r][c] = new JButton(null, bQueen);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("King")) {
            board[r][c] = new JButton(null, bKing);
            board[r][c].addActionListener(listener);
        }
    }

    private void createIcons() {
        // Sets the Image for white player pieces
        wRook = new ImageIcon("./src/Project3/wRook.png");
        wBishop = new ImageIcon("./src/Project3/wBishop.png");
        wQueen = new ImageIcon("./src/Project3/wQueen.png");
        wKing = new ImageIcon("./src/Project3/wKing.png");
        wPawn = new ImageIcon("./src/Project3/wPawn.png");
        wKnight = new ImageIcon("./src/Project3/wKnight.png");

        bRook = new ImageIcon("./src/Project3/bRook.png");
        bBishop = new ImageIcon("./src/Project3/bBishop.png");
        bQueen = new ImageIcon("./src/Project3/bQueen.png");
        bKing = new ImageIcon("./src/Project3/bKing.png");
        bPawn = new ImageIcon("./src/Project3/bPawn.png");
        bKnight = new ImageIcon("./src/Project3/bKnight.png");
    }

    private void toggleSpace(int r, int c, boolean select) {
        if (select) {
            board[r][c].setBackground(Color.PINK);
            System.out.println("Highlighting" + " [ " +  r  + " ] " + " [ " + c + " ] ");
        } else {
            setBackGroundColor(r, c);
            System.out.println("Un-Highlighting" + " [ " + r  + " ] " + " [ " + c + " ] ");
        }
    }

    // method that updates the board
    private void displayBoard() {

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (model.pieceAt(r, c) == null)
                    board[r][c].setIcon(null);
                else if (model.pieceAt(r, c).player() == Player.WHITE) {
                    if (model.pieceAt(r, c).type().equals("Pawn"))
                        board[r][c].setIcon(wPawn);

                    if (model.pieceAt(r, c).type().equals("Rook"))
                        board[r][c].setIcon(wRook);

                    if (model.pieceAt(r, c).type().equals("Knight"))
                        board[r][c].setIcon(wKnight);

                    if (model.pieceAt(r, c).type().equals("Bishop"))
                        board[r][c].setIcon(wBishop);

                    if (model.pieceAt(r, c).type().equals("Queen"))
                        board[r][c].setIcon(wQueen);

                    if (model.pieceAt(r, c).type().equals("King"))
                        board[r][c].setIcon(wKing);
                } else if (model.pieceAt(r, c).player() == Player.BLACK) {
                    if (model.pieceAt(r, c).type().equals("Pawn"))
                        board[r][c].setIcon(bPawn);

                    if (model.pieceAt(r, c).type().equals("Rook"))
                        board[r][c].setIcon(bRook);

                    if (model.pieceAt(r, c).type().equals("Knight"))
                        board[r][c].setIcon(bKnight);

                    if (model.pieceAt(r, c).type().equals("Bishop"))
                        board[r][c].setIcon(bBishop);

                    if (model.pieceAt(r, c).type().equals("Queen"))
                        board[r][c].setIcon(bQueen);

                    if (model.pieceAt(r, c).type().equals("King"))
                        board[r][c].setIcon(bKing);
                }
                repaint();
            }
        }
        if (model.inCheck(Player.BLACK))
            JOptionPane.showMessageDialog(null, "Black in Check");
        if (model.inCheck(Player.WHITE))
            JOptionPane.showMessageDialog(null, "White in Check");
    }

    // inner class that represents action listener for buttons
    private class listener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            for (int r = 0; r < model.numRows(); r++)
                for (int c = 0; c < model.numColumns(); c++)
                    if (board[r][c] == event.getSource())
                        if (firstTurnFlag == true) {
                            fromRow = r;
                            fromCol = c;
                            firstTurnFlag = false;
//                            if (model.pieceAt(r,c) != null &&
//                                    model.pieceAt(r, c).player() == model.currentPlayer())
//                                toggleSpace(fromRow, fromCol, true);

                        } else {
                            toRow = r;
                            toCol = c;
                            firstTurnFlag = true;

                            Move m = new Move(fromRow, fromCol, toRow, toCol);
//                            toggleSpace(fromRow, fromCol, false);
                            if ((model.isValidMove(m)) == true) {

                                //toggleSpace(fromRow, fromCol, false);
                                model.move(m);
                                model.setNextPlayer();
                            }
//                            lastMove.setText(m.toString()); // FIXME
//                            currentTurn.setText("Turn : " + model.currentPlayer()); // FIXME
                            displayBoard();

                        }
            if (undo == event.getSource()) {

            }
            if (newGame == event.getSource()) {

            }
        }
    }
}
