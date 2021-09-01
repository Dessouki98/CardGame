package view

import entity.*
import service.SchwimmenService
import tools.aqua.bgw.components.gamecomponentviews.CardView
import tools.aqua.bgw.core.BoardGameScene
import tools.aqua.bgw.util.BidirectionalMap
import tools.aqua.bgw.visual.ColorVisual
import tools.aqua.bgw.visual.ImageVisual

class GameScreen(private val rootService: SchwimmenService) : BoardGameScene(1920, 1080), Refreshable {

    //Cards of the  Deck
    private val deck=LabeledStackView(posX = 895, posY = 580, "Deck")
    // Players cards
    private val playerCard1 = LabeledStackView(posX = 600, posY = 830, "Card1")
    private val playerCard2 = LabeledStackView(posX = 895, posY = 830, "Card2")
    private val playerCard3 = LabeledStackView(posX = 1200, posY = 830, "Card3")
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
        background = ColorVisual(108, 168, 59)

        addComponents(
            deck,
            playerCard1,playerCard2,playerCard3
        )

    }

    /**
     * Initializes the complete GUI, i.e. the four stack views (left, right, played,
     * collected) of each player.
     */
    override fun refreshAfterGameStart(){
        val game = rootService.currentGame
        checkNotNull(game) { "No started game found." }

        cardMap.clear()

        val cardImageLoader = CardImageLoader()

        initializeStackView(game.playArea.deck, deck, cardImageLoader)
    }
    /**
     * clears [stackView], adds a new [CardView] for each
     * element of [stack] onto it, and adds the newly created view/card pair
     * to the global [cardMap].
     */
    private fun initializeStackView(stack: Deck, stackView: LabeledStackView, cardImageLoader: CardImageLoader) {
        stackView.clear()
        stack.peekAll().reversed().forEach { card ->
            val cardView = CardView(
                height = 200,
                width = 130,
                front = ImageVisual(cardImageLoader.frontImageFor(card.cardsuit, card.cardvalue)),
                back = ImageVisual(cardImageLoader.backImage)
            )
            stackView.add(cardView)
            cardMap.add(card to cardView)//3shan hya Map fa byrbot al atnyn bb3d
        }
    }
    /**
     * moves the views (as per [cardMap]) of all [movedCards] from current container onto
     * the provided [toStack], so that last element of [movedCards] is on on top of the stack
     * afterwards.
     *
     * @see moveCardView
     */
    private fun moveAllCardViews(movedCards: List<Card>, toStack: LabeledStackView) {
        movedCards.forEach { card ->
            val cardView = cardMap.forward(card)
            moveCardView(cardView, toStack)
        }
    }
    /**
     * moves a [cardView] from current container on top of [toStack].
     *
     * @param flip if true, the view will be flipped from [CardView.CardSide.FRONT] to
     * [CardView.CardSide.BACK] and vice versa.
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

}



