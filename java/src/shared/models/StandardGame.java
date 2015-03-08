package shared.models;

/**
 * Constants for the game
 */
public class StandardGame {
    /** The number of cards that can be held without needing to discard */
    public static final int MAX_SAFE_RESOURCES = 7;

    /** How many players there are in a game */
    public static final int NUM_PLAYERS = 4;

    /** The number of victory points needed to win */
    public static final int VICTORY_POINTS_TO_WIN = 10;

    /** The minimum number of roads needed to qualify for the longest road */
    public static final int MIN_ROADS_FOR_LONGEST_ROAD = 5;
    public static final int POINTS_FOR_LONGEST_ROAD = 2;

    /** The minimum number of soldiers needed to qualify for the largest army */
    public static final int MIN_SOLDIERS_FOR_LARGEST_ARMY = 3;
    public static final int POINTS_FOR_LARGEST_ARMY = 2;

    /** Number of roads a player starts with, and the max that they can place on the board */
    public static final int MAX_ROADS = 15;

    /** Number of settlements a player starts with, and the max that they can place on the board */
    public static final int MAX_SETTLEMENTS = 5;

    /** Number of cities a player starts with, and the max that they can place on the board */
    public static final int MAX_CITIES = 4;

    /** Initial resource amounts */
    public static final int TOTAL_CARDS_PER_RESOURCE = 19;

    /** Initial development card amounts */
    public static final int TOTAL_MONOPOLY_CARDS       = 2;
    public static final int TOTAL_MONUMENT_CARDS       = 5;
    public static final int TOTAL_ROAD_BUILDING_CARDS  = 2;
    public static final int TOTAL_SOLDIER_CARDS        = 14;
    public static final int TOTAL_YEAR_OF_PLENTY_CARDS = 2;

    /** How many land tiles are on the map */
    public static final int NUM_LAND_TILES = 19;
}

