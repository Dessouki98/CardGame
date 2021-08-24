package entity
/**
 * Enum to distinguish between the four possible Game States:
 * ([GAME_ENDED]):Represents the Case when the Game Ends
 * ([GAME_RUNNING]):Represents the Case when the Game is currently running
 * ([GAME_EVALUATION]):Represents the Case when the Game is currently being Evaluated.
 */
enum class GameState {
    GAME_ENDED,
    GAME_RUNNING,
    GAME_EVALUATION;
}