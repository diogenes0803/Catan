package shared.locations;

import java.io.Serializable;

public enum VertexDirection implements Serializable
{
	West, NorthWest, NorthEast, East, SouthEast, SouthWest;
	
	private VertexDirection opposite;
    private String abbreviation;

	static
	{
		West.opposite = East;
		NorthWest.opposite = SouthEast;
		NorthEast.opposite = SouthWest;
		East.opposite = West;
		SouthEast.opposite = NorthWest;
		SouthWest.opposite = NorthEast;

        West.abbreviation      = "W";
		NorthWest.abbreviation = "NW";
		NorthEast.abbreviation = "NE";
		East.abbreviation      = "E";
		SouthEast.abbreviation = "SE";
		SouthWest.abbreviation = "SW";
	}
	
	public VertexDirection getOppositeDirection()
	{
		return opposite;
	}


    public static VertexDirection fromAbbreviation(String abbr) {
        switch (abbr.toUpperCase()) {
            case "NE": return NorthEast;
            case "E" : return East;
            case "SE": return SouthEast;
            case "SW": return SouthWest;
            case "W" : return West;
            case "NW": return NorthWest;
            default:
                throw new IllegalArgumentException("Illegal VertexDirection abbreviation \"" + abbr + "\"");
        }
    }


    public String toAbbreviation() {
        return this.abbreviation;
    }

    public EdgeDirection[] getNeighboringEdgeDirections() {
        switch (this) {
            case NorthEast:
                return new EdgeDirection[]{EdgeDirection.North, EdgeDirection.NorthEast};
            case East:
                return new EdgeDirection[]{EdgeDirection.NorthEast, EdgeDirection.SouthEast};
            case SouthEast:
                return new EdgeDirection[]{EdgeDirection.SouthEast, EdgeDirection.South};
            case SouthWest:
                return new EdgeDirection[]{EdgeDirection.South, EdgeDirection.SouthWest};
            case West:
                return new EdgeDirection[]{EdgeDirection.SouthWest, EdgeDirection.NorthWest};
            case NorthWest:
                return new EdgeDirection[]{EdgeDirection.NorthWest, EdgeDirection.North};
            default:
                assert false;
                return null;
        }
    }
}

