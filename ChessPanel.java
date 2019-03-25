package ChessVersion4;
/**********************************************************************
 * A graphical representation of a chess game.
 *
 * @author Amela Aganovic, Emily Linderman, Xue Hua
 * @version Winter 2019
 *********************************************************************/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.BorderLayout;

/**********************************************************************
 * A graphical representation of a chess game.
 *
 * @author Amela Aganovic, Emily Linderman, Xue Hua
 * @version Winter 2019
 *********************************************************************/
public class ChessPanel extends JPanel {

    /** array of chess board as JButtons */
    private JButton[][] board;

    /** current player type */
    private ChessModel model;

    /** white rook icon */
    private ImageIcon wRook;

    /** white bishop icon */
    private ImageIcon wBishop;

    /** white queen icon */
    private ImageIcon wQueen;

    /** white king icon */
    private ImageIcon wKing;

    /** white pawn icon */
    private ImageIcon wPawn;

    /** white knight icon */
    private ImageIcon wKnight;

    /** black rook icon */
    private ImageIcon bRook;

    /** black bishop icon */
    private ImageIcon bBishop;

    /** black queen icon */
    private ImageIcon bQueen;

    /** black king icon */
    private ImageIcon bKing;

    /** black pawn icon */
    private ImageIcon bPawn;

    /** black knight icon */
    private ImageIcon bKnight;

    /** flag status of chess piece */
    private boolean firstTurnFlag;

    /** current row co-ordinates of chess piece */
    private int fromRow;

    /** current column co-ordinates of chess piece */
    private int toRow;

    /** advancing row co-ordinates of chess piece */
    private int fromCol;

    /** advancing column co-ordinates of chess piece */
    private int toCol;

    /** undo button */
    private JButton undo;

    /** new game button */
    private JButton newGame;

    /** left castle button */
    private JButton castleLeft;

    /** right castle button */
    private JButton castleRight;

    /** current player turn label */
    private JLabel currentTurn;

    /** listener for gameplay */
    private listener listener;

    /**********************************************************************
     * Constructor for ChessPanel
     *********************************************************************/
    public ChessPanel() {
        model = new ChessModel();
        board = new JButton[model.numRows()][model.numColumns()];
        listener = new listener();

        // Buttons and JLabels
        undo = new JButton("Undo");
        undo.addActionListener(listener);

        newGame = new JButton("New Game");
        newGame.addActionListener(listener);

        castleLeft = new JButton("Castle Left");
        castleLeft.addActionListener(listener);

        castleRight = new JButton("Castle Right");
        castleRight.addActionListener(listener);
        currentTurn = new JLabel("Turn:  WHITE");

        // Panels
        JPanel boardpanel = new JPanel();

        JPanel buttonpanel = new JPanel();
        buttonpanel.setLayout(new BoxLayout(buttonpanel, BoxLayout.Y_AXIS));
        buttonpanel.add(Box.createRigidArea(new Dimension(30, 40)));

        buttonpanel.add(newGame);
        buttonpanel.add(undo);
        buttonpanel.add(castleLeft);
        buttonpanel.add(castleRight);

        buttonpanel.add(currentTurn);

        newGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        undo.setAlignmentX(Component.CENTER_ALIGNMENT);
        currentTurn.setAlignmentX(Component.CENTER_ALIGNMENT);
        castleLeft.setAlignmentX(Component.CENTER_ALIGNMENT);
        castleRight.setAlignmentX(Component.CENTER_ALIGNMENT);


        boardpanel.setLayout(new GridLayout(model.numRows(), model.numColumns()));

        createIcons();

        for (int r = 0; r < model.numRows(); r++) {
            for (int c = 0; c < model.numColumns(); c++) {
                if (model.pieceAt(r, c) == null) {
                    board[r][c] = new JButton("", null);
                    board[r][c].addActionListener(listener);
                } else if (model.pieceAt(r, c).player() == Player.WHITE)
                    placeWhitePieces(r, c);
                else if (model.pieceAt(r, c).player() == Player.BLACK)
                    placeBlackPieces(r, c);

                setBackGroundColor(r, c);
                boardpanel.add(board[r][c]);
            }
        }
        boardpanel.setPreferredSize(new Dimension(700, 600));

        setLayout(new BorderLayout());
        add(boardpanel, BorderLayout.CENTER);
        add(buttonpanel, BorderLayout.EAST);

        firstTurnFlag = true;
    }

    /**********************************************************************
     * Helper method that sets background color of board.
     *********************************************************************/
    private void setBackGroundColor(int r, int c) {
        if ((c % 2 == 1 && r % 2 == 0) || (c % 2 == 0 && r % 2 == 1)) {
            board[r][c].setBackground(Color.LIGHT_GRAY);
        } else if ((c % 2 == 0 && r % 2 == 0) || (c % 2 == 1 && r % 2 == 1)) {
            board[r][c].setBackground(Color.WHITE);
        }
    }

    /**********************************************************************
     * Helper method that places white pieces on board.
     * @param r argument for row
     * @param c argument for column
     *********************************************************************/
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

    /**********************************************************************
     * Helper method that places white pieces on board.
     * @param r argument for row
     * @param c argument for column
     *********************************************************************/
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

    /**********************************************************************
     * Helper method that creates icons for graphical representation of
     * icons.
     *********************************************************************/
    private void createIcons() {
        wRook = new ImageIcon("./src/ChessVersion4/images/wRook.png");
        wBishop = new ImageIcon("./src/ChessVersion4/images/wBishop.png");
        wQueen = new ImageIcon("./src/ChessVersion4/images/wQueen.png");
        wKing = new ImageIcon("./src/ChessVersion4/images/wKing.png");
        wPawn = new ImageIcon("./src/ChessVersion4/images/wPawn.png");
        wKnight = new ImageIcon("./src/ChessVersion4/images/wKnight.png");

        bRook = new ImageIcon("./src/ChessVersion4/images/bRook.png");
        bBishop = new ImageIcon("./src/ChessVersion4/images/bBishop.png");
        bQueen = new ImageIcon("./src/ChessVersion4/images/bQueen.png");
        bKing = new ImageIcon("./src/ChessVersion4/images/bKing.png");
        bPawn = new ImageIcon("./src/ChessVersion4/images/bPawn.png");
        bKnight = new ImageIcon("./src/ChessVersion4/images/bKnight.png");
    }

    /**********************************************************************
     * Helper method that handles highlighting of spaces when selected.
     * @param r argument for row
     * @param c argument for column
     * @param select argument that sets state of space
     *********************************************************************/
    private void toggleSpace(int r, int c, boolean select) {
        if (select) {
            board[r][c].setBackground(Color.PINK);
            System.out.println("Highlighting" + " [ " + r + " ] " + " [ " + c + " ] ");
        } else {
            setBackGroundColor(r, c);
            System.out.println("Un-Highlighting" + " [ " + r + " ] " + " [ " + c + " ] ");
        }
    }

    /**********************************************************************
     * Helper method that repaints or updates the board.
     *********************************************************************/
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

        if (model.inCheck(model.currentPlayer()))
            JOptionPane.showMessageDialog(null, "King in Check");
        if (model.isComplete())
            JOptionPane.showMessageDialog(null, "Checkmate");

    }

    /**********************************************************************
     * Inner class that represents action listener for buttons. Acts as the
     * trigger to gameplay and events.
     *********************************************************************/
    private class listener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (newGame == event.getSource()) {
                model.newGame();
                currentTurn.setText("Turn: White");
                displayBoard();
            }
            if (undo == event.getSource()) {
                model.undo();
                displayBoard();
                currentTurn.setText("Turn : " + model.currentPlayer()); //fixme
            }
            if (castleRight == event.getSource()) {
                model.castleKingSide();
                displayBoard();
            }
            if (castleLeft == event.getSource()) {
                model.castleQueenSide();
                displayBoard();
            }

            for (int r = 0; r < model.numRows(); r++)
                for (int c = 0; c < model.numColumns(); c++)
                    if (board[r][c] == event.getSource()) {

                        // Only execute if space is occupied and it is the piece's turn OR it is not the first Turn
                        if ((model.isOccupied(r, c) && model.pieceAt(r, c).player() == model.currentPlayer()) || !firstTurnFlag) {

                            if (firstTurnFlag) {
                                fromRow = r;
                                fromCol = c;
                                firstTurnFlag = false;
                                if (model.pieceAt(r, c).player() == model.currentPlayer())
                                    toggleSpace(fromRow, fromCol, true);

                            } else {
                                toRow = r;
                                toCol = c;
                                firstTurnFlag = true;

                                Move m = new Move(fromRow, fromCol, toRow, toCol);
                                toggleSpace(fromRow, fromCol, false);
                                if ((model.isValidMove(m))) {
                                    model.saveMove(fromRow, fromCol, toRow, toCol);
                                    model.move(m);
                                    model.setPassantable();
                                    if (model.isPromote()) {
                                        model.toPromote(toRow, toCol);
                                    }
                                    model.setNextPlayer();
                                }
                                currentTurn.setText("Turn : " + model.currentPlayer());
                                displayBoard();

                            }
                        }
                    }
        }
    }
}