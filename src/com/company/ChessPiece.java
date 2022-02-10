package com.company;

public abstract class ChessPiece {
    String color;
    boolean check = true;

    public ChessPiece(String color){
        this.color = color;
    }

    public abstract String getColor();

    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    public abstract String getSymbol();

    boolean pointOnDeckc(int line, int column){
        return 0 <= line && line < 8 && 0 <= column && column < 8;
    }
}
