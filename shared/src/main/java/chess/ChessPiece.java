package chess;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private ChessGame.TeamColor pieceColor;
    private ChessPiece.PieceType type;
    private Collection<ChessMove> moves = new ArrayList<ChessMove>();

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        if (board.getPiece(myPosition).getPieceType() == PieceType.ROOK) {
            return new RookMove(board, myPosition).getMoves();
        } else if (board.getPiece(myPosition).getPieceType() == PieceType.BISHOP) {
            return new BishopMove(board, myPosition).getMoves();
        } else if (board.getPiece(myPosition).getPieceType() == PieceType.QUEEN) {
            return new QueenMove(board, myPosition).getMoves();
        } else if (board.getPiece(myPosition).getPieceType() == PieceType.KING) {
            return new KingMove(board, myPosition).getMoves();
        } else if (board.getPiece(myPosition).getPieceType() == PieceType.KNIGHT) {
            return new KnightMove(board, myPosition).getMoves();
        } else if (board.getPiece(myPosition).getPieceType() == PieceType.PAWN) {
            return new PawnMove(board, myPosition).getMoves();
        }
        return moves;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessPiece that = (ChessPiece) o;
        return pieceColor == that.pieceColor && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, type);
    }
}
