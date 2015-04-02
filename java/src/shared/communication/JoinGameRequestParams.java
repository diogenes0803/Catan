package shared.communication;

import shared.definitions.CatanColor;


public class JoinGameRequestParams extends AbstractJoinGameParams{
    public final int id;
    public final CatanColor color;

    public JoinGameRequestParams(int id, CatanColor color) {
        this.id = id;
        this.color = color;
    }
}
