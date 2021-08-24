package entity

class Schwimmen(val player1: Player, val player2: Player, val player3: Player?=null, val player4: Player?=null) {
    val activePlayer=0
    val state:GameState?=null
    val playArea:PlayArea?=null
}