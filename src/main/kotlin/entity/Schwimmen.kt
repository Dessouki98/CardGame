package entity
/**
 * Entity class that represents a game state of "Schwimmen". As all card stack information is stored
 * in the [playArea] entity, All player Information is stored in ([Player]),and the Gamestate is stored in ([GameState])
 * this class is just a wrapper for two player- four Player objects,the Gamestatus,and the Cards Information.
 */
class Schwimmen (val players: MutableList<Player>, val playArea: PlayArea){
    public var activePlayer = 0
    public var gameState:GameState=GameState.GAME_RUNNING
}