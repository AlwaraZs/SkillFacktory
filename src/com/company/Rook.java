package com.company;

public class Rook extends ChessPiece{
    public Rook(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!pointOnDeckc(line, column) || !pointOnDeckc(toLine, toColumn) ||
                (line == toLine && column == toColumn) || (line != toLine && column != toColumn)) return false;
        return chessBoard.board[toLine][toColumn] == null || !chessBoard.board[line][column].getColor().equals(chessBoard.board[toLine][toColumn].getColor());
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}
