package hemtest.RockPaperScissor.model;

import java.util.UUID;

public class Game {

    private State state;
    private final UUID uuid;
    private Player playerOne;
    private Player playerTwo;

    public Game () {
        this.state = State.Not_finished;
        this.uuid = UUID.randomUUID();
    }

    /**
     * Returns the UUID of the game
     * @return the game id
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * Add player to the game
     * @param player - pleyer to be added.
     * @throws IllegalStateException if a thrid player tries to join.
     */
    public void addPlayer(Player player) {
        if (playerOne == null) {
            playerOne = player;
        } else if (playerTwo == null) {
            playerTwo = player;
        } else {
            throw new IllegalStateException("Player already exists");
        }
    }

    /**
     * Fetches a specific player.
     * @param name - name of player
     * @return the player
     * @throws IllegalArgumentException if player does not exist
     */
    public Player getPlayer(String name) {
        if (playerOne != null && playerOne.getName().equals(name)) {
            return playerOne;
        } else if (playerTwo != null && playerTwo.getName().equals(name)) {
            return playerTwo;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Returns player one
     * @return - playerOne
     */
    public Player getPlayerOne() {
        if (playerOne == null) {
            return new Player("No player");
        }
        return playerOne;
    }

    /**
     * Returns player two
     * @return playerTwo
     */
    public Player getPlayerTwo() {
        if (playerTwo == null) {
            return new Player("No player");
        }
        return playerTwo;
    }

    /**
     * Checks the state of the game and decides the winner.
     * @return the state of the game
     */
    public State getResult() {
        if (playerOne == null || playerTwo == null) {
            return State.Not_finished;
        }
        try {
            boolean playOne = playerOne.getMove().winsOver(playerTwo.getMove());
            boolean playTwo = playerTwo.getMove().winsOver(playerOne.getMove());
            if (playOne && !playTwo) {
                this.state = State.PlayerOneWon;
            }
            if (playTwo && !playOne) {
                this.state = State.PlayerTwoWon;
            }
            if (!playOne && !playTwo) {
                this.state = State.Draw;
            }
        } catch (IllegalArgumentException e) {
            return State.Not_finished;
        }
        return this.state;
    }
}
