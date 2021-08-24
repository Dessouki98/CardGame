package entity
/**
 * Entity to represent a player in the game "Schwimmen". Besides having a [Name] and the information
 * whether a the player has ([knocked]), or if has ([passed]),there is also a ([place]) which is an integer showing the plyer Seat.
 * @param hand shows which Cards has the player in hand.
 */
class Player(
    val name:String?=null,
    val place:Int=0,
    var knocked:Boolean=false,
    var passed:Boolean=false,
    var hand: MutableList<Card>

)