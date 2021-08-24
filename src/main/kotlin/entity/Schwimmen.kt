package entity
/**
 * Entity class that represents a game state of "Schwimmen". As all card stack information is stored
 * in the [playArea] entity, All player Information is stored in ([Player]),and the Gamestate is stored in ([GameState])
 * this class is just a wrapper for two player- four Player objects,the Gamestatus,and the Cards Information.
 */
class Schwimmen(val player1: Player, val player2: Player, val player3: Player?=null, val player4: Player?=null) {
    val activePlayer=0
    val state:GameState?=null
    val playArea:PlayArea?=null
}