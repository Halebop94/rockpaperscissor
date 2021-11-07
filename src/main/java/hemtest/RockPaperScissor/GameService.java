package hemtest.RockPaperScissor;

import hemtest.RockPaperScissor.Model.Game;
import hemtest.RockPaperScissor.Model.Player;
import hemtest.RockPaperScissor.Utils.GameStatus;
import hemtest.RockPaperScissor.Utils.MoveStatus;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

@Component
public class GameService {

    private HashMap<UUID,Game> games;

    public GameService() {
        games = new HashMap<UUID, Game>();
    }

    public GameStatus addGame(MoveStatus game) {
        Player player = new Player(game.getName());
        Game g = new Game();
        g.addPlayer(player);
        UUID id = g.getUuid();
        games.put(id, g);
        return g.getGameStatus();
    }

    public Game getGame(UUID uuid) throws GameDoesNotExistException {
        Game game = games.get(uuid);
        if (game == null)
            throw new GameDoesNotExistException();
        return game;
    }

    public GameStatus getGameStatus(UUID id) {
        try {
            return getGame(id).getGameStatus();
        } catch (GameDoesNotExistException g) {
            return new GameStatus();
        }
    }
}
