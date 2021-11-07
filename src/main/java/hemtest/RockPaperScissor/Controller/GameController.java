package hemtest.RockPaperScissor.Controller;

import java.util.UUID;

import hemtest.RockPaperScissor.*;
import hemtest.RockPaperScissor.Model.Game;
import hemtest.RockPaperScissor.Model.Player;
import hemtest.RockPaperScissor.Utils.GameStatus;
import hemtest.RockPaperScissor.Utils.MoveStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {

    //private static final String template = "Hello, %s!";
    //private final AtomicLong counter = new AtomicLong();

    private GameService gameService;


    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/games")
    public GameStatus games(@RequestBody MoveStatus name) {
        return gameService.addGame(name);
    }

    @GetMapping("/games/{id}")
    public GameStatus getGame(@PathVariable UUID id) {
        return gameService.getGameStatus(id);
    }

    @PostMapping("/games/{id}/join")
    public GameStatus addPlayer(@RequestBody MoveStatus name, @PathVariable UUID id) {
        try {
            Game game = gameService.getGame(id);
            game.addPlayer(new Player(name.getName()));
            return game.getGameStatus();
        } catch (GameDoesNotExistException g) {
            return new GameStatus();
        }

    }

    @PostMapping("/games/{id}/move")
    public void registerMove(@PathVariable UUID id, @RequestBody MoveStatus game) {
        try {
            Game g = gameService.getGame(id);
            g.updateMove(game.getName(), game.getMove());
        } catch (GameDoesNotExistException g) {
        }
    }
}