package service

import entity.Player
import view.Refreshable
import kotlin.test.*
/**
 * testing the PlayAreaService Class which gives functions that we use in the PlayArea
 */
class PlayAreaServiceTest {
    /**
     * a private Function that only Creates a new Game with 2 players and return it back.
     */
    private fun setUpGame(vararg refreshables: Refreshable): SchwimmenService {
        val mc = SchwimmenService()
        refreshables.forEach { mc.addRefreshable(it) }
        //Player1
        val player1 = Player("Alice", 1)
        //Player2
        val player2 = Player("Bob", 2)
        //Player3
        val player3 = Player("Celina", 3)
        //Player4
        val player4 = Player("Derek", 4)
        val listOfPlayers = mutableListOf(player1, player2, player3, player4)
        mc.gameService.startGame(listOfPlayers)
        println(mc.currentGame)
        return mc
    }

    /**
     * test to see that when Deck is created that is the right Deck that we have and all Cards exists just one time.
     */
    @Test
    fun testCreateDeck()
    {
        val testRefreshable = TestRefreshable()
        val mc = setUpGame(testRefreshable)
        assertTrue { testRefreshable.refreshAfterGameStartCalled}
        //shouldn't be null by this time
        assertNotNull(mc.currentGame)
        //Deck contains One Card of each Suit*Value
        //1-Cards in each player's hand is not contained
        for(player in mc.currentGame!!.players)
        {
            for(card in player.hand)
            {
               assertFalse { mc.currentGame!!.playArea.deck.listOfCards.contains(card) }
            }
        }
        //2-Cards on the table are not Contained int the Deck
        for(card in mc.currentGame!!.playArea.cards)
        {
            assertFalse { mc.currentGame!!.playArea.deck.listOfCards.contains(card) }
        }
        //after each player takes 3 Cards * 4Players +3 Ont able we got 17left
        assertEquals(17,mc.currentGame!!.playArea.deck.listOfCards.size)
    }

    /**
     * a test Function to test that after calling renewMiddleCard all Card on Table will be replaced
     */
    @Test
    fun testRenewMiddleCards(){
        val testRefreshable = TestRefreshable()
        val mc = setUpGame(testRefreshable)
        //shouldn't be null by this time
        assertNotNull(mc.currentGame)
        assertTrue { testRefreshable.refreshAfterGameStartCalled}
        //test that when called all Cards on Table will be replaced
        val cards=mc.currentGame!!.playArea.cards
        val oldCards= mutableListOf(cards[0],cards[1],cards[2])
        mc.playAreaService.renewMiddleCards()
        //checking to see if any of old Card is still on Table
        for (card in oldCards)
        {
            assertFalse { cards.contains(card) }
        }
    }
}