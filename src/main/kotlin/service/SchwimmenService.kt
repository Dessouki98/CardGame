package service
import entity.Schwimmen
import view.Refreshable

/**
 * Class Schwimmen Service works as wrapper for all the Entities and connects them to Services
 */
class SchwimmenService {
    /**
     * The currently active game. Can be `null`, if no game has started yet.
     */
    var currentGame:Schwimmen?=null
    val evaluationService = EvaluationService(this)
    val gameService = GameService(this)
    val playerService = PlayerService(this)
    val playAreaService = PlayAreaService(this)
    /**
     * Adds the provided [newRefreshable] to all services connected
     * to this root service
     */
    fun addRefreshable(newRefreshable: Refreshable) {
        gameService.addRefreshable(newRefreshable)
        playAreaService.addRefreshable(newRefreshable)
        evaluationService.addRefreshable(newRefreshable)
        playerService.addRefreshable(newRefreshable)
    }

    /**
     * Adds each of the provided [newRefreshables] to all services
     * connected to this root service
     */
    fun addRefreshables(vararg newRefreshables: Refreshable) {
        newRefreshables.forEach { addRefreshable(it) }//uses the last method ([addRefreshable])hmm
    }
}