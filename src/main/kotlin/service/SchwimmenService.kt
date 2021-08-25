package service
import entity.PlayArea
import entity.Player
import entity.Schwimmen

class SchwimmenService {
    /**
     * The currently active game. Can be `null`, if no game has started yet.
     */
    public var currentGame:Schwimmen?=null
    val evaluationService = EvaluationService(this)
    val gameService = GameService(this)
    val playerService = PlayerService(this)
    val playAreaService = PlayAreaService(this)

}