package service
import entity.Card
import entity.Player
import entity.Suit
import entity.Value
import view.Refreshable
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
/**
 * test Evaluation Service which its main request to perform calculating of points and shows how won.
 */
class EvaluationServiceTest
{
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
     *We try here to test if the CalculatePoints method always gives the right amount of Points a Player has.
     */
    @Test
    fun testCalculatePoints()
    {
        val testRefreshable = TestRefreshable()
        val mc = setUpGame(testRefreshable)
        //shouldn't be null by this time
        assertNotNull(mc.currentGame)
        assertTrue { testRefreshable.refreshAfterGameStartCalled}
        val p1 = mc.currentGame!!.players[0]
        val p2 = mc.currentGame!!.players[1]
        val p3 = mc.currentGame!!.players[2]
        val p4 = mc.currentGame!!.players[3]
        //We Clear the hand and Give here a List that we Want to test the function.
        p1.hand.clear()
        p2.hand.clear()
        p3.hand.clear()
        p4.hand.clear()
        //A set with 30 Points
        val cardSet1= mutableListOf(Card(Suit.DIAMONDS, Value.NINE),
            Card(Suit.DIAMONDS, Value.JACK),
            Card(Suit.DIAMONDS, Value.ACE))
        //A set with 27 Points
        val cardSet2= mutableListOf(Card(Suit.HEARTS, Value.SEVEN),
            Card(Suit.HEARTS, Value.TEN),
            Card(Suit.HEARTS, Value.JACK))
        //A set with 18 Points
        val cardSet3= mutableListOf(Card(Suit.SPADES, Value.ACE),
            Card(Suit.SPADES, Value.SEVEN),
            Card(Suit.CLUBS, Value.QUEEN))
        //A set with all Values are the same therefore 30.5 Points
        val cardSet4= mutableListOf(Card(Suit.CLUBS, Value.EIGHT),
            Card(Suit.DIAMONDS, Value.EIGHT),
            Card(Suit.SPADES, Value.EIGHT))
        //we add the cards to Players Hands
        p1.hand.addAll(cardSet1)
        p2.hand.addAll(cardSet2)
        p3.hand.addAll(cardSet3)
        p4.hand.addAll(cardSet4)
        assertEquals(30.0,mc.evaluationService.calculateScore(p1))
        assertEquals(27.0,mc.evaluationService.calculateScore(p2))
        assertEquals(18.0,mc.evaluationService.calculateScore(p3))
        assertEquals(30.5,mc.evaluationService.calculateScore(p4))
    }
    /**
     * with calculateWinner test we suppose the scenario now that game has just started and
     * player1 has knocked this should be enough because the other procedures should be also
     * covered with this Scenario
     */
    @Test
    fun testCalculateWinner()
    {
        val testRefreshable = TestRefreshable()
        val mc = setUpGame(testRefreshable)
        assertNotNull(mc.currentGame)
        assertTrue { testRefreshable.refreshAfterGameStartCalled}
        val p1 = mc.currentGame!!.players[0]
        val p2 = mc.currentGame!!.players[1]
        val p3 = mc.currentGame!!.players[2]
        val p4 = mc.currentGame!!.players[3]
        //We Clear the hand and Give here a List that we Want to test the function.
        p1.hand.clear()
        p2.hand.clear()
        p3.hand.clear()
        p4.hand.clear()
        //A set with 30 Points
        val cardSet1= mutableListOf(Card(Suit.DIAMONDS, Value.NINE),
            Card(Suit.DIAMONDS, Value.JACK),
            Card(Suit.DIAMONDS, Value.ACE))
        //A set with 27 Points
        val cardSet2= mutableListOf(Card(Suit.HEARTS, Value.SEVEN),
            Card(Suit.HEARTS, Value.TEN),
            Card(Suit.HEARTS, Value.JACK))
        //A set with 18 Points
        val cardSet3= mutableListOf(Card(Suit.SPADES, Value.ACE),
            Card(Suit.SPADES, Value.SEVEN),
            Card(Suit.CLUBS, Value.QUEEN))
        //A set with all Values are the same therefore 30.5 Points
        val cardSet4= mutableListOf(Card(Suit.CLUBS, Value.EIGHT),
            Card(Suit.DIAMONDS, Value.EIGHT),
            Card(Suit.SPADES, Value.EIGHT))
        //we add the cards to Players Hands
        p1.hand.addAll(cardSet1)
        p2.hand.addAll(cardSet2)
        p3.hand.addAll(cardSet3)
        p4.hand.addAll(cardSet4)
        //shouldn't be null by this time
        assertNotNull(mc.currentGame)
        //we know from previous method that we Calculate Points right, now we show that by using [CalculateWinner]
        //we get a list of players ordered ascending according to points
        val playersWithPoints=mc.evaluationService.calculateWinner()
        val players=playersWithPoints.keys.toMutableList()
        val calcPoints=mc.evaluationService
        //showing now that the points of player4 is the maximum and that the list is sorted.
        assertTrue { (calcPoints.calculateScore(players[3])) > (calcPoints.calculateScore(players[2])) }
        assertTrue { (calcPoints.calculateScore(players[2])) > (calcPoints.calculateScore(players[1])) }
        assertTrue { (calcPoints.calculateScore(players[1])) > (calcPoints.calculateScore(players[0])) }
        //showing that refresh is being called right
        assertTrue { testRefreshable.refreshAfterGameFinishedCalled }
        testRefreshable.reset()
    }
}