package service
import entity.Card
import entity.Deck
import entity.Suit
import entity.Value
/**
 * the PlayAreaService is the one Responsible for all actions belonging to the playArea Field
 */
class PlayAreaService(private val root: SchwimmenService) : AbstractRefreshingService() {
    /**
     *Creates a Deck with 32 Cards and is then used throw the game.
     */
    fun createDeck(): Deck {
        val allCards: MutableList<Card> = defaultRandomCardList()
        return Deck(allCards)
    }

    /**
     * Creates a shuffled 32 cards list of all four suits and cards
     * from 7 to Ace
     */
    private fun defaultRandomCardList() =listOf(
        Card(Suit.CLUBS, Value.QUEEN),
        Card(Suit.SPADES, Value.TEN),
        Card(Suit.DIAMONDS, Value.SEVEN),
        Card(Suit.CLUBS, Value.EIGHT),
        Card(Suit.CLUBS, Value.NINE),
        Card(Suit.HEARTS, Value.KING),
        Card(Suit.DIAMONDS, Value.QUEEN),
        Card(Suit.SPADES, Value.QUEEN),
        Card(Suit.DIAMONDS, Value.JACK),
        Card(Suit.SPADES, Value.SEVEN),
        Card(Suit.DIAMONDS, Value.KING),
        Card(Suit.DIAMONDS, Value.NINE),
        Card(Suit.SPADES, Value.EIGHT),
        Card(Suit.HEARTS, Value.TEN),
        Card(Suit.HEARTS, Value.EIGHT),
        Card(Suit.CLUBS, Value.JACK),
        Card(Suit.HEARTS, Value.ACE),
        Card(Suit.SPADES, Value.NINE),
        Card(Suit.CLUBS, Value.ACE),
        Card(Suit.SPADES, Value.JACK),
        Card(Suit.HEARTS, Value.SEVEN),
        Card(Suit.CLUBS, Value.SEVEN),
        Card(Suit.CLUBS, Value.KING),
        Card(Suit.DIAMONDS, Value.EIGHT),
        Card(Suit.CLUBS, Value.TEN),
        Card(Suit.DIAMONDS, Value.TEN),
        Card(Suit.HEARTS, Value.JACK),
        Card(Suit.SPADES, Value.KING),
        Card(Suit.DIAMONDS, Value.ACE),
        Card(Suit.HEARTS, Value.QUEEN),
        Card(Suit.SPADES, Value.ACE),
        Card(Suit.HEARTS, Value.NINE),).shuffled().toMutableList()

    /**
     * changes the middle Cards with 3 new from the Deck.
     *
     * @throws IllegalStateException if no game has started yet.
     *
     * @throws IllegalStateException if no Deck available.
     */
    fun renewMiddleCards() {
        if(!hasDeckEnoughCards())
        {
            root.gameService.exitGame()
        }
        val game = root.currentGame
        checkNotNull(game)
        game.playArea.cards.clear()
        val myDeck = game.playArea.deck
        val cardOnTable1 = myDeck.draw(1)[0]
        val cardOnTable2 = myDeck.draw(1)[0]
        val cardOnTable3 = myDeck.draw(1)[0]
        game.playArea.cards.add(cardOnTable1)
        game.playArea.cards.add(cardOnTable2)
        game.playArea.cards.add(cardOnTable3)
    }

    /**
     * checks if the Deck has more than three cards.
     * @throws IllegalStateException if no game has started yet
     */
    private fun hasDeckEnoughCards(): Boolean {
        val game = root.currentGame
        checkNotNull(game)
        val myDeck = game.playArea.deck
        return myDeck.listOfCards.size > 3
    }
}