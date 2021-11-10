package hemtest.RockPaperScissor.apiModel;

import hemtest.RockPaperScissor.model.Game;
import hemtest.RockPaperScissor.model.Move;
import hemtest.RockPaperScissor.model.Player;
import hemtest.RockPaperScissor.model.State;

public class StatusState {
    private State state;
    private String playerOne;
    private String playerTwo;
    private Move playerOneMove;
    private Move playerTwoMove;

    /**
     * Creates a new StatusState för a spcific game.
     * If no player has joined, name is set to No Player.
     * If the game is Not_Finished moves are Hidden, otherwise they show.
     * @param game a game
     */
    public StatusState(Game game) {
        this.state = game.getResult();
        Player player1 = game.getPlayerOne();
        Player player2 = game.getPlayerTwo();
        this.playerOne = player1.getName();
        this.playerTwo = player2.getName();
        if (this.state == State.Not_finished) {
            this.playerOneMove = Move.Hidden;
            this.playerTwoMove = Move.Hidden;
        } else {
            this.playerOneMove = player1.getMove();
            this.playerTwoMove = player2.getMove();
        }
    }

    /**
     * Returns state to outgoing JSON body
     * @return state
     */
    public State getState() {
        return state;
    }

    /**
     * Returns player one to outgoing JSON body
     * @return player one
     */
    public String getPlayerOne() {
        return playerOne;
    }

    /**
     * Returns player two to outgoing JSON body
     * @return player two
     */
    public String getPlayerTwo() {
        return playerTwo;
    }

    /**
     * Returns move from player one to outgoing JSON body
     * @return move by player one
     */
    public Move getPlayerOneMove() {
        return playerOneMove;
    }

    /**
     * Returns move from player two to outgoing JSON body
     * @return move by player two
     */
    public Move getPlayerTwoMove() {
        return playerTwoMove;
    }
}
