package shared.definitions;

public enum HexType {
	
	WOOD, BRICK, SHEEP, WHEAT, ORE, DESERT, WATER;

  private ResourceType resource;

  static {
    WOOD.resource  = ResourceType.WOOD;
    BRICK.resource = ResourceType.BRICK;
    SHEEP.resource = ResourceType.SHEEP;
    WHEAT.resource = ResourceType.WHEAT;
    ORE.resource   = ResourceType.ORE;
    
    DESERT.resource = null;
    WATER.resource  = null;
  }
    

  public ResourceType getResource() {
    return resource;
  }
}
