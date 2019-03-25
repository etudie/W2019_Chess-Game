package Project3;
/*	ChessPanel.java
This class is responsible for
1. presenting the GUI
2. respond to user actions
3. updates view
4. allows white to move before black
5. allows only valid moves
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.BorderLayout;

public class ChessPanel extends JPanel {

    private JButton[][] board;
    private ChessModel model;

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

    private JButton undo;
    private JButton newGame;
    private JButton castleLeft;
    private JButton castleRight;
    private JLabel lastMove;
    private JLabel currentTurn;
    private JButton temp;
    // declare other intance variables as needed

    private listener listener;

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

//        lastMove = new JLabel("Last Move:  none");
        currentTurn = new JLabel("Turn:  White");

        temp = new JButton("AI");
        temp.addActionListener(listener);

        // Panels
        JPanel boardpanel = new JPanel();

        JPanel buttonpanel = new JPanel();
        buttonpanel.setLayout(new BoxLayout(buttonpanel, BoxLayout.Y_AXIS));
        buttonpanel.add(Box.createRigidArea(new Dimension(30,40)));

        buttonpanel.add(newGame);
        buttonpanel.add(undo);
        buttonpanel.add(castleLeft);
        buttonpanel.add(castleRight);
        buttonpanel.add(temp);

        buttonpanel.add(currentTurn);

        newGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        undo.setAlignmentX(Component.CENTER_ALIGNMENT);
        currentTurn.setAlignmentX(Component.CENTER_ALIGNMENT);
        castleLeft.setAlignmentX(Component.CENTER_ALIGNMENT);
        castleRight.setAlignmentX(Component.CENTER_ALIGNMENT);
        temp.setAlignmentX(Component.CENTER_ALIGNMENT);
        temp.setEnabled(false);


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
        if (model.inCheck(Player.WHITE))
            JOptionPane.showMessageDialog(null, "White King in Check");
        if (model.inCheck(Player.BLACK))
            JOptionPane.showMessageDialog(null, "Black King in Check");
        if (model.isComplete())
            JOptionPane.showMessageDialog(null, "Checkmate");
    }

    // inner class that represents action listener for buttons
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
                currentTurn.setText("Turn : " + model.currentPlayer());
                if (model.currentPlayer().equals(Player.BLACK))
                    temp.setEnabled(true);
                else
                    temp.setEnabled(false);
            }
            if (castleRight == event.getSource()) {
                if (model.castleKingSide()) {
                    displayBoard();
                    model.setNextPlayer();
                    currentTurn.setText("Turn : " + model.currentPlayer());
                }
            }
            if (castleLeft == event.getSource()) {
                if (model.castleQueenSide()) {
                    displayBoard();
                    model.setNextPlayer();
                    currentTurn.setText("Turn : " + model.currentPlayer());
                }
            }
            if (temp == event.getSource()) {
                model.AI();
                displayBoard();
                if (!model.isComplete())
                    model.setNextPlayer();
                currentTurn.setText("Turn : " + model.currentPlayer());
                temp.setEnabled(false);
            }

            for (int r = 0; r < model.numRows(); r++)
                for (int c = 0; c < model.numColumns(); c++)
                    if (board[r][c] == event.getSource()) {

                        // Only execute if space is occupied and it is the piece's turn OR it is not the first Turn
                        if ((model.isOccupied(r, c) && model.pieceAt(r, c).player() == model.currentPlayer()) || !firstTurnFlag) {

                            if (firstTurnFlag == true) {
                                fromRow = r;
                                fromCol = c;
                                firstTurnFlag = false;
                                if (model.pieceAt(r, c).player() == model.currentPlayer())
                                    toggleSpace(fromRow, fromCol, true);

                            }
                            else {
                                toRow = r;
                                toCol = c;
                                firstTurnFlag = true;

                                Move m = new Move(fromRow, fromCol, toRow, toCol);
                                toggleSpace(fromRow, fromCol, false);
                                if ((model.isValidMove(m)) == true) {

//                                    toggleSpace(fromRow, fromCol, false);
                                    model.saveMove(fromRow, fromCol, toRow, toCol);
                                    model.move(m);
                                    model.setPassantable();
                                    if (model.ifPromote())
                                        model.toPromote(toRow, toCol);
                                    model.setNextPlayer();
                                    temp.setEnabled(true);
//                                    model.AI();
//                                    displayBoard();
//                                    model.setNextPlayer();

                                }
//                                lastMove.setText(m.toString()); // FIXME
                                currentTurn.setText("Turn : " + model.currentPlayer()); // FIXME
                                displayBoard();

                            }
                        }
                    }
        }
    }
}