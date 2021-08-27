package service

import entity.Player
import entity.PlayArea
import view.Refreshable
import kotlin.test.*
class PlayAreaService {
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
    @Test
    fun testCreateDeck()
    {
        val testRefreshable = TestRefreshable()
        val mc = setUpGame(testRefreshable)
        //shouldn't be null by this time
        assertNotNull(mc.currentGame)
        //Deck contains One Card of each Suit*Value
/*        for(player in mc.currentGame!!.players)
        {
            for(card in player.hand)
            {
               assertFalse { mc.currentGame!!.playArea.deck.listOfCards.contains(card) }
            }
        }*/
//        for(card in mc.currentGame!!.playArea.cards)
//        {
//            assertFalse { mc.currentGame!!.playArea.cards.contains(card) }
//        }
//        //after each player takes 3 Cards * 4Players +3 Ont able we got 17left
//        assertEquals(17,mc.currentGame!!.playArea.deck.listOfCards.size)
    }
}