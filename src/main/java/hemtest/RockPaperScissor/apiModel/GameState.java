package hemtest.RockPaperScissor.apiModel;

import java.util.UUID;

public class GameState {
    private UUID game_id;

    public GameState(UUID id) {
        this.game_id = id;
    }

    /**
     * Returns the game id to JSON body.
     * @return game id
     */
    public UUID getGame_id() {
        return game_id;
    }
}
