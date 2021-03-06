package service
import view.Refreshable
/**
 * [Refreshable] implementation that refreshes nothing, but remembers
 * if a refresh method has been called (since last [reset])
 */
class TestRefreshable: Refreshable {

    var refreshAfterGameStartCalled: Boolean = false
        private set

    var refreshAfterGameFinishedCalled: Boolean = false
        private set

    var refreshAfterCardDrawnCalled: Boolean = false
        private set

    var refreshAfterCardSwapCalled: Boolean = false
        private set

    var refreshAfterKnockedCalled: Boolean = false
        private set

    var refreshAfterPassedCalled: Boolean = false
        private set

    var refreshAfterTurnChangedCalled: Boolean = false
        private set


    /**
     * resets all *Called properties to false
     */
    fun reset() {
        refreshAfterGameStartCalled = false
        refreshAfterGameFinishedCalled = false
        refreshAfterCardDrawnCalled=false
        refreshAfterCardSwapCalled = false
        refreshAfterKnockedCalled = false
        refreshAfterPassedCalled = false
        refreshAfterTurnChangedCalled = false
    }

    override fun refreshAfterGameStart() {
        refreshAfterGameStartCalled = true
    }

    override fun refreshAfterGameFinished() {
        refreshAfterGameFinishedCalled = true
    }

     fun refreshAfterCardDrawn() {
        refreshAfterCardDrawnCalled = true
    }

     fun refreshAfterCardSwap() {
        refreshAfterCardSwapCalled = true
    }

    override fun refreshAfterKncoked() {
        refreshAfterKnockedCalled = true
    }

    override fun refreshAfterPassed() {
        refreshAfterPassedCalled = true
    }
    override fun refreshAfterTurnChanged() {
        refreshAfterTurnChangedCalled = true
    }
}