package view

import tools.aqua.bgw.components.uicomponents.Button
import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.core.BoardGameScene
import tools.aqua.bgw.util.Font
import tools.aqua.bgw.visual.ColorVisual
import java.awt.Color

class ScoresScreen(private val schwimmenApplication: SchwimmenApplication) : BoardGameScene(1920, 1080), Refreshable {
    /**
     * headline On Top of the Screen.
     */
    private val headlineLabel = Label(
        width = 1740,
        height = 1080,
        posX = -100,
        posY = -500,
        text = "Scores",
        font = Font(size = 80, color = Color.red, family = "Algerian")
    )

    /**
     * headline to simulate a table View
     */
    private val scores = Label(
        width = 800, height = 350, posX = 450, posY = 60,
        font = Font(size = 45, color = Color.BLACK, family = "Papyrus")
    )

    /**
     * highest Score which will be given Text after game finishes
     */
    private val p1Score = Label(
        width = 550, height = 350, posX = 550, posY = 125,
        font = Font(size = 30, color = Color.black, family = "Papyrus")
    )
    private val p2Score = Label(
        width = 550, height = 350, posX = 550, posY = 175,
        font = Font(size = 30, color = Color.black, family = "Papyrus")
    )
    private val p3Score = Label(
        width = 550, height = 350, posX = 550, posY = 225,
        font = Font(size = 30, color = Color.black, family = "Papyrus")
    )
    private val p4Score = Label(
        width = 550, height = 350, posX = 550, posY = 275,
        font = Font(size = 30, color = Color.black, family = "Papyrus")
    )

    /**
     * the quit Button bottom left
     */
    private val quitButton = Button(width = 150, height = 50, posX = 650, posY = 800, text = "Quit").apply {
        visual = ColorVisual(164, 62, 62)
        onMouseClicked = { schwimmenApplication.exit() }
    }

    /**
     * simulates the starting of the game with same players
     */
    private val newGameButton = Button(width = 150, height = 50, posX = 950, posY = 800, text = "New Game").apply {
        visual = ColorVisual(46, 164, 114)
        onMouseClicked = {
            schwimmenApplication.showGameScene(schwimmenApplication.selectionScreen)
        }
    }

    /**
     * gray Background to simulate Gui
     */
    init {
        background = ColorVisual(196, 196, 196)
        addComponents(headlineLabel, scores, p1Score, p2Score, p3Score, p4Score, newGameButton, quitButton)
    }

    /**
     * After game finishes this method is called which we now initialise the score of every winner and list them
     * according to their points
     */
    override fun refreshAfterGameFinished() {
        val gameService = schwimmenApplication.schwimmenService
        val playersWithPoints = gameService.evaluationService.calculateWinner()
        val players = playersWithPoints.keys.toMutableList()
        val points = playersWithPoints.values.toMutableList()
        gameService.evaluationService.calculateWinner()
        val numOfPlayer = players.size
        scores.text = " Players:        Cards:       points:"
        p1Score.text =
            "1-Player " + (players[numOfPlayer - 1].place + 1) + "           " + players[numOfPlayer - 1].hand.toString() + "           " + points[numOfPlayer - 1]
        p2Score.text =
            "2-Player " + (players[numOfPlayer - 2].place + 1) + "           " + players[numOfPlayer - 2].hand.toString() + "           " + points[numOfPlayer - 2]
        if (numOfPlayer > 2)
            p3Score.text =
                "3-Player " + (players[numOfPlayer - 3].place + 1) + "          " + players[numOfPlayer - 3].hand.toString() + "           " + points[numOfPlayer - 3]
        if (numOfPlayer > 3)
            p4Score.text =
                "4-Player " + (players[numOfPlayer - 4].place + 1) + "          " + players[numOfPlayer - 4].hand.toString() + "           " + points[numOfPlayer - 4]
    }
}