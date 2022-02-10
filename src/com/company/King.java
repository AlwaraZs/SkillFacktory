package com.company;


public class King extends ChessPiece{
    public King(String color) {
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
        //2 короля не могут атаковать друг друга
        if(chessBoard.board[toLine][toColumn] != null && chessBoard.board[toLine][toColumn].getSymbol().equals("K")) return false;
        if(line != toLine && column != toColumn){
            if ((column - 1 == toColumn || column + 1 == toColumn) && (line - toLine == 1 || line - toLine == -1)){
                int index = chessBoard.nowPlayer.equals("While") ? 0 : 1;
                chessBoard.kingsPos[index][0] = toLine;
                chessBoard.kingsPos[index][1] = toColumn;
                return true;
            }
        }else{
            if (column + 1 == toColumn || column - 1 == toColumn || line + 1 == toLine || line - 1 == toLine){
                int index = chessBoard.nowPlayer.equals("While") ? 0 : 1;
                chessBoard.kingsPos[index][0] = toLine;
                chessBoard.kingsPos[index][1] = toColumn;
                return true;
            }
        }
        return false;
    }

    public boolean isUnderAttack(ChessBoard chessBoard, int line, int column){
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                ChessPiece currFigure = chessBoard.board[i][j];
                if(currFigure != null && !currFigure.getColor().equals(getColor()) &&
                chessBoard.board[i][j].canMoveToPosition(chessBoard, i, j, line, column)) return true;
            }
        }
        return false;
    }


    @Override
    public String getSymbol() {
        return "K";
    }
}
