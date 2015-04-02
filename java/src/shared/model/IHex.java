package shared.model;

import shared.definitions.HexType;
import shared.definitions.ResourceType;
import shared.locations.HexLocation;

import java.io.Serializable;


public interface IHex extends Serializable {

    public HexType type();


    public HexLocation location();


    public ResourceType resource();


    public int numberToken();


    public boolean hasRobber();


    public void placeRobber();


    public void removeRobber();
}
