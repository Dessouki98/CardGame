package service

import entity.Card
import entity.Deck
import entity.Suit
import entity.Value

class PlayAreaService (private val root:SchwimmenService){
    fun createDeck():Deck
    {
        val allCards: MutableList<Card> = defaultRandomCardList()
        val myDeck=Deck(allCards)
        return myDeck
    }
    /**
     * Creates a shuffled 32 cards list of all four suits and cards
     * from 7 to Ace
     */
    private fun defaultRandomCardList() = MutableList(32){ index ->
        Card(
            Suit.values()[index / 8],
            Value.values()[(index % 8) + 5]
        )
    }.shuffled().toMutableList()

    fun renewMiddleCards()
    {
        val game=root.currentGame
        checkNotNull(game)
        val myDeck=game.playArea.deck
        val CardOnTable1 = myDeck.draw(1).get(0)
        val CardOnTable2 = myDeck.draw(1).get(0)
        val CardOnTable3 = myDeck.draw(1).get(0)
        val CardsOnTable= mutableListOf<Card>(CardOnTable1,CardOnTable2,CardOnTable3)
        refresh
    }
    fun hasDeckEnoughCards():Boolean{
        val game=root.currentGame
        checkNotNull(game)
        val myDeck=game.playArea.deck
        return myDeck.listOfCards.size>3
    }
}