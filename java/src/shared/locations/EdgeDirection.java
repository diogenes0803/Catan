package shared.locations;

import java.io.Serializable;

public enum EdgeDirection implements Serializable {
	
	NorthWest, North, NorthEast, SouthEast, South, SouthWest;
	
	private EdgeDirection opposite;
    private EdgeDirection clockwise;
    private EdgeDirection counterclockwise;
    private String abbreviation;

	static {
		NorthWest.opposite = SouthEast;
		North.opposite = South;
		NorthEast.opposite = SouthWest;
		SouthEast.opposite = NorthWest;
		South.opposite = North;
		SouthWest.opposite = NorthEast;
        
     	NorthWest.clockwise = North;
		North.clockwise     = NorthEast;
		NorthEast.clockwise = SouthEast;
		SouthEast.clockwise = South;
		South.clockwise     = SouthWest;
		SouthWest.clockwise = NorthWest;

        NorthWest.counterclockwise = SouthWest;
        North.counterclockwise     = NorthWest;
        NorthEast.counterclockwise = North;
        SouthEast.counterclockwise = NorthEast;
        South.counterclockwise     = SouthEast;
        SouthWest.counterclockwise = South;

        NorthWest.abbreviation = "NW";
		North.abbreviation     = "N";
		NorthEast.abbreviation = "NE";
		SouthEast.abbreviation = "SE";
		South.abbreviation     = "S";
		SouthWest.abbreviation = "SW";
	}
	
	public EdgeDirection getOppositeDirection() {
		return opposite;
	}
    
    public EdgeDirection getClockwise() {
        return clockwise;
    }

    public EdgeDirection getCounterclockwise() {
        return counterclockwise;
    }

    public VertexDirection[] getNeighboringVertexDirections() {
        switch (this) {
            case NorthWest:
                return new VertexDirection[]{VertexDirection.West, VertexDirection.NorthWest};
            case North:
                return new VertexDirection[]{VertexDirection.NorthWest, VertexDirection.NorthEast};
            case NorthEast:
                return new VertexDirection[]{VertexDirection.NorthEast, VertexDirection.East};
            case SouthEast:
                return new VertexDirection[]{VertexDirection.East, VertexDirection.SouthEast};
            case South:
                return new VertexDirection[]{VertexDirection.SouthEast, VertexDirection.SouthWest};
            case SouthWest:
                return new VertexDirection[]{VertexDirection.SouthWest, VertexDirection.West};
            default:
                assert false;
                return null;
        }
    }


    public static EdgeDirection fromAbbreviation(String abbr) {
        switch (abbr.toUpperCase()) {
            case "NE": return NorthEast;
            case "SE": return SouthEast;
            case "S" : return South;
            case "SW": return SouthWest;
            case "NW": return NorthWest;
            case "N" : return North;
            default:   throw new IllegalArgumentException("Illegal EdgeDirection abbreviation \"" + abbr + "\"");
        }
    }


    public String toAbbreviation() {
        return this.abbreviation;
    }
}

