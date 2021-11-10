package hemtest.RockPaperScissor.service;

import hemtest.RockPaperScissor.model.Game;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

@Component
public class GameService {

    private HashMap<UUID,Game> games;

    public GameService() {
        games = new HashMap<UUID, Game>();
    }

    /**
     * Creates a game and an id
     * @return id in UUID
     */
    public UUID createGame() {
        Game g = new Game();
        UUID id = g.getUuid();
        games.put(id, g);
        return g.getUuid();
    }

    /**
     * Fetches a specific game
     * @param uuid - id of game
     * @return a game
     */
    public Game getGame(UUID uuid) {
        return games.get(uuid);
    }
}
