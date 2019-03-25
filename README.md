# chessProject
## Objectives
- To design, implement, and test a small class hierarchy 
- To use two dimensional arrays and enum types and implement a GUI-base game 
working with a group on a project 

## Description
A program that implements a simple GUI program that allows two players to play a chess. The challenge was to design and organize pieces into a class hierarchy that utilizes polymorphism. 

**IChessModel.java ( UNCHANGED )** 

**IchessPiece.java ( UNCHANGED )** 

**Player.java ( UNCHANGED )** 

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
