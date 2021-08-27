package entity
/**
 * Entity to represent a player in the game "Schwimmen". Besides having a [Name] and the information
 * whether a the player has ([knocked]), or if has ([passed]),
 * there is also a ([place])which is an integer showing the plyer Seat.
 * @param hand shows which Cards has the player in hand.
 */
class Player (val name: String, val place: Int){
    val hand= mutableListOf<Card>()
    var knocked = false
    var passed = false
}