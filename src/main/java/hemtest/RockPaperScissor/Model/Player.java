package hemtest.RockPaperScissor.Model;

import hemtest.RockPaperScissor.Model.Move;

public class Player {

    private final String name;
    private Move move;

    public Player(String name) {
        this.name = name;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public String getName(){
        return name;
    }

    public Move getMove() {
        return move;
    }
}
