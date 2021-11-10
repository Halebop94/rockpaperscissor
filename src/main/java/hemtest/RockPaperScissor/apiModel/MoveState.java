package hemtest.RockPaperScissor.apiModel;

import hemtest.RockPaperScissor.model.Move;

public class MoveState {
    private String name;
    private Move move;

    /**
     * Returns the name of player from incoming JSON body
     * @return name of player
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the move by player from incoming JSON body
     * @return move by player.
     */
    public Move getMove(){
        return move;
    }
}
