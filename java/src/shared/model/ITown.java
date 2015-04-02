package shared.model;

import shared.locations.VertexLocation;

import java.io.Serializable;


public interface ITown extends Serializable {


    public IPlayer getOwner();


    public VertexLocation getLocation();


    public void setLocation(VertexLocation location);


    public int getVictoryPoints();


    public int getResourceCount();

}

