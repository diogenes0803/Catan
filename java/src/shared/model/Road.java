package shared.model;

import shared.locations.EdgeLocation;


public class Road implements IRoad {

    private IPlayer m_owner;
    private EdgeLocation m_location;


    public Road(IPlayer owner) {
        assert owner != null;

        m_owner = owner;
        setLocation(null);
    }


    public Road(IPlayer owner, EdgeLocation location) {
        assert owner != null && location != null;

        m_owner = owner;
        setLocation(location);
    }


    public IPlayer getOwner() {
        return m_owner;
    }


    @Override
    public EdgeLocation getLocation() {
        return m_location;
    }


    @Override
    public void setLocation(EdgeLocation edge) {
        m_location = (edge != null ? edge.getNormalizedLocation() : null);
    }
}
