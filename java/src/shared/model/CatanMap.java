package shared.model;

import shared.definitions.PortType;
import shared.locations.*;

import java.util.*;
import java.util.logging.Logger;


public class CatanMap implements ICatanMap {
    private static Logger logger = Logger.getLogger("catanserver");

    private Map<HexLocation, IHex> m_tiles;
    private Map<VertexLocation, ITown> m_towns;
    private Map<EdgeLocation, IRoad> m_roads;
    private Map<EdgeLocation, PortType> m_ports;
    private HexLocation m_robber;


    public CatanMap(Map<HexLocation, IHex> tiles, Map<EdgeLocation, PortType> ports) {
        m_tiles = tiles;
        m_ports = ports;
        m_towns = new HashMap<>();
        m_roads = new HashMap<>();
        m_robber = null;
    }


    public CatanMap(Map<HexLocation, IHex> tiles, Map<VertexLocation, ITown> towns, Map<EdgeLocation, IRoad> roads,
                    Map<EdgeLocation, PortType> ports, HexLocation robber) throws ModelException {

        this.m_tiles = tiles;
        this.m_towns = towns;
        this.m_roads = roads;
        this.m_ports = ports;
        this.m_robber = robber;

        // check that all pieces on tiles
        for (EdgeLocation edge : m_ports.keySet()) {
            if (!isOnMap(edge)) throw new ModelException("Some pieces are off the map!");
        }

        for (EdgeLocation edge : m_roads.keySet()) {
            if (!isOnMap(edge)) throw new ModelException("Some pieces are off the map!");
        }

        for (VertexLocation vertex : m_towns.keySet()) {
            if (!isOnMap(vertex)) throw new ModelException("Some pieces are off the map!");
        }
        
        // Apparently we can't use Java 8 after all


        // Set robber flag in tile if it is not already set
        if (!m_tiles.get(robber).hasRobber()) {
            m_tiles.get(robber).placeRobber();
        }
    }

    @Override
    public Collection<ITown> getTowns() {
        return m_towns.values();
    }

    @Override
    public Collection<ITown> getCities() {
        Collection<ITown> cities = new ArrayList<>();
        for (ITown town : m_towns.values()) {
            if (town.getClass().equals(City.class)) {
                cities.add(town);
            }
        }

        return cities;
    }

    @Override
    public Collection<ITown> getSettlements() {
        Collection<ITown> settlements = new ArrayList<>();
        for (ITown town : m_towns.values()) {
            if (town.getClass().equals(Settlement.class)) {
                settlements.add(town);
            }
        }

        return settlements;
    }

    @Override
    public Collection<IRoad> getRoads() {
        return m_roads.values();
    }

    @Override
    public Collection<IHex> getTiles() {
        return m_tiles.values();
    }

    @Override
    public Map<EdgeLocation, PortType> getPorts() {
        return Collections.unmodifiableMap(m_ports);
    }

    @Override
    public ITown getTownAt(VertexLocation vertexLoc) {
        return m_towns.get(vertexLoc.getNormalizedLocation());
    }

    @Override
    public IRoad getRoad(EdgeLocation edge) {
        return m_roads.get(edge.getNormalizedLocation());
    }

    @Override
    public Set<PortType> getPlayersPorts(IPlayer player) {
        Set<PortType> ports = new HashSet<>();

        for (Map.Entry<EdgeLocation, PortType> entry : m_ports.entrySet()) {
            for (ITown town : getAdjacentTowns(entry.getKey())) {
                if (player.equals(town.getOwner())) {
                    ports.add(entry.getValue());
                }
            }
        }

        return Collections.unmodifiableSet(ports);
    }

    @Override
    public boolean canPlaceRoad(IPlayer player, EdgeLocation edge) {
        assert player != null && edge != null;

        edge = edge.getNormalizedLocation();

        // check is the edge is on land (not water)
        if (!isOnMap(edge)) {
            return false;
        }

        // check if a road is already placed
        if (m_roads.containsKey(edge)) {
            return false;
        }

        // check if there is a neighboring town
        Collection<ITown> neighbors = getAdjacentTowns(edge);
        for (ITown neighborTown : neighbors) {
            if (player.equals(neighborTown.getOwner())) {
                return true;
            }
        }

        // check if there is a connecting road
        for (IRoad neighborRoad : getAdjacentRoads(edge)) {
            if (player.equals(neighborRoad.getOwner())
                    && roadIsNotBrokenByOpponentTown(player, edge, neighborRoad)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean canPlaceInitialRoad(IPlayer player, EdgeLocation edge) {
        assert edge != null && player != null;

        edge = edge.getNormalizedLocation();

        // roads must be placed on the map, not on top of another road, and not next to a town
        if (!isOnMap(edge) || m_roads.containsKey(edge) || !getAdjacentTowns(edge).isEmpty()) {
            return false;
        }

        // make sure that a settlement can be placed next to the road
        for (VertexLocation vertex : edge.getAdjacentVertices()) {
            if (canPlaceSettlementWithoutRoad(vertex)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean canPlaceTwoRoads(IPlayer player, EdgeLocation firstEdge, EdgeLocation secondEdge) {
        assert player != null && firstEdge != null && secondEdge != null;

        firstEdge = firstEdge.getNormalizedLocation();
        secondEdge = secondEdge.getNormalizedLocation();

        boolean canPlaceBoth = false;

        if (canPlaceRoad(player, firstEdge)) {
            assert !m_roads.containsKey(firstEdge);

            // temporarily place the first road
            m_roads.put(firstEdge, new Road(player, firstEdge));

            canPlaceBoth = canPlaceRoad(player, secondEdge);

            m_roads.remove(firstEdge);
        }

        return canPlaceBoth;
    }


    private boolean roadIsNotBrokenByOpponentTown(IPlayer player, EdgeLocation proposedRoadLocation, IRoad currentRoad) {
        ITown town = getTownBetweenEdges(proposedRoadLocation, currentRoad.getLocation());

        return town == null || player.equals(town.getOwner());
    }

    @Override
    public boolean canPlaceSettlement(IPlayer player, VertexLocation vertex) {
        vertex = vertex.getNormalizedLocation();

        if (!canPlaceSettlementWithoutRoad(vertex)) {
            return false;
        }

        // check that the player has a road connecting to this vertex
        for (IRoad road : getAdjacentRoads(vertex)) {
            if (player.equals(road.getOwner())) {
                return true;
            }
        }

        return false;
    }


    private boolean canPlaceSettlementWithoutRoad(VertexLocation vertex) {
        vertex = vertex.getNormalizedLocation();

        // check if the vertex is occupied
        if (m_towns.containsKey(vertex)) {
            return false;
        }

        // check if vertex is on the map
        if (!isOnMap(vertex)) {
            return false;
        }

        // check if there are any adjacent towns
        if (!getAdjacentTowns(vertex).isEmpty()) {
            return false;
        }

        return true;
    }

    @Override
    public boolean canPlaceCity(IPlayer player, VertexLocation vertex) {
        assert player != null && vertex != null;

        ITown town = m_towns.get(vertex.getNormalizedLocation());

        return town != null && town instanceof Settlement && player.equals(town.getOwner());
    }

    @Override
    public void placeRoad(IRoad road, EdgeLocation edge) {
        assert road != null && edge != null;
        assert isOnMap(edge) : "Edge not on map.";

        edge = edge.getNormalizedLocation();

        assert !m_roads.containsKey(edge) :  "There is already a road placed at " + edge.toString();
        assert road.getLocation() == null : "The road " + road.toString() + " already thinks it's placed!";

        m_roads.put(edge, road);
        road.setLocation(edge);
    }

    @Override
    public void placeSettlement(Settlement settlement, VertexLocation vertex) {
        vertex = vertex.getNormalizedLocation();

        assert !m_towns.containsKey(vertex) : "There is already a settlement placed at " + vertex.toString();
        assert settlement.getLocation() == null : "The settlement " + settlement.toString() + " already thinks it's placed!";

        m_towns.put(vertex, settlement);
        settlement.setLocation(vertex);
    }

    @Override
    public void placeCity(City city, VertexLocation vertex) {
        vertex = vertex.getNormalizedLocation();

        assert m_towns.containsKey(vertex): "There not already a settlement placed at " + vertex.toString();
        assert m_towns.get(vertex) instanceof Settlement : "A city must be placed on a settlement only!";
        assert city.getLocation() == null : "The city " + city.toString() + " already thinks it's placed!";

        m_towns.put(vertex, city);
        city.setLocation(vertex);
    }

    @Override
    public HexLocation getRobber() {
        return m_robber;
    }

    @Override
    public void moveRobber(HexLocation location) {
        assert m_tiles.containsKey(location);

        m_tiles.get(m_robber).removeRobber();

        m_robber = location;
        m_tiles.get(location).placeRobber();
    }

    @Override
    public boolean canPlaceRobber(HexLocation location) {
        return !location.equals(m_robber) && isOnMap(location);
    }

    @Override
    public Set<IPlayer> getRobbablePlayersOnTile(HexLocation tile, IPlayer exclude) {
        Set<IPlayer> players = new HashSet<>();

        for (VertexDirection dir : VertexDirection.values()) {
            VertexLocation loc = new VertexLocation(tile, dir).getNormalizedLocation();
            if (m_towns.containsKey(loc)) {
                IPlayer player = m_towns.get(loc).getOwner();

                if (!player.equals(exclude) && player.hasResources()) {
                    players.add(player);
                }
            }
        }

        return players;
    }

    @Override
    public int getPlayersLongestRoad(IPlayer player) {
        if (player == null) {
            return 0; // if no longest road, just return 0
        }

        int longestSoFar = 0;

        // could optimize by only checking starting from roads at intersections or ends... but this is fast enough
        for (IRoad road : player.getRoads()) {
            // road length starts at 1
            Set<EdgeLocation> pathEdges = new HashSet<>();
            pathEdges.add(road.getLocation().getNormalizedLocation());

            int pathLength = longestRoad(player, road, pathEdges, longestSoFar);
            if (pathLength > longestSoFar) {
                longestSoFar = pathLength;
            }
        }

        logger.finest("Player " + player.getName() + "'s longest road is " + longestSoFar);

        return longestSoFar;
    }



    private int longestRoad(IPlayer player, IRoad startRoad, Set<EdgeLocation> pathEdges, int longestSoFar) {
        assert pathEdges.contains(startRoad.getLocation()) : "The start road should be added to the path before calling this function.";

        // find the roads that can legally be added to the path
        Collection<IRoad> candidateRoads = getCandidateRoads(player, startRoad, pathEdges);

        // if there are no branches to explore, stop searching this branch
        if (candidateRoads.isEmpty()) {
            return pathEdges.size();
        }

        // start a new search down each branch
        for (IRoad road : candidateRoads) {
            // look for the longest path with the next road added
            pathEdges.add(road.getLocation());

            int pathLength = longestRoad(player, road, pathEdges, longestSoFar);
            if (pathLength > longestSoFar) {
                longestSoFar = pathLength;
            }

            pathEdges.remove(road.getLocation());
        }

        return longestSoFar;
    }


    private Collection<IRoad> getCandidateRoads(IPlayer player, IRoad lastRoad, Set<EdgeLocation> pathEdges) {
        Collection<EdgeLocation> candidateEdges = new ArrayList<>();
        EdgeLocation startEdge = lastRoad.getLocation();

        EdgeLocation edge1;
        EdgeLocation edge2;

        // get the edges on one end of the road -- don't add them if either is on the path (prevent backtracking in a path)
        edge1 = startEdge.getNormalizedClockwise();
        edge2 = startEdge.getEquivalentEdge().getNormalizedCounterClockwise();
        if (!pathEdges.contains(edge1) && !pathEdges.contains(edge2) && roadIsNotBrokenByOpponentTown(player, edge1, lastRoad)) {
            candidateEdges.add(edge1);
            candidateEdges.add(edge2);
        }

        // now, do the same for the other end of the road
        edge1 = startEdge.getNormalizedCounterClockwise();
        edge2 = startEdge.getEquivalentEdge().getNormalizedClockwise();
        if (!pathEdges.contains(edge1) && !pathEdges.contains(edge2) && roadIsNotBrokenByOpponentTown(player, edge1, lastRoad)) {
            candidateEdges.add(edge1);
            candidateEdges.add(edge2);
        }

        // add the player's roads on the candidate edges, if any
        Collection<IRoad> roads = new ArrayList<>();
        for (EdgeLocation edge : candidateEdges) {
            if (m_roads.containsKey(edge)) {
                IRoad road = m_roads.get(edge);

                if (player.equals(road.getOwner())) {
                    roads.add(road);
                }
            }
        }

        return roads;
    }

    private Collection<IHex> getAdjacentTiles(EdgeLocation edge) {
        assert edge != null;
        edge = edge.getNormalizedLocation();

        Collection<IHex> tiles = new ArrayList<>();

        HexLocation hexLoc = edge.getHexLoc();
        HexLocation otherHexLoc = hexLoc.getNeighborLoc(edge.getDir());

        // exterior edges are only adjacent to one tile, so
        // check if the tile is a "water" tile
        if (m_tiles.containsKey(hexLoc)) {
            tiles.add(m_tiles.get(hexLoc));
        }

        if (m_tiles.containsKey(otherHexLoc)) {
            tiles.add(m_tiles.get(otherHexLoc));
        }

        return tiles;
    }

    private Collection<IHex> getAdjacentTiles(VertexLocation location) {
        location = location.getNormalizedLocation();

        Set<IHex> tiles = new HashSet<>();

        // This is inefficient, but it works and doesn't involve writing new code
        for (EdgeLocation edge : location.getAdjacentEdges()) {
            tiles.addAll(getAdjacentTiles(edge));
        }

        assert tiles.size() <= 3;

        return tiles;
    }


    private Collection<IRoad> getAdjacentRoads(EdgeLocation edge) {
        Collection<IRoad> roads = new ArrayList<>();
        for (EdgeLocation neighbor : edge.getAdjacentEdges()) {
            if (m_roads.containsKey(neighbor)) {
                roads.add(m_roads.get(neighbor));
            }
        }

        return roads;
    }

    private Collection<IRoad> getAdjacentRoads(VertexLocation vertex) {
        Collection<IRoad> roads = new ArrayList<>();
        for (EdgeLocation neighbor : vertex.getAdjacentEdges()) {
            if (m_roads.containsKey(neighbor)) {
                roads.add(m_roads.get(neighbor));
            }
        }

        return roads;
    }

    private Collection<ITown> getAdjacentTowns(EdgeLocation edge) {
        Collection<ITown> towns = new ArrayList<>();

        for (VertexLocation neighbor : edge.getAdjacentVertices()) {
            if (m_towns.containsKey(neighbor)) {
                towns.add(m_towns.get(neighbor));
            }
        }

        return towns;
    }

    private Collection<ITown> getAdjacentTowns(HexLocation hexLoc) {
        Collection<ITown> towns = new ArrayList<>();

        for (VertexDirection dir : VertexDirection.values()) {
            VertexLocation loc = new VertexLocation(hexLoc, dir).getNormalizedLocation();
            if (m_towns.containsKey(loc)) {
                towns.add(m_towns.get(loc));
            }
        }

        return towns;
    }

    private Collection<ITown> getAdjacentTowns(VertexLocation vertex) {
        Collection<ITown> towns = new ArrayList<>();

        for (VertexLocation neighbor : vertex.getAdjacentVertices()) {
            if (m_towns.containsKey(neighbor)) {
                towns.add(m_towns.get(neighbor));
            }
        }

        return towns;
    }

    private ITown getTownBetweenEdges(EdgeLocation edge1, EdgeLocation edge2) {
        return m_towns.get(EdgeLocation.getVertexBetweenEdges(edge1, edge2));
    }

    private boolean isOnMap(HexLocation hex) {
        return m_tiles.containsKey(hex);
    }


    private boolean isOnMap(EdgeLocation edge) {
        return m_tiles.containsKey(edge.getHexLoc())
                || m_tiles.containsKey(edge.getEquivalentEdge().getHexLoc());
    }


    private boolean isOnMap(VertexLocation vertex) {
        HexLocation hexLoc =  vertex.getHexLoc();
        // test the vertex's hex loc
        if (m_tiles.containsKey(hexLoc)) {
            return true;
        }

        // check the other hex locs this is adjacent to
        for (EdgeDirection dir : vertex.getDir().getNeighboringEdgeDirections()) {
            if (m_tiles.containsKey(hexLoc.getNeighborLoc(dir))) {
                return true;
            }
        }

        // didn't find any of the locs...
        return false;
    }

    @Override
    public void distributeResources(int number, IResourceBank gameResourceBank) {
        IResourceBank resourcesToGive = new ResourceBank();

        for (IHex tile : m_tiles.values()) {
            if (tile.numberToken() == number && !tile.hasRobber()) {
                for (ITown town : getAdjacentTowns(tile.location())) {
                    resourcesToGive.add(town.getResourceCount(), tile.resource());
                }
            }
        }

        if (gameResourceBank.getBrick() < resourcesToGive.getBrick()) {
            resourcesToGive.setBrick(0);
        }
        if (gameResourceBank.getWood() < resourcesToGive.getWood()) {
            resourcesToGive.setWood(0);
        }
        if (gameResourceBank.getSheep() < resourcesToGive.getSheep()) {
            resourcesToGive.setSheep(0);
        }
        if (gameResourceBank.getWheat() < resourcesToGive.getWheat()) {
            resourcesToGive.setWheat(0);
        }
        if (gameResourceBank.getOre() < resourcesToGive.getOre()) {
            resourcesToGive.setOre(0);
        }

        gameResourceBank.subtract(resourcesToGive);

        for (IHex tile : m_tiles.values()) {
            if (tile.numberToken() == number && !tile.hasRobber()) {
                giveResourcesToAdjacentTowns(tile, resourcesToGive);
            }
        }

        assert resourcesToGive.getCount() == 0 : "There are resources remaining that weren't distributed.";
    }

    private void giveResourcesToAdjacentTowns(IHex tile, IResourceBank resourcesToGive) {
        for (ITown town : getAdjacentTowns(tile.location())) {
            if (resourcesToGive.getCount(tile.resource()) > 0) {
                resourcesToGive.subtract(town.getResourceCount(), tile.resource());
                town.getOwner().addResources(town.getResourceCount(), tile.resource());
            }
        }
    }

    @Override
    public void distributeInitialResources(ITown town, IResourceBank gameResourceBank) {
        IResourceBank resources = new ResourceBank();
        for (IHex tile : getAdjacentTiles(town.getLocation())) {
            resources.add(1, tile.resource());
        }

        assert resources.getCount() <= 3;

        town.getOwner().addResources(resources);
        gameResourceBank.subtract(resources);
    }

}
