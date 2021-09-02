package view

import service.SchwimmenService
import tools.aqua.bgw.core.BoardGameApplication

/**
 * This Class Works As wrapper for all GameScreens
 */
class SchwimmenApplication : BoardGameApplication("Schwimmen Spiel"), Refreshable {

    val schwimmenService = SchwimmenService()
    private val gameScene = GameScreen(this)
    val selectionScreen = SelectionScreen(this)
    private val scoresScreen = ScoresScreen(this)

    init {
        /**
         * all scenes and the application itself need to
         * react to change done in the service layer
         *Important:when we use now addRefreshable on schwimmenService this means these scenes will also hear when ever
         * the On all refreshables are Called because they are now also in the list.
         */
        this.showGameScene(selectionScreen)
        schwimmenService.addRefreshables(
            this,
            gameScene,
            selectionScreen,
            scoresScreen
        )
    }

    override fun refreshAfterGameStart() {
        showGameScene(gameScene)
    }

    override fun refreshAfterGameFinished() {
        this.showGameScene(scoresScreen)
    }

}

