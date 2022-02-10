package com.company;

public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    String nowPlayer;
    int [][] kingsPos = new int[2][2];// 0 - while(0 - line, 1 - column) 1 - black

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
        kingsPos[0][0] = 0; kingsPos[0][1] = 4;
        kingsPos[1][0] = 7; kingsPos[1][1] = 4;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        //if(board[startLine][startColumn] == null) return false;
        if (checkPos(startLine) && checkPos(startColumn)) {

            if (!nowPlayer.equals(board[startLine][startColumn].getColor())) return false;

            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                if (board[startLine][startColumn].getSymbol().equals("K") ||  // check position for castling
                        board[startLine][startColumn].getSymbol().equals("R")) {
                    board[startLine][startColumn].check = false;
                }
                //pawn becomes queen or normal move
                if(board[startLine][startColumn].getSymbol().equals("P") && ((this.nowPlayer.equals("White") && endLine == 7) || (this.nowPlayer.equals("Black") && endLine == 0))){
                    board[startLine][startColumn] = null;
                    board[endLine][endColumn] = new Queen(this.nowPlayer);
                }else {
                    board[endLine][endColumn] = board[startLine][startColumn]; // if piece can move, we moved a piece
                    board[startLine][startColumn] = null; // set null to previous cell
                }

                //если на этом ходу ставят шах
                if(kingUnderAttack()) {
                    System.out.println("ШАХ!");
                }else

                this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";

                return true;
            } else return false;
        } else return false;
    }

    //король под атакой
    private boolean kingUnderAttack(){
        int index = nowPlayer.equals("White") ? 1 : 0;
        return new King(nowPlayer.equals("White") ? "Black" : "White").isUnderAttack(this, kingsPos[index][0], kingsPos[index][1]);
    }
    //поставил ли этим ходом мат - значение игрока меняется в меоде moveToPosition()
    public String itIsCheck(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(board[i][j].getSymbol().equals("K") || board[i][j] == null)
                    if(i == 7 && j == 7) return "(Пат, ничья!)";
            }
        }
        //нужен еще кусок кода что короля никто не может защитить не оч понятно как без полного обхода сделать
        boolean ans = true;
        int index = nowPlayer.equals("While") ? 0 : 1;
        int arrPos [] [] = {
                {kingsPos[index][0], kingsPos[index][1] + 1}, {kingsPos[index][0] + 1, kingsPos[index][1] + 1}, {kingsPos[index][0] + 1, kingsPos[index][1]},
                {kingsPos[index][0], kingsPos[index][1] - 1}, {kingsPos[index][0] - 1, kingsPos[index][1] - 1}, {kingsPos[index][0] - 1, kingsPos[index][1]},
                {kingsPos[index][0] + 1, kingsPos[index][1] - 1}, {kingsPos[index][0] - 1, kingsPos[index][1] + 1}
        };
        for(int [] arr : arrPos){
            if(new Rook(nowPlayer).pointOnDeckc(arr[0], arr[1]) && new King(nowPlayer).isUnderAttack(this, arr[0], arr[1])) {
                ans = false;
                break;
            }
        }
        if(ans){
            if(new King(nowPlayer).isUnderAttack(this, kingsPos[index][0], kingsPos[index][1])){
                return String.format(" и Мат! %s пробедил!!!", nowPlayer.equals("While") ? "Black" : "While");
            }
            return "Пат, ничья!";
        }
        return null;
    }


    public void printBoard() {  //print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");
        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public boolean castling0() {
        if (nowPlayer.equals("White")) {
            if (board[0][0] == null || board[0][4] == null) return false;
            if (board[0][0].getSymbol().equals("R") && board[0][4].getSymbol().equals("K") && // check that King and Rook
                    board[0][1] == null && board[0][2] == null && board[0][3] == null) {              // never moved
                if (board[0][0].getColor().equals("White") && board[0][4].getColor().equals("White") &&
                        board[0][0].check && board[0][4].check &&
                        !new King("White").isUnderAttack(this, 0, 2)) { // check that position not in under attack
                    board[0][4] = null;
                    board[0][2] = new King("White");   // move King
                    board[0][2].check = false;
                    board[0][0] = null;
                    board[0][3] = new Rook("White");   // move Rook
                    board[0][3].check = false;
                    nowPlayer = "Black";  // next turn
                    return true;
                } else return false;
            } else return false;
        } else {
            if (board[7][0] == null || board[7][4] == null) return false;
            if (board[7][0].getSymbol().equals("R") && board[7][4].getSymbol().equals("K") && // check that King and Rook
                    board[7][1] == null && board[7][2] == null && board[7][3] == null) {              // never moved
                if (board[7][0].getColor().equals("Black") && board[7][4].getColor().equals("Black") &&
                        board[7][0].check && board[7][4].check &&
                        !new King("Black").isUnderAttack(this, 7, 2)) { // check that position not in under attack
                    board[7][4] = null;
                    board[7][2] = new King("Black");   // move King
                    board[7][2].check = false;
                    board[7][0] = null;
                    board[7][3] = new Rook("Black");   // move Rook
                    board[7][3].check = false;
                    nowPlayer = "White";  // next turn
                    return true;
                } else return false;
            } else return false;
        }
    }

    public boolean castling7() {
        if (nowPlayer.equals("White")) {
            if (board[0][7] == null || board[0][4] == null) return false;
            if (board[0][7].getSymbol().equals("R") && board[0][4].getSymbol().equals("K") && // check that King and Rook
                    board[0][6] == null && board[0][5] == null) {                             // never moved
                if (board[0][7].getColor().equals("White") && board[0][4].getColor().equals("White") &&
                        board[0][7].check && board[0][4].check &&
                        !new King("White").isUnderAttack(this, 0, 6)) { // check that position nit in under attack
                    board[0][4] = null;
                    board[0][6] = new King("White");   // move King
                    board[0][6].check = false;
                    board[0][7] = null;
                    board[0][5] = new Rook("White");   // move Rook
                    board[0][5].check = false;
                    nowPlayer = "Black";  // next turn
                    return true;
                } else return false;
            } else return false;
        } else {
            if (board[7][7] == null || board[7][4] == null) return false;
            if (board[7][7].getSymbol().equals("R") && board[7][4].getSymbol().equals("K") && // check that King and Rook
                    board[7][6] == null && board[7][5] == null) {                             // never moved
                if (board[7][7].getColor().equals("Black") && board[7][4].getColor().equals("Black") &&
                        board[7][7].check && board[7][4].check &&
                        !new King("Black").isUnderAttack(this, 7, 6)) { // check that position not in under attack
                    board[7][4] = null;
                    board[7][6] = new King("Black");   // move King
                    board[7][6].check = false;
                    board[7][7] = null;
                    board[7][5] = new Rook("Black");   // move Rook
                    board[7][5].check = false;
                    nowPlayer = "White";  // next turn
                    return true;
                } else return false;
            } else return false;
        }
    }

}
