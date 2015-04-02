package shared.model;

import shared.definitions.HexType;
import shared.definitions.ResourceType;
import shared.locations.HexLocation;


public class Hex implements IHex {
    private HexType m_type;
    private HexLocation m_location;
    private int m_number;
    private boolean m_robber = false;


    public static final int DESERT_NUMBER = 0;

    public Hex(HexType type, HexLocation location, int number) {
      assert type != null;
      assert location != null;
      assert !(type == HexType.DESERT && number != DESERT_NUMBER)
          : "Cannot create Desert hex with a number other than DESERT_NUMBER";
      assert (2 <= number && number <= 12 && number != 7)
              || (number == DESERT_NUMBER && type == HexType.DESERT) : "Invalid number: " + number;

      m_type = type;
      m_location = location;
      m_number = number;
    }


    public static Hex generateNewTile(HexType type, HexLocation location, int number) {
        Hex newTile = new Hex(type, location, number);

        if (newTile.type() == HexType.DESERT) {
            newTile.placeRobber();
        }

        return newTile;
    }


    @Override
    public HexType type() {
      return m_type;
    }


    @Override
    public HexLocation location() {
      return m_location;
    }


    @Override
    public ResourceType resource() {
      return m_type.getResource();
    }


    @Override
    public int numberToken() {
      return m_number;
    }


    @Override
    public boolean hasRobber() {
      return m_robber;
    }


    @Override
    public void placeRobber() {
      assert !m_robber : "Attempted to place the robber on a tile that already has the robber.";

      m_robber = true;
    }


    @Override
    public void removeRobber() {
      assert m_robber : "Attempted to remove the robber from a tile with no robber.";

      m_robber = false;
    }
}
