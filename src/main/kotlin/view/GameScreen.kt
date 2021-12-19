package view

import entity.Card
import entity.CardImageLoader
import tools.aqua.bgw.components.gamecomponentviews.CardView
import tools.aqua.bgw.components.uicomponents.Button
import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.core.BoardGameScene
import tools.aqua.bgw.util.BidirectionalMap
import tools.aqua.bgw.util.Font
import tools.aqua.bgw.visual.ColorVisual
import tools.aqua.bgw.visual.ImageVisual
import java.awt.Color

/**
 * Game Screen Class simulate the Gui GameScreen
 */
class GameScreen(private val schwimmenApplication: SchwimmenApplication) : BoardGameScene(1920, 1080), Refreshable {
    /**
     * Banner for the Name
     */
    private val banner = Label(
        width = 800, height = 350, posX = 450, posY = -60,
        font = Font(size = 70, color = Color.RED, family = "Papyrus"), text = "CardGame"
    )

    /**
     * play Ground label
     */
    private val playGroundLabel = Label(
        width = 800, height = 350, posX = 450, posY = 45,
        font = Font(size = 30, color = Color.BLACK, family = "Papyrus"), text = "playground"
    )

    /**
     * Deck label
     */
    private val deckLabel = Label(
        width = 800, height = 350, posX = 1160, posY = 170,
        font = Font(size = 30, color = Color.BLACK, family = "Papyrus"), text = "Deck"
    )

    /**
     * Gray BackGround to simulate Gui template
     */
    private val playgroundBackground = Label(
        width = 825, height = 350, posX = 450, posY = 250,
        font = Font(size = 70, color = Color.RED, family = "Papyrus"), visual = ColorVisual(235, 210, 180)
    )

    /**
     * This deck hold the Gui photos of the cards of the game's Deck
     */
    private var deck = LabeledStackView(posX = 1500, posY = 400, "Deck")

    /**
     * Player Cards holds the gui template of all the hand's Cards
     */
    private val playerCards = LabeledStackView(posX = 600, posY = 830, "playerCards")

    /**
     * Each on of them holds a view of 1 card of the hand
     */
    private val playerCard1 = LabeledStackView(posX = 600, posY = 830, "playerCard1")
    private val playerCard2 = LabeledStackView(posX = 895, posY = 830, "playerCard2")
    private val playerCard3 = LabeledStackView(posX = 1200, posY = 830, "playerCard3")

    /**
     * cardOnTable holds the gui template of all the Player Area's cards(Cards On table)
     */
    private val cardsOnTable = LabeledStackView(posX = 600, posY = 830, "playerCards")

    /**
     * Each on of them holds a view of 1 card of the Cards on the table
     */
    private val cardOnTable1 = LabeledStackView(posX = 500, posY = 350, "Card1")
    private val cardOnTable2 = LabeledStackView(posX = 795, posY = 350, "Card2")
    private val cardOnTable3 = LabeledStackView(posX = 1100, posY = 350, "Card3")

    /**
     * Here are flag variables to show which hand card have been chosen
     */
    private var flagForPlayerCard1 = false
    private var flagForPlayerCard2 = false
    private var flagForPlayerCard3 = false

    /**
     * the Button below Card On Table num 1, apply does the given function when activated
     */
    private val buttonForCardOnTable1 = Button(
        width = 120, height = 35,
        posX = 505, posY = 560,
        text = "Choose Card", visual = ColorVisual(164, 62, 62)
    ).apply {
        onMouseClicked = {
            opacity = .5
            if (flagForPlayerCard1) {
                schwimmenApplication.schwimmenService.playerService.swapOneCard(schwimmenApplication.schwimmenService.currentGame?.activePlayer?.let { it1 ->
                    schwimmenApplication.schwimmenService.currentGame?.players?.get(
                        it1
                    )
                }!!.hand[0], schwimmenApplication.schwimmenService.currentGame?.playArea?.cards!![0])
            } else if (flagForPlayerCard2) {
                schwimmenApplication.schwimmenService.playerService.swapOneCard(schwimmenApplication.schwimmenService.currentGame?.activePlayer?.let { it1 ->
                    schwimmenApplication.schwimmenService.currentGame?.players?.get(
                        it1
                    )
                }!!.hand[1], schwimmenApplication.schwimmenService.currentGame?.playArea?.cards!![0])
            } else if (flagForPlayerCard3) {
                schwimmenApplication.schwimmenService.playerService.swapOneCard(schwimmenApplication.schwimmenService.currentGame?.activePlayer?.let { it1 ->
                    schwimmenApplication.schwimmenService.currentGame?.players?.get(
                        it1
                    )
                }!!.hand[2], schwimmenApplication.schwimmenService.currentGame?.playArea?.cards!![0])
            }
            flagForPlayerCard1 = false
            flagForPlayerCard2 = false
            flagForPlayerCard3 = false
            opacity = 1.0
        }
    }

    /**
     * the Button below Card On Table num 2,attributes can not be predefined as they can change
     * from the time of initialization
     */
    private val buttonForCardOnTable2 = Button(
        width = 120, height = 35,
        posX = 800, posY = 560,
        text = "Choose Card", visual = ColorVisual(164, 62, 62)
    ).apply {
        onMouseClicked = {
            opacity = .5
            if (flagForPlayerCard1) {
                schwimmenApplication.schwimmenService.playerService.swapOneCard(schwimmenApplication.schwimmenService.currentGame?.activePlayer?.let { it1 ->
                    schwimmenApplication.schwimmenService.currentGame?.players?.get(
                        it1
                    )
                }!!.hand[0], schwimmenApplication.schwimmenService.currentGame?.playArea?.cards!![1])
            } else if (flagForPlayerCard2) {
                schwimmenApplication.schwimmenService.playerService.swapOneCard(schwimmenApplication.schwimmenService.currentGame?.activePlayer?.let { it1 ->
                    schwimmenApplication.schwimmenService.currentGame?.players?.get(
                        it1
                    )
                }!!.hand[1], schwimmenApplication.schwimmenService.currentGame?.playArea?.cards!![1])

            } else if (flagForPlayerCard3) {
                //checkNotNull(players)
                schwimmenApplication.schwimmenService.playerService.swapOneCard(schwimmenApplication.schwimmenService.currentGame?.activePlayer?.let { it1 ->
                    schwimmenApplication.schwimmenService.currentGame?.players?.get(
                        it1
                    )
                }!!.hand[2], schwimmenApplication.schwimmenService.currentGame?.playArea?.cards!![1])
            }
            flagForPlayerCard1 = false
            flagForPlayerCard2 = false
            flagForPlayerCard3 = false
            opacity = 1.0
        }
    }

    /**
     * the Button below Card On Table num 3
     */
    private val buttonForCardOnTable3 = Button(
        width = 120, height = 35,
        posX = 1105, posY = 560,
        text = "Choose Card", visual = ColorVisual(164, 62, 62)
    ).apply {
        onMouseClicked = {
            opacity = .5
            if (flagForPlayerCard1) {
                schwimmenApplication.schwimmenService.playerService.swapOneCard(schwimmenApplication.schwimmenService.currentGame?.activePlayer?.let { it1 ->
                    schwimmenApplication.schwimmenService.currentGame?.players?.get(
                        it1
                    )
                }!!.hand[0], schwimmenApplication.schwimmenService.currentGame?.playArea?.cards!![2])

            } else if (flagForPlayerCard2) {
                schwimmenApplication.schwimmenService.playerService.swapOneCard(schwimmenApplication.schwimmenService.currentGame?.activePlayer?.let { it1 ->
                    schwimmenApplication.schwimmenService.currentGame?.players?.get(
                        it1
                    )
                }!!.hand[1], schwimmenApplication.schwimmenService.currentGame?.playArea?.cards!![2])

            } else if (flagForPlayerCard3) {
                schwimmenApplication.schwimmenService.playerService.swapOneCard(schwimmenApplication.schwimmenService.currentGame?.activePlayer?.let { it1 ->
                    schwimmenApplication.schwimmenService.currentGame?.players?.get(
                        it1
                    )
                }!!.hand[2], schwimmenApplication.schwimmenService.currentGame?.playArea?.cards!![2])

            }
            flagForPlayerCard1 = false
            flagForPlayerCard2 = false
            flagForPlayerCard3 = false
            opacity = 1.0
        }
    }

    /**
     * the Buttons for the players hand when pressed flag is set
     */
    private val buttonForPlayerCard1 = Button(
        width = 120, height = 35,
        posX = 605, posY = 1040,
        text = "Choose Card", visual = ColorVisual(164, 62, 62)
    ).apply {
        onMouseClicked = {
            flagForPlayerCard1 = true
        }
    }
    private val buttonForPlayerCard2 = Button(
        width = 120, height = 35,
        posX = 900, posY = 1040,
        text = "Choose Card", visual = ColorVisual(164, 62, 62)
    ).apply {
        onMouseClicked = {
            flagForPlayerCard2 = true
        }
    }
    private val buttonForPlayerCard3 = Button(
        width = 120, height = 35,
        posX = 1205, posY = 1040,
        text = "Choose Card", visual = ColorVisual(164, 62, 62)
    ).apply {
        onMouseClicked = {
            flagForPlayerCard3 = true
        }
    }

    /**
     * the Swap all Button which
     */
    private val swapAll = Button(
        width = 180, height = 60,
        posX = 1700, posY = 800,
        text = "swap all"
    ).apply {
        visual = ColorVisual(164, 62, 62)
        onMousePressed = {
            schwimmenApplication.schwimmenService.playerService.swapThreeCards()
        }
    }

    /**
     * the Pass Button
     */
    private val pass = Button(
        width = 180, height = 60,
        posX = 1700, posY = 900,
        text = "Pass"
    ).apply {
        visual = ColorVisual(164, 62, 62)
        onMousePressed = {
            schwimmenApplication.schwimmenService.playerService.pass()
        }
    }

    /**
     * the Knock Button
     */
    private val knock = Button(
        width = 180, height = 60,
        posX = 1700, posY = 1000,
        text = "knock"
    ).apply {
        visual = ColorVisual(164, 62, 62)
        onMousePressed = {
            schwimmenApplication.schwimmenService.playerService.knock()
        }
    }

    /**
     * the Quit Button
     */
    private val quit = Button(
        width = 180, height = 60,
        posX = 20, posY = 1000,
        text = "Quit"
    ).apply {
        visual = ColorVisual(164, 62, 62)
        onMousePressed = {
            schwimmenApplication.exit()
        }
    }

    /**
     * structure to hold pairs of (card, cardView) that can be used
     *
     * 1. to find the corresponding view for a card passed on by a refresh method (forward lookup)
     *
     * 2. to find the corresponding card to pass to a service method on the occurrence of
     * ui events on views (backward lookup).
     */
    private val cardMap: BidirectionalMap<Card, CardView> = BidirectionalMap()

    init {
        // dark green for "Casino table" flair
        background = ColorVisual(196, 196, 196)

        addComponents(
            deck, banner, playgroundBackground, playGroundLabel, quit, deckLabel,
            buttonForCardOnTable1, buttonForCardOnTable2, buttonForCardOnTable3,
            buttonForPlayerCard1, buttonForPlayerCard2, buttonForPlayerCard3,
            playerCard1, playerCard2, playerCard3, swapAll, pass, knock, cardOnTable1, cardOnTable2, cardOnTable3
        )

    }

    /**
     * clears [stackView], adds a new [CardView] for each
     * element of [stack] onto it, and adds the newly created view/card pair
     * to the global [cardMap].
     * Important:the function does the following it takes a List of Card,an item of LabeledStackView and then saves
     * the all Items int list of Card to this LabeledStackView.
     */

    private fun initializeStackView(
        stack: MutableList<Card>,
        stackView: LabeledStackView,
        cardImageLoader: CardImageLoader
    ) {
        stackView.clear()
        stack.reversed().forEach { card ->
            val cardView = CardView(
                height = 200,
                width = 130,
                front = ImageVisual(cardImageLoader.frontImageFor(card.cardsuit, card.cardvalue)),
                back = ImageVisual(cardImageLoader.backImage)
            )
            stackView.add(cardView)
            //Card map connects Photo to card
            cardMap.add(card to cardView)
        }
    }

    /**
     * Initializes the complete GUI Player's Hand, Deck,Card on Table
     */
    override fun refreshAfterGameStart() {
        val game = schwimmenApplication.schwimmenService.currentGame
        val active = game?.activePlayer
        checkNotNull(game) { "No started game found." }
        cardMap.clear()
        val cardImageLoader = CardImageLoader()
        //get card from a Card list to a LabeledStackView(cards with photo)...deck As list to Deck As photos
        initializeStackView(game.playArea.deck.listOfCards, deck, cardImageLoader)
        //playArea'S Card from a List to Gui Card list
        initializeStackView(game.playArea.cards, cardsOnTable, cardImageLoader)
        //hand's Card from a List to Gui hand card list
        initializeStackView(game.players[active!!].hand, playerCards, cardImageLoader)
        //get the top card from the list gui and add it to the gui component the true means its flipped
        moveCardView(cardsOnTable.peek(), cardOnTable1, true)
        moveCardView(cardsOnTable.peek(), cardOnTable2, true)
        moveCardView(cardsOnTable.peek(), cardOnTable3, true)
        //get the top card from the list gui and add it to the gui component the true means its flipped
        //playerCards has three Cards in it, and we will distribute it.
        //Important:Peek doesn't remove the Card from parent Stack but in moveCardView we remove it.
        moveCardView(playerCards.peek(), playerCard1, true)
        moveCardView(playerCards.peek(), playerCard2, true)
        moveCardView(playerCards.peek(), playerCard3, true)

    }

    /**
     * one has passed means we load the next players Cards on the Gui
     */
    override fun refreshAfterPassed() {//note that when we override this function hear then as soon as 
        //[onAllrefreshables] is called all overridden function will get to work and do what in their bodies
        refreshPlayerCards()
    }

    /**
     * One has Knocked we load the next players Card on the Gui
     */
    override fun refreshAfterKncoked() {
        refreshPlayerCards()

    }

    /**
     * a player has either swapped a card or swapped 3 Cards in both cases we
     * Update Players Cards and Cards on the Table.
     */
    override fun refreshAfterCardsSwap() {
        refreshPlayerCards()//Updates player card
        refreshAfterAllPassed()//updates card on table

    }

    /**
     * all players have passed now we Update the players Card because of next turn, and we update the table
     */
    override fun refreshAfterAllPassed() {
        val game = schwimmenApplication.schwimmenService.currentGame
        val cardImageLoader = CardImageLoader()
        cardOnTable1.clear()
        cardOnTable2.clear()
        cardOnTable3.clear()
        initializeStackView(game!!.playArea.cards, playerCards, cardImageLoader)
        moveCardView(playerCards.peek(), cardOnTable1, true)
        moveCardView(playerCards.peek(), cardOnTable2, true)
        moveCardView(playerCards.peek(), cardOnTable3, true)
    }

    /**
     * a private method which updates the Gui of the Cards on Hand 
     */
    private fun refreshPlayerCards() {
        val game = schwimmenApplication.schwimmenService.currentGame
        val active = game?.activePlayer
        val cardImageLoader = CardImageLoader()
        playerCard1.clear()
        playerCard2.clear()
        playerCard3.clear()
        initializeStackView(game!!.players[active!!].hand, playerCards, cardImageLoader)
        moveCardView(playerCards.peek(), playerCard1, true)
        moveCardView(playerCards.peek(), playerCard2, true)
        moveCardView(playerCards.peek(), playerCard3, true)
    }
    /**
     * moves a [cardView] from current container on top of [toStack].
     *
     * @param flip if true, the view will be flipped from [CardView.CardSide.FRONT] to
     * [CardView.CardSide.BACK] and vice versa.
     * Important this methode takes the Card Image and adds it to LabeledStackView using flip we show front
     */
    private fun moveCardView(cardView: CardView, toStack: LabeledStackView, flip: Boolean = false) {
        if (flip) {
            when (cardView.currentSide) {
                CardView.CardSide.BACK -> cardView.showFront()
                CardView.CardSide.FRONT -> cardView.showBack()
            }
        }
        cardView.removeFromParent()
        toStack.add(cardView)
    }
    /**
     * Notes:
     * 1-implement the refresh in the class where it should be implemented ex in refresh after finish doesn't affect
     * game Screen because we do nothing in this scene but that is different in Score Screen.
     * 2-Place On all refreshable where the method should run.
     * 3-in every Package there is One holding all classes which they all take as input in order to connect
     * with One another.
     * 4-in gameScene they all need to have the refreshable methods to refresh gui and in Service classes they all need
     * to have the Abstract RefreshingService to Send changes
     */
}