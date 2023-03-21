package chess.domain.piece.pawn;

import static chess.domain.Team.BLACK;
import static chess.domain.Team.WHITE;
import static chess.domain.movement.Movement.DISCONTINUOUS_DIAGONAL;

import chess.domain.Team;
import chess.domain.movement.Movement;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

public abstract class PawnPiece extends Piece {

    private static final int MOVABLE_RANK_BOUNDARY = 0;
    private static final int IMMOVABLE_FILE_BOUNDARY = 0;


    public PawnPiece(final Team team, final PieceType pieceType) {
        super(team, pieceType);
    }

    @Override
    public boolean isValidMove(int fileInterval, int rankInterval, Piece target) {
        Movement movement = Movement.of(fileInterval, rankInterval);
        if (!getPieceType().getMovements().contains(movement)) {
            throw new IllegalArgumentException("말이 이동할 수 없는 규칙입니다.");
        }

        if (getTeam() == WHITE && rankInterval < MOVABLE_RANK_BOUNDARY) {
            throw new IllegalArgumentException("화이트 폰은 위로만 움직일 수 있습니다.");
        }

        if (getTeam() == BLACK && rankInterval > MOVABLE_RANK_BOUNDARY) {
            throw new IllegalArgumentException("블랙 폰은 아래로만 움직일 수 있습니다.");
        }

        if (DISCONTINUOUS_DIAGONAL.equals(movement) && !isOppositeTeam(target)) {
            throw new IllegalArgumentException("상대 팀이 위치하는 경우만 대각선으로 이동할 수 있습니다.");
        }

        if (!DISCONTINUOUS_DIAGONAL.equals(movement) && Math.abs(fileInterval) > IMMOVABLE_FILE_BOUNDARY) {
            throw new IllegalArgumentException("말이 이동할 수 없는 규칙입니다.");
        }

        return validateInitialMovement(fileInterval, rankInterval);
    }

    private boolean isOppositeTeam(final Piece target) {
        return Team.change(getTeam()) == target.getTeam();
    }

    @Override
    public final boolean isValidTeam(final Piece target) {
        return getTeam() != target.getTeam();
    }

    public abstract boolean validateInitialMovement(final int fileInterval, final int rankInterval);

    @Override
    public abstract boolean isInitialPawn();
}