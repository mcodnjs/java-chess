package chess.domain.piece;

public enum PieceType {
    KING("k"),
    QUEEN("q"),
    ROOK("r"),
    KNIGHT("n"),
    BISHOP("b"),
    PAWN("p");

    private final String name;

    PieceType(final String name) {
        this.name = name;
    }
}