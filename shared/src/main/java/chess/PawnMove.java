package chess;

import java.util.ArrayList;
import java.util.Collection;

public class PawnMove {

    private ChessBoard board;
    private ChessPosition myPosition;
    private Collection<ChessMove> moves = new ArrayList<ChessMove>();

    public PawnMove(ChessBoard board, ChessPosition position) {
        this.board = board;
        this.myPosition = position;
    }

//    public boolean checkAddPosition(int row, int col, ChessGame.TeamColor team) {
//        ChessPosition possiblePosition = new ChessPosition(row, col);
//        if (row < 1 || row > 8 || col < 1 || col > 8) {
//            return false;
//        } else if (board.getPiece(possiblePosition) == null) {
//            moves.add(new ChessMove(myPosition, possiblePosition, null));
//            return true;
//        } else if (board.getPiece(possiblePosition).getTeamColor() != team) {
//            moves.add(new ChessMove(myPosition, possiblePosition, null));
//            return false;
//        } else {return false;}
//
//    }

    public boolean onBoard(ChessPosition possiblePosition) {
        int row = possiblePosition.getRow();
        int col = possiblePosition.getColumn();
        if (row < 1 || row > 8 || col < 1 || col > 8) {
            return false;
        }
        return true;
    }

    public void checkAddPromote(ChessPosition possiblePosition, ChessGame.TeamColor team) {
        int row = possiblePosition.getRow();
        if ((team == ChessGame.TeamColor.WHITE && row == 8) || (team == ChessGame.TeamColor.BLACK && row == 1)) {
            moves.add(new ChessMove(myPosition, possiblePosition, ChessPiece.PieceType.ROOK));
            moves.add(new ChessMove(myPosition, possiblePosition, ChessPiece.PieceType.KNIGHT));
            moves.add(new ChessMove(myPosition, possiblePosition, ChessPiece.PieceType.QUEEN));
            moves.add(new ChessMove(myPosition, possiblePosition, ChessPiece.PieceType.BISHOP));
        } else {moves.add(new ChessMove(myPosition, possiblePosition, null));}
    }

    public Collection<ChessMove> getMoves() {
        int row = myPosition.getRow();
        int col = myPosition.getColumn();
        ChessGame.TeamColor team = board.getPiece(myPosition).getTeamColor();

        ChessPosition possiblePosition;
        if (team == ChessGame.TeamColor.WHITE) {
            possiblePosition = new ChessPosition(row+1, col);
        } else {
            possiblePosition = new ChessPosition(row-1, col);
        }
        if (onBoard(possiblePosition) && board.getPiece(possiblePosition) == null) {
            checkAddPromote(possiblePosition, team);
            if (team == ChessGame.TeamColor.WHITE && row == 2) {
                possiblePosition = new ChessPosition(row + 2, col);
                if (onBoard(possiblePosition) && board.getPiece(possiblePosition) == null) {
                    checkAddPromote(possiblePosition, team);
                }
            } else if (team == ChessGame.TeamColor.BLACK && row == 7) {
                possiblePosition = new ChessPosition(row - 2, col);
                if (onBoard(possiblePosition) && board.getPiece(possiblePosition) == null) {
                    checkAddPromote(possiblePosition, team);
                }
            }
        }

        //diagonal right capture
        if (team == ChessGame.TeamColor.WHITE) {
            possiblePosition = new ChessPosition(row+1, col+1);
        } else {
            possiblePosition = new ChessPosition(row-1, col +1);
        }
        if (onBoard(possiblePosition) && board.getPiece(possiblePosition) != null && board.getPiece(possiblePosition).getTeamColor() != team) {
            checkAddPromote(possiblePosition, team);
        }

        //diagonal left capture
        if (team == ChessGame.TeamColor.WHITE) {
            possiblePosition = new ChessPosition(row+1, col-1);
        } else {
            possiblePosition = new ChessPosition(row-1, col-1);
        }
        if (onBoard(possiblePosition) && board.getPiece(possiblePosition) != null && board.getPiece(possiblePosition).getTeamColor() != team) {
            checkAddPromote(possiblePosition, team);
        }

        return moves;
    }

}
