package com.company;

public class Pawn extends ChessPiece{
    public Pawn(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        //the pawn eats crosswise, moves only forward
        if (!pointOnDeckc(line, column) || !pointOnDeckc(toLine, toColumn)) return false;
        if(chessBoard.board[toLine][toColumn] != null && chessBoard.board[line][column].getColor().equals(chessBoard.board[toLine][toColumn].getColor())) return false;
        if (chessBoard.board[toLine][toColumn] == null){
            if(color.equals("White") && column == toColumn && ((line == 1 && line + 2 == toLine) || (line + 1 == toLine))) return true;
            return color.equals("Black") && column == toColumn && ((line == 6 && line - 2 == toLine) || (line - 1 == toLine));
        }else{//can be only enemy piece
            if(color.equals("White") && line + 1 == toLine && ((column + 1 == toColumn) || (column - 1 == toColumn))) return true;
            return color.equals("Black") && line - 1 == toLine && ((column + 1 == toColumn) || (column - 1 == toColumn));
        }
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
