package shared.model;


public interface IModelInitializer {

    public void initializeClientModel(String clientModelJson, int localPlayerID) throws ModelException;
}
