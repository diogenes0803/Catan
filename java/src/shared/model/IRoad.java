package shared.model;

import shared.locations.EdgeLocation;

import java.io.Serializable;


public interface IRoad extends Serializable {

    public IPlayer getOwner();


    public EdgeLocation getLocation();


    public void setLocation(EdgeLocation edge);
}
