package entity

import kotlin.test.*

/**
 * Test cases for [Card]
 */

class CardTest {

    // Some cards to perform the tests with
    private val aceOfSpades = Card(Suit.SPADES, Value.ACE)
    private val jackOfClubs = Card(Suit.CLUBS, Value.JACK)
    private val queenOfHearts = Card(Suit.HEARTS, Value.QUEEN)
    private val otherQueenOfHearts = Card(Suit.HEARTS, Value.QUEEN)
    private val jackOfDiamonds = Card(Suit.DIAMONDS, Value.JACK)

    // unicode characters for the suits, as those should be used by [WarCard.toString]
    private val heartsChar = '\u2665' // ♥
    private val diamondsChar = '\u2666' // ♦
    private val spadesChar = '\u2660' // ♠
    private val clubsChar = '\u2663' // ♣

    /**
     * Check if to String produces the correct strings for some test cards
     * of all four suits.
     */

    fun testToString() {
        assertEquals(spadesChar + "A", aceOfSpades.toString())
        assertEquals(clubsChar + "J", jackOfClubs.toString())
        assertEquals(heartsChar + "Q", queenOfHearts.toString())
        assertEquals(diamondsChar + "J", jackOfDiamonds.toString())
    }

    /**
     * Check if toString produces a 2 character string for every possible card
     * except the 10,11 (for which length=3 is ensured)
     */
    @Test
    fun testToStringLength() {
        Suit.values().forEach {suit ->
            Value.values().forEach {value ->
                if (value == Value.TEN)
                    assertEquals(3, Card(suit, value).toString().length)
                else
                    assertEquals(2, Card(suit, value).toString().length)
            }
        }
    }
    /**
     * Check if getPoints the right value of the Card
     */
    fun testgetpoints() {
        Suit.values().forEach {suit ->
            Value.values().forEach {value ->
                if (value == Value.SEVEN)
                    assertEquals(7, Card(suit, value).getPoints())
                if (value == Value.EIGHT)
                    assertEquals(8, Card(suit, value).getPoints())
                if (value == Value.NINE)
                    assertEquals(9, Card(suit, value).getPoints())
                if (value == Value.TEN||value == Value.JACK||value == Value.QUEEN||value == Value.KING)
                    assertEquals(10, Card(suit, value).getPoints())
                if (value == Value.ACE)
                    assertEquals(11, Card(suit, value).getPoints())
                else
                    assertEquals(0, Card(suit, value).getPoints())
            }
        }
    }


    fun testCompareTo() {
        assertTrue(jackOfDiamonds < queenOfHearts)
        assertFalse(jackOfClubs < jackOfDiamonds)
        assertTrue(jackOfClubs <= jackOfDiamonds)
    }


    fun testEquals() {
        assertEquals(queenOfHearts, otherQueenOfHearts)
        assertNotSame(queenOfHearts, otherQueenOfHearts)
    }

}