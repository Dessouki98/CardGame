package entity

class Card(val cardsuit:Suit,val cardvalue:Value) {
    //Suit( CLUBS(Kreuz) -> "♣" ,SPADES(Pik) -> "♠", HEARTS(Herz) -> "♥" ,DIAMONDS(Karo) -> "♦")
    //Value(7,8,9,10,JACK(bube),QUEEN(Dame),KING(könig),ACE(Ass))...Jack,Queen,King=10Pnkt,ACE=11Pnkt
    //Suit needs to be the same to add the Values of cards together
    override fun toString()="$cardsuit$cardvalue"
    fun getPoints()
    {
        when(cardvalue.toString())
        {
            "7"->7
            "8"->8
            "9"->9
            "10"->10
            "J"->10
            "Q"->10
            "K"->10
            "A"->11

        }
    }
}