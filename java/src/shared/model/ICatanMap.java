package shared.model;

import shared.definitions.PortType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;


public interface ICatanMap extends Serializable {

    public Collection<ITown> getTowns();


    public Collection<ITown> getCities();


    public Collection<ITown> getSettlements();


    public Collection<IRoad> getRoads();


    public Collection<IHex> getTiles();


    public Map<EdgeLocation, PortType> getPorts();


    public IRoad getRoad(EdgeLocation edge);


    public Set<PortType> getPlayersPorts(IPlayer player);


    public ITown getTownAt(VertexLocation vertexLoc);


    public boolean canPlaceRoad(IPlayer player, EdgeLocation edge);


    public boolean canPlaceInitialRoad(IPlayer player, EdgeLocation edge);


    boolean canPlaceTwoRoads(IPlayer player, EdgeLocation firstEdge, EdgeLocation secondEdge);


    public boolean canPlaceSettlement(IPlayer player, VertexLocation vertex);


    public boolean canPlaceCity(IPlayer player, VertexLocation vertex);


    public void placeRoad(IRoad road, EdgeLocation edge);


    public void placeSettlement(Settlement settlement, VertexLocation vertex);


    public void placeCity(City city, VertexLocation vertex);


    public HexLocation getRobber();


    public Collection<IPlayer> getRobbablePlayersOnTile(HexLocation tile, IPlayer exclude);


    public void moveRobber(HexLocation location);


    public boolean canPlaceRobber(HexLocation location);


    public int getPlayersLongestRoad(IPlayer player);


    public void distributeResources(int number, IResourceBank gameResourceBank);


    public void distributeInitialResources(ITown town, IResourceBank gameResourceBank);
}
