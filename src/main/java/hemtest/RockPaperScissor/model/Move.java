package hemtest.RockPaperScissor.model;

public enum Move {
    Rock(1),
    Paper(2),
    Scissor(3),
    No_Move(0),
    Hidden(-1);

    private final int move;

    Move(int move) {
        this.move = move;
    }

    /**
     * Checks if this.move is better than move.move. Returns true if this.move winner
     * @param move - other players move
     * @return true if this move wins, otherwise false
     * @throws IllegalStateException if one of the players haven't done a move.
     */
    public boolean winsOver(Move move) {
        if (this.move == 0 || move.move == 0) {
            throw new IllegalStateException();
        }
        switch (this.move) {
            case 1: return (3 == move.move);
            case 2: return (1 == move.move);
            case 3: return (2 == move.move);
            default: return false;
        }
    }
}
