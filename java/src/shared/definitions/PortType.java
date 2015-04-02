package shared.definitions;

public enum PortType
{
	WOOD, BRICK, SHEEP, WHEAT, ORE, THREE;

    public static PortType fromResourceType(ResourceType type) {
        switch (type) {
            case WOOD:  return WOOD;
            case BRICK: return BRICK;
            case SHEEP: return SHEEP;
            case WHEAT: return WHEAT;
            case ORE:   return ORE;
            default:
                assert false : "Unknown resource type";
                return null;
        }
    }
}

