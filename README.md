
# chessProject

Last updated: 16 March 2019 
- Highlight Space when selected 
- Displays current turn colour 
- inCheck() for pawn, knight, rook
- Pawns no longer capture same coloured pieces 
- Pieces rightfully operates in turns (white then black then white, etc. ) 
Read full Log here
- New Game Functionality

|PAWN| KING |
|--|--|
| - if first turn, move 2 spaces  | - move to one of surrounding 8 spaces |
| - else, move 1 space | - except when end of row  |
| - moves diagonally to capture  | - can move forwards and backwards |
| - cannot move backwards |  |
| - is promoted at end of board to another piece |  |

|QUEEN| KNIGHT|
|--|--|
| - moves diagonally, forward, backwards  | - 2 away horizontally and  1 vertically |
| - unlimited paces except end of board | - or 2 vertically and 1 horizontally.  |
|   | - moves in opposite colors|


|ROOK| BISHOP|
|--|--|
| - moves horizontally / vertically | - moves diagonally |
| - unlimited paces| |
| - involved in castle-ing | |




![](https://i.ibb.co/XSnx4GY/one.gif)
**GIF recording of Functionality**

No changes below this line

--------------------------------------------------------------------------------

## Objectives
- To design, implement, and test a small class hierarchy 
- To use two dimensional arrays and enum types and implement a GUI-base game 
working with a group on a project 

## Description
A program that implements a simple GUI program that allows two players to play a chess. The challenge was to design and organize pieces into a class hierarchy that utilizes polymorphism. 

**FEATURES**

HIGHLIGHTING

![highlight](https://i.imgur.com/OiuFpYe.png)

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
| ![BEFORE](http://g.recordit.co/Q7A2C6SdWL.gif) | ![AFTER](https://i.ibb.co/XSnx4GY/one.gif) |
