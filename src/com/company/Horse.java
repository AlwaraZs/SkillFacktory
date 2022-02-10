package com.company;

public class Horse extends ChessPiece{

    public Horse(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!pointOnDeckc(line, column) || !pointOnDeckc(toLine, toColumn)) return false;
        if(chessBoard.board[toLine][toColumn] != null && chessBoard.board[line][column].getColor().equals(chessBoard.board[toLine][toColumn].getColor())) return false;
        if((line + 2 == toLine && column + 1 == toColumn) || (line + 2 == toLine && column - 1 == toColumn) ||
                (line - 2 == toLine && column + 1 == toColumn) || (line - 2 == toLine && column - 1 == toColumn) ||
                (line + 1 == toLine && column + 2 == toColumn) || (line + 1 == toLine && column - 2 == toColumn) ||
                (line - 1 == toLine && column + 2 == toColumn) || (line - 1 == toLine && column - 2 == toColumn)) return true;
        return false;
    }

    @Override
    public String getSymbol() {
        return "H";
    }
}
