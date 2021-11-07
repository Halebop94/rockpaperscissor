package hemtest.RockPaperScissor.Utils;

import hemtest.RockPaperScissor.Model.Move;

public class MoveStatus {
    private String name;
    private Move move;

    public String getName() {
        return name;
    }

    public Move getMove(){
        return move;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMove(Move move) {
        this.move = move;
    }
}
