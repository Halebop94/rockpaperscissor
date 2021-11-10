package hemtest.RockPaperScissor.model;

public class Player {

    private final String name;
    private Move move;

    public Player(String name) {
        this.name = name;
        this.move = Move.No_Move;
    }

    /**
     * Sets move for player.
     * @param move players choice of move
     * @throws IllegalStateException if player already made a move
     * @throws IllegalArgumentException if player tries to do move hidden.
     */
    public void setMove(Move move) {
        if (this.move != Move.No_Move) {
            throw new IllegalStateException();
        }
        if (move == Move.Hidden) {
            throw new IllegalArgumentException();
        }
        this.move = move;
    }

    /**
     * Returns name of player.
     * @return name of player
     */
    public String getName(){
        return name;
    }

    /**
     * Returns move by player
     * @return move by player
     */
    public Move getMove() {
        return move;
    }
}
