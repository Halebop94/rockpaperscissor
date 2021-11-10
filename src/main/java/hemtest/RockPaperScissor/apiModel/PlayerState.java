package hemtest.RockPaperScissor.apiModel;

public class PlayerState {
    private String name;

    /**
     * Returns the name of player from incoming JSON body
     * @return name of player
     */
    public String getName() {
        return name;
    }
}
