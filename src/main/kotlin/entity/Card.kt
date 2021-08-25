package entity
/**
 * Data class for the single typ of game elements that the game "Schwimmen" knows: cards.
 *
 * It is characterized by a [Suit] and a [Value]
 */
class Card(val cardsuit:Suit,val cardvalue:Value) {
    //Suit( CLUBS(Kreuz) -> "♣" ,SPADES(Pik) -> "♠", HEARTS(Herz) -> "♥" ,DIAMONDS(Karo) -> "♦")
    //Value(7,8,9,10,JACK(bube),QUEEN(Dame),KING(könig),ACE(Ass))...Jack,Queen,King=10Pnkt,ACE=11Pnkt
    //Suit needs to be the same to add the Values of cards together.
    /**
     * to String method ives the String value in an int value.
     * */
    override fun toString()="$cardsuit$cardvalue"
    fun getPoints():Int
    {
        when(cardvalue.toString())
        {
            "7"->return 7
            "8"->return 8
            "9"->return 9
            "10"->return 10
            "J"->return 10
            "Q"->return 10
            "K"->return 10
            "A"->return 11

        }
        return 0
    }
    /**
     * compares two [Card]s according to the [Enum.ordinal] value of their [Value]
     * (i.e., the order in which the suits are declared in the enum class)
     */
    operator fun compareTo(other: Card) = this.cardvalue.ordinal - other.cardvalue.ordinal
}