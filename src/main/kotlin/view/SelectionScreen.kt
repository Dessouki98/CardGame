package view


import entity.Player
import tools.aqua.bgw.components.uicomponents.Button
import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.components.uicomponents.TextField
import tools.aqua.bgw.core.BoardGameScene
import tools.aqua.bgw.util.Font
import tools.aqua.bgw.visual.ColorVisual
import java.awt.Color

class SelectionScreen(private val schwimmenApplication: SchwimmenApplication) : BoardGameScene(1920, 1080),
    Refreshable {
    private val players = mutableListOf<Player>()

    private val helloLabel = Label(
        width = 1740,
        height = 1080,
        posX = 0,
        posY = -50,
        text = "CardGame by Zeyad",
        font = Font(size = 120, color = Color.red, family = "Algerian")

    )
    private val p1Label = Label(
        width = 200, height = 100,
        posX = -10, posY = 10,
        text = "Player 1:",
        font = Font(size = 30)
    )
    private val p1Input: TextField = TextField(
        width = 200, height = 30,
        posX = 150, posY = 45,
        text = ""
    )

    private val p2Label = Label(
        width = 200, height = 100,
        posX = 1540, posY = 10,
        text = "Player 2:",
        font = Font(size = 30)
    )
    private val p2Input: TextField = TextField(
        width = 200, height = 30,
        posX = 1700, posY = 45,
        text = ""
    )

    private val p3Label = Label(
        width = 200, height = 100,
        posX = -10, posY = 720,
        text = "Player 3:",
        font = Font(size = 30)
    )
    private val p3Input: TextField = TextField(
        width = 200, height = 30,
        posX = 150, posY = 755,
        text = ""
    )

    private val p4Label = Label(
        width = 200, height = 100,
        posX = 1540, posY = 720,
        text = "Player 4:",
        font = Font(size = 30)
    )
    private val p4Input: TextField = TextField(
        width = 200, height = 30,
        posX = 1700, posY = 755,
        text = ""
    )

    val quitButton = Button(
        width = 180, height = 80,
        posX = 610, posY = 800,
        text = "Quit"
    ).apply {
        visual = ColorVisual(164, 62, 62)
        onMouseClicked = { schwimmenApplication.exit() }
    }

    val startButton = Button(
        width = 180, height = 80,
        posX = 970, posY = 800,
        text = "Start"
    ).apply {
        visual = ColorVisual(46, 164, 114)
        onMouseClicked = {
            players.clear()
            if (!p1Input.text.isBlank()) {
                val player1 = Player(p1Input.text, 0)
                players.add(player1)
            }
            if (!p2Input.text.isBlank()) {
                val player2 = Player(p2Input.text, 1)
                players.add(player2)
            }
            if (!p3Input.text.isBlank()) {
                val player3 = Player(p3Input.text, 2)
                players.add(player3)
            }
            if (!p4Input.text.isBlank()) {
                val player4 = Player(p4Input.text, 3)
                players.add(player4)
            }
            if (players.size < 2) {
                throw IllegalArgumentException("Enter more players")
            }
            schwimmenApplication.schwimmenService.gameService.startGame(players)
        }
    }


    init {
        background = ColorVisual(196, 196, 196)
        addComponents(
            helloLabel,
            p1Label, p1Input,
            p2Label, p2Input,
            p3Label, p3Input,
            p4Label, p4Input,
            startButton, quitButton
        )
    }

}