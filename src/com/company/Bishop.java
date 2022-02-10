package com.company;

public class Bishop extends ChessPiece {
    public Bishop(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!pointOnDeckc(line, column) || !pointOnDeckc(toLine, toColumn) || (line == toLine && column == toColumn)) return false;
        if(chessBoard.board[toLine][toColumn] != null && chessBoard.board[line][column].getColor().equals(chessBoard.board[toLine][toColumn].getColor())) return false;
        int difLine = toLine - line;
        int difColomn = toColumn - column;
        return column - difLine == toColumn || column + difLine == toColumn ||
                line - difColomn == toLine || line + difColomn == toLine;
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}