package shared.locations;

import java.io.Serializable;


public class EdgeLocation implements Serializable
{
	
	private HexLocation hexLoc;
	private EdgeDirection dir;

	public EdgeLocation(int x, int y, EdgeDirection dir)
	{
		this.hexLoc = new HexLocation(x, y);
		this.dir = dir;
	}

	public EdgeLocation(HexLocation hexLoc, EdgeDirection dir)
	{
		setHexLoc(hexLoc);
		setDir(dir);
	}
	
	public HexLocation getHexLoc()
	{
		return hexLoc;
	}
	
	private void setHexLoc(HexLocation hexLoc)
	{
		if(hexLoc == null)
		{
			throw new IllegalArgumentException("hexLoc cannot be null");
		}
		this.hexLoc = hexLoc;
	}
	
	public EdgeDirection getDir()
	{
		return dir;
	}
	
	private void setDir(EdgeDirection dir)
	{
		this.dir = dir;
	}
	
	@Override
	public String toString()
	{
		return "EdgeLocation [hexLoc=" + hexLoc + ", dir=" + dir + "]";
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dir == null) ? 0 : dir.toString().hashCode());
		result = prime * result + ((hexLoc == null) ? 0 : hexLoc.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		EdgeLocation other = (EdgeLocation)obj;
		if(dir != other.dir)
			return false;
		if(hexLoc == null)
		{
			if(other.hexLoc != null)
				return false;
		}
		else if(!hexLoc.equals(other.hexLoc))
			return false;
		return true;
	}


	public EdgeLocation getNormalizedLocation()
	{
		
		// Return an EdgeLocation that has direction NW, N, or NE
		
		switch (dir)
		{
			case NorthWest:
			case North:
			case NorthEast:
				return this;
			case SouthWest:
			case South:
			case SouthEast:
				return new EdgeLocation(hexLoc.getNeighborLoc(dir),
										dir.getOppositeDirection());
			default:
				assert false;
				return null;
		}
	}


    public EdgeLocation getEquivalentEdge() {
        return new EdgeLocation(hexLoc.getNeighborLoc(dir), dir.getOppositeDirection());
    }


    public EdgeLocation getNormalizedClockwise() {
        return new EdgeLocation(hexLoc, dir.getClockwise()).getNormalizedLocation();
    }


    public EdgeLocation getNormalizedCounterClockwise() {
        return new EdgeLocation(hexLoc, dir.getCounterclockwise()).getNormalizedLocation();
    }


     public EdgeLocation[] getAdjacentEdges() {
        EdgeLocation other = getEquivalentEdge();

        return new EdgeLocation[] {
                getNormalizedClockwise(),
                getNormalizedCounterClockwise(),
                other.getNormalizedClockwise(),
                other.getNormalizedCounterClockwise(),
        };
    }


    public VertexLocation[] getAdjacentVertices() {
        VertexDirection[] vertexDirs = getDir().getNeighboringVertexDirections();
        return new VertexLocation[] {
                new VertexLocation(getHexLoc(), vertexDirs[0]).getNormalizedLocation(),
                new VertexLocation(getHexLoc(), vertexDirs[1]).getNormalizedLocation(),
        };
    }


    public static VertexLocation getVertexBetweenEdges(EdgeLocation edge1, EdgeLocation edge2) {
        assert edge1 != null && edge2 != null : "Called with null edge.";

        // check if the two edges share a vertex
        for (VertexLocation vertex1 : edge1.getAdjacentVertices()) {
            for (VertexLocation vertex2 : edge2.getAdjacentVertices()) {
                if (vertex1.equals(vertex2)) {
                    return vertex1;
                }
            }
        }

        return null;
    }
}

