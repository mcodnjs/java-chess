package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.domain.Team;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RookTest {
    Rook rook = new Rook(Team.BLACK);

    @ParameterizedTest
    @CsvSource({"1,2"})
    void canMove_fail(int fileInterval, int rankInterval) {
        assertThrows(IllegalArgumentException.class,
                () -> rook.validateMovement(fileInterval, rankInterval));    }

    @ParameterizedTest
    @CsvSource({"3,0"})
    void canMove_success(int fileInterval, int rankInterval) {
        assertDoesNotThrow(() -> rook.validateMovement(fileInterval, rankInterval));
    }
}
