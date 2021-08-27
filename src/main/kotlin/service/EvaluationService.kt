package service
import entity.Card
import entity.Player
/**
 * Service layer Evaluation that provides the number of points each player has, and it also
 * calculates with help from [calculateWinner] who won the Game.
 */
class EvaluationService(private val root: SchwimmenService) : AbstractRefreshingService() {
    /**
     *creates a list with all possible suits +1 because there is the Condition that the Player
     * has a cards of same Value or same Suit and this gives him 30.5 points, after that the points is
     * then summed up according to their Suit.
     *
     *  @throws IllegalArgumentException if [player] has no cards in hand.
     *
     * @throws IllegalStateException if no game has started yet
     */
     fun calculateScore(player: Player): Double {
        val myCard: MutableList<Double> = mutableListOf(0.0, 0.0, 0.0, 0.0, 0.0)
        for (card: Card in player.hand) {
            when (card.cardsuit.toString()) {
                "♣" -> myCard[0] = myCard[0] + card.getPoints()
                "♠" -> myCard[1] = myCard[1] + card.getPoints()
                "♥" -> myCard[2] = myCard[2] + card.getPoints()
                "♦" -> myCard[3] = myCard[3] + card.getPoints()
            }
        }
        //Checks if the special condition is available
        val card1Va = player.hand[0].cardvalue.toString()
        val card2Va = player.hand[1].cardvalue.toString()
        val card3Va = player.hand[2].cardvalue.toString()
        if ((card1Va == card2Va && card2Va == card3Va)) {
            myCard[4] = 30.5
        }
        //returns max points for the given player
        return myCard.maxOrNull() as Double
    }

    /**
     * gives a Map of all player sorted up according to their points and the last player in the
     * list has the most Points and therefore considered the winner
     *
     * @throws IllegalStateException if no game has started yet
     */
     fun calculateWinner(): Map<Player, Double> {
        val hashMap: HashMap<Player, Double> = HashMap()
        val game = root.currentGame
        checkNotNull(game)
        for (player: Player in game.players) {

            hashMap[player] = calculateScore(player) as (Double)
        }
        //Order the Map according to players points
        val resultMap = hashMap.entries.sortedBy { it.value }.associate { it.toPair() }
        onAllRefreshables { refreshAfterGameFinished() }
        return resultMap
    }
}
