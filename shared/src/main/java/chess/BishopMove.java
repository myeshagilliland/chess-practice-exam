package chess;

import java.util.ArrayList;
import java.util.Collection;

public class BishopMove {

    private ChessBoard board;
    private ChessPosition myPosition;
    private Collection<ChessMove> moves = new ArrayList<ChessMove>();

    public BishopMove(ChessBoard board, ChessPosition position) {
        this.board = board;
        this.myPosition = position;
    }

    public boolean checkAddPosition(int row, int col, ChessGame.TeamColor team) {
        ChessPosition possiblePosition = new ChessPosition(row, col);
        if (row < 1 || row > 8 || col < 1 || col > 8) {
            return false;
        } else if (board.getPiece(possiblePosition) == null) {
            moves.add(new ChessMove(myPosition, possiblePosition, null));
            return true;
        } else if (board.getPiece(possiblePosition).getTeamColor() != team) {
            moves.add(new ChessMove(myPosition, possiblePosition, null));
            return false;
        } else {return false;}

    }

    public Collection<ChessMove> getMoves() {
        int row = myPosition.getRow();
        int col = myPosition.getColumn();
        ChessGame.TeamColor team = board.getPiece(myPosition).getTeamColor();

        //to top right
        for (int i = 1; i < 8; i++) {
            int new_row = row + i;
            int new_col = col + i;
            boolean validMove = checkAddPosition(new_row, new_col, team);
            if (!validMove) {break;}
        }

        //to bottom left
        for (int i = 1; i < 8; i++) {
            int new_row = row - i;
            int new_col = col - i;
            boolean validMove = checkAddPosition(new_row, new_col, team);
            if (!validMove) {break;}
        }

        //to top left
        for (int i = 1; i < 8; i++) {
            int new_row = row + i;
            int new_col = col - i;
            boolean validMove = checkAddPosition(new_row, new_col, team);
            if (!validMove) {break;}
        }

        //to bottom right
        for (int i = 1; i < 8; i++) {
            int new_row = row - i;
            int new_col = col + i;
            boolean validMove = checkAddPosition(new_row, new_col, team);
            if (!validMove) {break;}
        }

        return moves;
    }

}
