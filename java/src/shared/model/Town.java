package shared.model;

import shared.locations.VertexLocation;


public abstract class Town implements ITown {
    private IPlayer m_owner;
    private VertexLocation m_location;


    protected Town(IPlayer owner, VertexLocation location) {
        assert owner != null;

        m_owner = owner;
        setLocation(location);
    }


    @Override
    public IPlayer getOwner() {
        return m_owner;
    }


    @Override
    public VertexLocation getLocation() {
        return m_location;
    }


    @Override
    public void setLocation(VertexLocation location) {
        m_location = (location != null ? location.getNormalizedLocation() : null);
    }
}
