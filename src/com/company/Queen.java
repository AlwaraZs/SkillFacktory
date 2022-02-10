package com.company;

public class Queen extends ChessPiece{
    public Queen(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if(!pointOnDeckc(line, column) || !pointOnDeckc(toLine, toColumn) || (line == toLine && column == toColumn)) return false;
        if(chessBoard.board[toLine][toColumn] != null && chessBoard.board[line][column].getColor().equals(chessBoard.board[toLine][toColumn].getColor())) return false;
        if(checkCross(line, column, toLine, toColumn) || checkDiagonals(line, column, toLine, toColumn)) return true;
        return false;
    }

    private boolean checkCross(int line, int column, int toLine, int toColumn){
        if (line != toLine && column != toColumn) return false;
        return true;
    }

    private boolean checkDiagonals(int line, int column, int toLine, int toColumn){
        int difLine = toLine - line;
        int difColomn = toColumn - column;
        if (column - difLine == toColumn || column + difLine == toColumn ||
                line - difColomn == toLine || line + difColomn == toLine) return true;
        return false;
    }
    @Override
    public String getSymbol() {
        return "Q";
    }
}
