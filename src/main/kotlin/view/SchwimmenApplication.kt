package view

import service.SchwimmenService
import tools.aqua.bgw.core.BoardGameApplication

class SchwimmenApplication : BoardGameApplication("Schwimmen Spiel"),Refreshable {
    // Central service from which all others are created/accessed.
    // also holds the currently active game.
    private val rootService = SchwimmenService()
    // This is where the actual game takes place
    private val gameScene = GameScreen(rootService)

    // This menu scene is shown after application start and if the "new game" button.
    // is clicked in the gameFinishedMenuScene.
    private val newGameMenuScene = SelectionScreen(rootService).apply {
        quitButton.onMouseClicked = {
            exit()
        }
        startButton.onMouseClicked={
            showGameScene(gameScene)
//            if(rootService.currentGame?.players?.size!! > 1)
//            {
//                showGameScene(gameScene)
//            }
//            else
//            {
//                rootService.currentGame?.players?.clear()
//                throw IllegalArgumentException("Enter more players")
//            }
        }

    }

   init {

       // all scenes and the application itself need too
       // react to changes done in the service layer
       rootService.addRefreshables(
           this,
           gameScene,
           newGameMenuScene
       )
       this.showGameScene(newGameMenuScene)
   }

}

