package shared.model;

import shared.definitions.HexType;
import shared.definitions.PortType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

import java.util.*;
import java.util.logging.Logger;


public class MapGenerator {
    private static Logger logger = Logger.getLogger("catanserver");

    private boolean randomPorts;
    private boolean randomTiles;
    private boolean randomNumbers;

    private Map<HexLocation, IHex> tiles;
    private Map<EdgeLocation, PortType> ports;
    HexLocation robber;

    private List<Integer> numberIndices;
    private List<Integer> tileIndices;
    private List<Integer> portIndices;


    private final static HexType[] DEFAULT_HEX_TYPES = {
            // radius = 2
            HexType.ORE,
            HexType.WHEAT,
            HexType.WOOD,
            HexType.ORE,
            HexType.WHEAT,
            HexType.SHEEP,
            HexType.WHEAT,
            HexType.SHEEP,
            HexType.WOOD,
            HexType.BRICK,
            HexType.DESERT,
            HexType.BRICK,
            // radius = 1
            HexType.SHEEP,
            HexType.SHEEP,
            HexType.WOOD,
            HexType.BRICK,
            HexType.ORE,
            HexType.WOOD,
            // center tile
            HexType.WHEAT,
    };

    private final static EdgeDirection[] PORT_DIRECTIONS = {
            EdgeDirection.SouthEast,
            EdgeDirection.NorthEast,
            EdgeDirection.NorthEast,
            EdgeDirection.North,
            EdgeDirection.NorthWest,
            EdgeDirection.NorthWest,
            EdgeDirection.SouthWest,
            EdgeDirection.South,
            EdgeDirection.South,
    };

    private final static PortType[] DEFAULT_PORTS = {
            PortType.THREE,
            PortType.WOOD,
            PortType.BRICK,
            PortType.THREE,
            PortType.THREE,
            PortType.SHEEP,
            PortType.THREE,
            PortType.ORE,
            PortType.WHEAT,
    };


    private final static Integer[] DEFAULT_NUMBERS = {
            5,
            2,
            6,
            3,
            8,
            10,
            9,
            12,
            11,
            4,
            8,
            10,
            9,
            //Hex.DESERT_NUMBER, // desert hex: EXCLUDED since whichever hex is desert gets this #
            4,
            5,
            6,
            3,
            11,
    };

    private final List<HexLocation> HEX_LOCATIONS;

    private void addHex(int x, int y) {
        logger.finest(String.format("HexLoc (%d, %d)", x, y));
        HEX_LOCATIONS.add(new HexLocation(x, y));
    }

    public MapGenerator(boolean randomPorts, boolean randomTiles, boolean randomNumbers) {
        this.randomPorts = randomPorts;
        this.randomTiles = randomTiles;
        this.randomNumbers = randomNumbers;

        numberIndices = (randomNumbers ? generateIndexList(DEFAULT_NUMBERS) : null);
        tileIndices = (randomTiles ? generateIndexList(DEFAULT_HEX_TYPES) : null);
        portIndices = (randomPorts ? generateIndexList(DEFAULT_PORTS) : null);

        tiles = new HashMap<>();
        ports = new HashMap<>();
        robber = null;

        HEX_LOCATIONS = new ArrayList<>();
        generateHexesSpiral();

        logger.finest(String.format("%d tiles\n", HEX_LOCATIONS.size()));
        for (HexLocation hex : HEX_LOCATIONS) {
            logger.finest(String.format("(%d, %d)\n", hex.getX(), hex.getY()));
        }
    }


    private void generateHexesSpiral() {
        final int RADIUS = 4;

        int max = RADIUS-1;
        int min = -max;

        for (int r = RADIUS-1; r >= 1; --r) {
            int x = min;
            int y = 0;

            for (; y < max; ++y) {
                addHex(x, y);
            }
            addHex(x, y);
            for (++x; x < 0; ++x) {
                addHex(x, y);
            }
            addHex(x, y);
            for (++x, --y; x < max; ++x, --y) {
                addHex(x, y);
            }
            addHex(x, y);
            for (--y; y > min; --y) {
                addHex(x, y);
            }
            addHex(x, y);
            for (--x; x > 0; --x) {
                addHex(x, y);
            }
            addHex(x, y);
            for (--x, ++y; x > min; --x, ++y) {
                addHex(x, y);
            }

            max--;
            min++;
        }

        addHex(0, 0);
    }

    public ICatanMap generateMap() throws ModelException {
        // place ports
        for (int i = 0; i < DEFAULT_PORTS.length; ++i) {
            placePort(HEX_LOCATIONS.get(2 * i), i); // place a port on every other water hex
        }

        Int tileCount = new Int(0);
        Int numberCount = new Int(0);

        for (int i = HEX_LOCATIONS.size() - CatanConstants.NUM_LAND_TILES; i < HEX_LOCATIONS.size(); ++i) {
            placeTile(HEX_LOCATIONS.get(i), tileCount, numberCount);
        }

        return new CatanMap(tiles, new HashMap<VertexLocation, ITown>(), new HashMap<EdgeLocation, IRoad>(), ports, robber);
    }

    private void placePort(HexLocation hexLocation, int index) {
        PortType type = (randomPorts ? getRandom(DEFAULT_PORTS, portIndices) : DEFAULT_PORTS[index]);
        EdgeLocation location = new EdgeLocation(hexLocation, PORT_DIRECTIONS[index]);

        ports.put(location, type);
    }


    private void placeTile(HexLocation hexLoc, Int tileCount, Int numberCount) {
        HexType type = (randomTiles ? getRandom(DEFAULT_HEX_TYPES, tileIndices)     : DEFAULT_HEX_TYPES[tileCount.postincrement()]);

        int number;
        if (type == HexType.DESERT) {
            number = Hex.DESERT_NUMBER;
        }
        else {
            number = (randomNumbers ? getRandom(DEFAULT_NUMBERS, numberIndices) : DEFAULT_NUMBERS[numberCount.postincrement()]);
        }

        IHex newTile = Hex.generateNewTile(type, hexLoc, number);

        // check for the robber's placement
        if (newTile.hasRobber()) {
            assert robber == null : "There appear to be multiple desert tiles!";
            robber = newTile.location();
        }

        tiles.put(hexLoc, newTile);
    }

    private static <T> List<Integer> generateIndexList(T[] sourceArray) {
        List<Integer> indexList = new ArrayList<>(sourceArray.length);
        for (int i = 0; i < sourceArray.length; ++i) {
            indexList.add(i);
        }
        return indexList;
    }

    private static <T> T getRandom(T[] source, List<Integer> remainingIndices) {
        final Random rand = new Random();

        assert remainingIndices.size() > 0 : "No indices left to choose from!";

        int index = rand.nextInt(remainingIndices.size());
        int sourceIndex = remainingIndices.get(index);

        assert sourceIndex < source.length : "Invalid random index found!";

        T value = source[sourceIndex];

        removeItem(remainingIndices, index);

        return value;
    }


    private static <T> void removeItem(List<T> items, int index) {
        items.set(index, items.get(items.size()-1));
        items.remove(items.size()-1);
    }


    private class Int {
        public int value;

        public int preincrement() {
            return ++value;
        }

        public int postincrement() {
            return value++;
        }

        public Int(int value) {
            this.value = value;
        }

        public Int() {
            this.value = 0;
        }
    }
}
