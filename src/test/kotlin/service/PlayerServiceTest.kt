package service
import entity.Player
import view.Refreshable
import kotlin.test.*

/**
 * this class test the PlayerService Class which consists of the actions that a player can perform during the game
 */
class PlayerServiceTest {
    /**
     * a private Function that only Creates a new Game with 2 players and return it back.
     */
    private fun setUpGame(vararg refreshables: Refreshable): SchwimmenService {
        val mc = SchwimmenService()
        refreshables.forEach { mc.addRefreshable(it) }
        //Player1
        val player1= Player("Alice",1)
        //Player2
        val player2= Player("Bob",2)
        //Player3
        val player3= Player("Celina",3)
        //Player4
        val player4= Player("Derek",4)
        val listOfPlayers=mutableListOf(player1,player2,player3,player4)
        mc.gameService.startGame(listOfPlayers)
        println(mc.currentGame)
        return mc
    }

    /**
     * Test the case that the Game just started and p1 has now decided to switch card
     */
    @Test
    fun testSwapOneCard() {
        val testRefreshable = TestRefreshable()
        val mc = setUpGame(testRefreshable)
        //shouldn't be null by this time
        assertNotNull(mc.currentGame)
        assertTrue { testRefreshable.refreshAfterGameStartCalled}
        val p1 = mc.currentGame!!.players[0]
        val cartonHand=mc.currentGame!!.players[0].hand[0]
        val cardOnTable=mc.currentGame!!.playArea.cards[0]
        mc.playerService.swapOneCard(p1.hand[0],mc.currentGame!!.playArea.cards[0])
        //Card on Hand is now on Table
        assertTrue { mc.currentGame!!.playArea.cards.contains(cartonHand) }
        //Hand has now the Card on Table
        assertTrue { p1.hand.contains(cardOnTable) }
        //Hand has no longer the Card
        assertFalse {p1.hand.contains(cartonHand) }
        //the Table has the Card no more
        assertFalse{mc.currentGame!!.playArea.cards.contains(cardOnTable) }
        //player2 turn
        assertEquals(1, mc.currentGame!!.activePlayer)
        assertTrue { testRefreshable.refreshAfterTurnChangedCalled }
        //Check if refreshed correctly
        assertTrue(testRefreshable.refreshAfterCardSwapCalled)
    }
    /**
     * Test the case that the Game just started and p1 has now decided to switch all cards
     */
    @Test
    fun testSwapThreeCard() {
        val testRefreshable = TestRefreshable()
        val mc = setUpGame(testRefreshable)
        //shouldn't be null by this time
        assertNotNull(mc.currentGame)
        assertTrue { testRefreshable.refreshAfterGameStartCalled}
        val p1 = mc.currentGame!!.players[0]
        val hand=p1.hand
        val table=mc.currentGame!!.playArea.cards
        val cardsOnHand= mutableListOf(hand[0],hand[1],hand[2])
        val cardsOnTable=mutableListOf(table[0],table[1],table[2])
        mc.playerService.swapThreeCards()
        //Cards on Hand is now on Table
        assertEquals( table, cardsOnHand)
        //Hand has now the Card on Table
        assertEquals( hand, cardsOnTable)
        //player2 turn
        assertEquals(1, mc.currentGame!!.activePlayer)
        assertTrue { testRefreshable.refreshAfterTurnChangedCalled }
        //Check if refreshed correctly
        assertTrue(testRefreshable.refreshAfterCardSwapCalled)
        //reset the parameters
        testRefreshable.reset()
    }
    /**
     * Test the case that the Game just started and p1 has now decided to pass
     */
    @Test
    fun testPass()
    {
        val testRefreshable = TestRefreshable()
        var mc = setUpGame(testRefreshable)
        assertTrue { testRefreshable.refreshAfterGameStartCalled}
        //shouldn't be null by this time
        assertNotNull(mc.currentGame)
        val p1 = mc.currentGame!!.players[0]
        //Player 1 is active by default when starting the game
        mc.playerService.pass()
        //check to see if variables changes
        assertTrue { p1.passed }
        //checks if player2 get his turn
        assertEquals(1, mc.currentGame!!.activePlayer)
        assertTrue { testRefreshable.refreshAfterTurnChangedCalled }
        //num of cards in deck >3...Game continues...17 Cards still
        assertEquals(17,mc.currentGame!!.playArea.deck.listOfCards.size)
        //now all pass too
        repeat(3){mc.playerService.pass()}
        //num of cards in deck =14,3 cards in the middle were changed
        assertEquals(14,mc.currentGame!!.playArea.deck.listOfCards.size)
        //refresh after passed correctly
        assertTrue { testRefreshable.refreshAfterPassedCalled }
        //Cards have been drawn from Deck
        assertTrue { testRefreshable.refreshAfterCardDrawnCalled}
        //Game doesn't End yet
        assertFalse { testRefreshable.refreshAfterGameFinishedCalled }
        //reset the parameters
        testRefreshable.reset()
        //restart the game now to show when not enough card the game ends
        mc = setUpGame(testRefreshable)
        //after this we got only 2 Cards
        repeat(5)
        {
            mc.playAreaService.renewMiddleCards()
        }
        //all players pass
        repeat(4)
        {
            mc.playerService.pass()
        }
        //the game ends no enough cards
        assertTrue { testRefreshable.refreshAfterGameFinishedCalled }

    }
    /**
     * Test the case that the Game just started and p1 has now decided to knock
     */
    @Test
    fun testKnock()
    {
        val testRefreshable = TestRefreshable()
        val mc = setUpGame(testRefreshable)
        //the game started
        assertTrue { testRefreshable.refreshAfterGameStartCalled}
        //shouldn't be null by this time
        assertNotNull(mc.currentGame)
        val p1 = mc.currentGame!!.players[0]
        //Player 1 is active by default when starting the game
        mc.playerService.knock()
        //check to see if variables changes
        assertTrue { p1.knocked }
        //checks if player2 get his turn
        assertEquals(1, mc.currentGame!!.activePlayer)
        assertTrue { testRefreshable.refreshAfterTurnChangedCalled }
        //refresh after knocked correctly
        //assertTrue { testRefreshable.refreshAfterKnockedCalled }
        //now player get to play one more time
        repeat(3)
        {
            mc.playerService.pass()
        }
        //Game Ends after they play
        assertTrue { testRefreshable.refreshAfterGameFinishedCalled }
        testRefreshable.reset()
    }
}


