package hemtest.RockPaperScissor.controller;

import java.util.UUID;

import hemtest.RockPaperScissor.apiModel.GameState;
import hemtest.RockPaperScissor.apiModel.MoveState;
import hemtest.RockPaperScissor.apiModel.PlayerState;
import hemtest.RockPaperScissor.apiModel.StatusState;
import hemtest.RockPaperScissor.model.Game;
import hemtest.RockPaperScissor.model.Player;
import hemtest.RockPaperScissor.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class GameController {
    private final GameService gameService;


    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Creates a game and returns a GameStatus with a game_id.
     *
     * @return a game_id
     */
    @PostMapping(path = "/games", produces = "application/json")
    public GameState games() {
        UUID id = gameService.createGame();
        return new GameState(id);
    }

    /**
     * Returns a StatusState of a specified game
     * @param id - id of the game.
     * @return game status
     * @throws ResponseStatusException if game does not exist or wrong id format
     */
    @GetMapping(path = "/games/{id}", consumes = "application/json", produces = "application/json")
    public StatusState getGame(@PathVariable String id) {
        try {
            Game game = gameService.getGame(UUID.fromString(id));
            if (game == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game does not exist");
            }
            return new StatusState(game);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong id format");
        }
    }

    /**
     * A player joins a game.
     * @param id - id of the game.
     * @param name - name of player in JSON body
     * @throws ResponseStatusException if game does not exist or wrong id format or third player tries to join.
     */
    @PostMapping(path = "/games/{id}/join", consumes = "application/json", produces = "application/json")
    public void joinGame( @PathVariable String id, @RequestBody PlayerState name) {
        try {
            Game game = gameService.getGame(UUID.fromString(id));
            if (game == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game does not exist");
            }
            game.addPlayer(new Player(name.getName()));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
        }

    }

    /**
     * Registers a move for specified player.
     * @param id - id of the game
     * @param moveState - name and move in JSON body
     * @throws ResponseStatusException if game does not exist, illegal move, second move by player,
     *         player does not exist, wrong id format
     */
    @PostMapping(path = "/games/{id}/move", consumes = "application/json", produces = "application/json")
    public void registerMove(@PathVariable String id, @RequestBody MoveState moveState) {
        try {
            Game g = gameService.getGame(UUID.fromString(id));
            if (g == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
            }
            try {
                Player player = g.getPlayer(moveState.getName());
                try {
                    player.setMove(moveState.getMove());
                } catch (IllegalArgumentException e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Move Hidden is not allowed");
                } catch (IllegalStateException e) {
                    throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Move has already been made");
                }
            } catch (IllegalArgumentException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player does not exist");
            }
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong id format");
        }
    }

}