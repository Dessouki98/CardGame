package entity
import kotlin.test.*
import kotlin.random.Random
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertSame


class DeckTest {

    //Cards to perform test
    private val aceOfSpades = Card(Suit.SPADES, Value.ACE)
    private val kingOfHearts = Card(Suit.HEARTS, Value.KING)
    private val queenOfClubs = Card(Suit.CLUBS, Value.QUEEN)

    /**
     * Check if draw cards works as we draw the three cards given above we should get them back in form of list
     */
 @Test
    fun drawTest(){
        val deckThreeCards = Deck(mutableListOf(aceOfSpades,kingOfHearts,queenOfClubs))
        val drawnCards = deckThreeCards.draw(3)
        assertEquals(mutableListOf(aceOfSpades, kingOfHearts, queenOfClubs), drawnCards)
        assertEquals(true, deckThreeCards.listOfCards.isEmpty())
    }
}