package entity
/**
 * An Entity that conncets both the Deck and the cards on the table together.
 */
class PlayArea {
    val deck:Deck?=null
    val cards = mutableListOf<Card>()
}