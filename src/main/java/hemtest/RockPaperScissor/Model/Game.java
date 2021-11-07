package hemtest.RockPaperScissor.Model;

import hemtest.RockPaperScissor.Utils.GameStatus;

import java.util.UUID;

public class Game {

    private Status status;

    private final UUID uuid = UUID.randomUUID();
    private Player playerOne;
    private Player playerTwo;

    public Game () {
        this.status = Status.started;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void addPlayer(Player player) {
        if (playerOne == null) {
            playerOne = player;
        } else if (playerTwo == null) {
            playerTwo = player;
        } else {
            throw new IllegalStateException("Player already exists");
        }
    }

    public void updateMove(String name, Move move) {
        if (playerOne.getName().equals(name)) {
            playerOne.setMove(move);
        } else if (playerTwo.getName().equals(name)) {
            playerTwo.setMove(move);
        }
        if (playerOne != null && playerTwo != null) {
            if (playerOne.getMove() != null && playerTwo.getMove() != null) {
                status = Status.finished;
            }
        }
    }

    public GameStatus getGameStatus() {
       GameStatus toReturn = new GameStatus();
       toReturn.setID(uuid);
       toReturn.setStatus(status);
       toReturn.setPlayerOne(playerOne.getName());
       if (playerTwo != null ) {toReturn.setPlayerTwo(playerTwo.getName());}
       if (status == Status.finished) {
           Result result = getResult();
           if(result == Result.Win){
               toReturn.setWinner(playerOne.getName());
           } else if (result == Result.Loose){
               toReturn.setWinner(playerTwo.getName());
           } else {
               toReturn.setWinner("Draw");
           }
       }
       return toReturn;
    }

    private Result getResult(){
        Move playOne = playerOne.getMove();
        Move playTwo = playerTwo.getMove();
        if (playOne == playTwo) {
            return Result.Draw;
        } else if (playOne==Move.Paper && playTwo == Move.Rock){
            return Result.Win;
        } else if (playOne==Move.Rock && playTwo == Move.Scissor) {
            return Result.Win;
        } else if (playOne == Move.Scissor && playTwo == Move.Paper) {
            return Result.Win;
        } else {
            return Result.Loose;
        }
    }
}
