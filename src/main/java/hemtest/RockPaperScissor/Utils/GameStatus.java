package hemtest.RockPaperScissor.Utils;

import hemtest.RockPaperScissor.Model.Status;

import java.util.UUID;

public class GameStatus {
    private UUID game_id;
    private Status status;
    private String playerOne;
    private String playerTwo;
    private String winner;

    public void setID(UUID id) {
        this.game_id = id;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPlayerOne(String playerOne) {
        this.playerOne = playerOne;
    }
    public void setPlayerTwo(String playerTwo) {
        this.playerTwo = playerTwo;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public UUID getId(){
        return game_id;
    }

    public Status getStatus() {
        return status;
    }

    public String getPlayerOne() {
        return playerOne;
    }

    public String getPlayerTwo() {
        return playerTwo;
    }

    public String getWinner() {
        return winner;
    }
}
