# chessProject

Last updated: 04 March 2019 
- Pawns on the Chessboard
- isValidMove() for PAWN & KING
- Emily working on Queen & Rook , Amela on Knight & Bishop

|PAWN| KING |
|--|--|
| - if first turn, move 2 spaces  | - move to one of surrounding 8 spaces |
| - else, move 1 space | - except when end of row  |
| - moves diagonally to capture  | - can move forwards and backwards |
| - cannot move backwards |  |

![](http://g.recordit.co/Q7A2C6SdWL.gif)
**GIF recording of Functionality**

No changes below this line

--------------------------------------------------------------------------------

## Objectives
- To design, implement, and test a small class hierarchy 
- To use two dimensional arrays and enum types and implement a GUI-base game 
working with a group on a project 

## Description
A program that implements a simple GUI program that allows two players to play a chess. The challenge was to design and organize pieces into a class hierarchy that utilizes polymorphism. 

- **IChessModel.java ( UNCHANGED )** 
- **IchessPiece.java ( UNCHANGED )** 
- **Player.java ( UNCHANGED )** 

**ChessModel.java**

Class responsible for storing the chessboard and implementing the game logic
- `isComplete()`  
- `isValidMove(Move move)` checks if move is valid
- `isComplete(Move move)` 
- `move()` 
- `inCheck(Player p)`
- `Player currentPlayer()`
- `IChessPiece pieceAt()`

**ChessPanel.java extends `JPanel`**

Class is responsible for presenting the GUI and responding to user actions and updating the view. Allows only valid moves and ensures white moves before black. 
- `displayBoard()` updates board

**ChessPiece.java implements `IChessPiece`** 

- `type()` is abstract.
- `isValidMove()`  verify that start/end locations are different. Performs checks.

**ChessGUI.java**

Contains main method that creates and displays the chess game GUI. Use chess piece icons. 

**GUIcodes.java**

 `isValidMove()` Check for all pieces
 - Pawn.java extends `ChessPiece` 
 - Rook.java extends `ChessPiece`
- King.java extends `ChessPiece` 
- Queen.java extends `ChessPiece`
- Bishop.java extends `ChessPiece`

![](https://i.imgur.com/DFecr70.png)
**UML Diagram**

| BEFORE | AFTER |
|--|--|
| ![BEFORE](https://camo.githubusercontent.com/13df35400361913ddeb16bc09a0de6bcb43104dd/687474703a2f2f672e7265636f726469742e636f2f4e476e396150647056702e676966) | ![AFTER](http://g.recordit.co/Q7A2C6SdWL.gif) |
