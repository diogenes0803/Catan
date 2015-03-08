package shared.models;

import shared.definitions.ResourceType;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

/**
 * Created by Kent Coble
 */
public class ResourceBank implements IResourceBank {
    static final long serialVersionUID = 7157637162661465358l;

    public static final int NUM_RESOURCES = 5;

    private int wood = 0;
    private int brick = 0;
    private int sheep = 0;
    private int wheat = 0;
    private int ore = 0;

    public ResourceBank() {
        setWood(0);
        setBrick(0);
        setSheep(0);
        setWheat(0);
        setOre(0);
    }

    public ResourceBank(Bank bank){
        List<ResCard> cards = bank.getResCards();
        for (ResCard card : cards)
            increment(card.getType());


    }

    public ResourceBank(List<ResCard> rs){
        for (ResCard card : rs)
            increment(card.getType());
    }

    public ResourceBank(int wood, int brick, int sheep, int wheat, int ore) {
        setWood(wood);
        setBrick(brick);
        setSheep(sheep);
        setWheat(wheat);
        setOre(ore);
    }

    public static ResourceBank generateInitial() {
        return new ResourceBank(
                StandardGame.TOTAL_CARDS_PER_RESOURCE,
                StandardGame.TOTAL_CARDS_PER_RESOURCE,
                StandardGame.TOTAL_CARDS_PER_RESOURCE,
                StandardGame.TOTAL_CARDS_PER_RESOURCE,
                StandardGame.TOTAL_CARDS_PER_RESOURCE
        );
    }

    @Override
    public int getWood() {
        return wood;
    }

    public void setWood(int wood) {
        this.wood = wood;
    }

    @Override
    public int getBrick() {
        return brick;
    }

    public void setBrick(int brick) {
        this.brick = brick;
    }

    @Override
    public int getSheep() {
        return sheep;
    }

    public void setSheep(int sheep) {
        this.sheep = sheep;
    }

    @Override
    public int getWheat() {
        return wheat;
    }

    public void setWheat(int wheat) {
        this.wheat = wheat;
    }

    @Override
    public int getOre() {
        return ore;
    }

    public void setOre(int ore) {
        this.ore = ore;
    }

    @Override
    public int getCount(ResourceType resourceType) {
        if (resourceType == null) { // water & desert have null resourceType
            return 0;
        }
        switch (resourceType) {
            case WOOD:  return wood;
            case BRICK: return brick;
            case SHEEP: return sheep;
            case WHEAT: return wheat;
            case ORE:   return ore;
            default:
                assert false : "Unknown resource type.";
                return -1;
        }
    }

    @Override
    public void setCount(ResourceType resourceType, int amount) {
        switch (resourceType) {
            case WOOD:  wood = amount; break;
            case BRICK: brick = amount; break;
            case SHEEP: sheep = amount; break;
            case WHEAT: wheat = amount; break;
            case ORE:   ore = amount; break;
        }
    }

    /**
     * Get the total amount of resources a player has.
     *
     * @return the amount of resources
     */
    @Override
    public int getCount() {
        return wood + brick + sheep + wheat + ore;
    }

    @Override
    public void add(IResourceBank resources) {
        setWood(wood + resources.getWood());
        setBrick(brick + resources.getBrick());
        setSheep(sheep + resources.getSheep());
        setWheat(wheat + resources.getWheat());
        setOre(ore + resources.getOre());
    }

    @Override
    public void add(int amount, ResourceType rType) {
        if (rType != null) { // water and desert have a null resource type
            setCount(rType, getCount(rType) + amount);
        }
    }


    @Override
    public void subtract(IResourceBank resources) {
        IResourceBank bundle = new ResourceBank(resources.getWood(), resources.getBrick(), resources.getSheep(),
                resources.getWheat(), resources.getOre());

        assert wood >= resources.getWood() && brick >= resources.getBrick() && sheep >= resources.getSheep()
                && wheat >= resources.getWheat() && ore >= resources.getOre() : "Trying to subtract more than is in the bank!";

        setWood(wood - resources.getWood());
        setBrick(brick - resources.getBrick());
        setSheep(sheep - resources.getSheep());
        setWheat(wheat - resources.getWheat());
        setOre(ore - resources.getOre());
    }

    @Override
    public void subtract(int amount, ResourceType rType) {
        assert amount <= getCount(rType) : "Tried to subtract " + amount + " " + rType + " resources but only " + getCount(rType) + " are available";
        int netAmount = getCount(rType) - amount;
        setCount(rType, netAmount);
    }

    /**
     * Add one of the specified resource to the resource bank.
     *
     * @param resource the type of resource to increment
     */
    @Override
    public void increment(ResourceType resource) {
        switch (resource) {
            case WOOD:  wood++; break;
            case BRICK: brick++; break;
            case SHEEP: sheep++; break;
            case WHEAT: wheat++; break;
            case ORE:   ore++; break;
        }
    }

    /**
     * Subtract one of the specified resource from the resource bank.
     *
     * @param resource the bundle of resources to remove from the bank
     */
    @Override
    public void decrement(ResourceType resource) {
        switch (resource) {
            case WOOD:  wood--; break;
            case BRICK: brick--; break;
            case SHEEP: sheep--; break;
            case WHEAT: wheat--; break;
            case ORE:   ore--; break;
        }
    }

    @Override
    public boolean canAfford(IResourceBank purchase) {
        if (wood >= purchase.getWood() && brick >= purchase.getBrick() && sheep >= purchase.getSheep()
                && wheat >= purchase.getWheat() && ore >= purchase.getOre()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean canAfford(int amount, ResourceType rType) {
        return getCount(rType) >= amount;
    }

    @Override
    public boolean containsResources() {
        for (ResourceType type : ResourceType.values()) {
            if (getCount(type) > 0) {
                return true;
            }
        }

        return false;
    }

    @Override
    public IResourceBank negate() {
        return new ResourceBank(
                -wood,
                -brick,
                -sheep,
                -wheat,
                -ore
        );
    }

    /**
     * Draws a random card from the ResourceBank
     *
     * @return the type of resource card that was drawn
     * @param rand
     */
    @Override
    public ResourceType drawRandom(Random rand) {
        assert getCount() > 0 : "Tried to draw from an empty resource bank!";

        ResourceType drawnCard = null;

        int randomNumber = rand.nextInt(getCount());

        if (randomNumber < brick) {
            drawnCard = ResourceType.BRICK;
        } else if (randomNumber < brick + wood) {
            drawnCard = ResourceType.WOOD;
        } else if (randomNumber < brick + wood + ore) {
            drawnCard = ResourceType.ORE;
        } else if (randomNumber < brick + wood + ore + sheep) {
            drawnCard = ResourceType.SHEEP;
        } else { //if (randomNumber < brick + wood + ore + sheep + wheat) {
            drawnCard = ResourceType.WHEAT;
        }

        assert getCount(drawnCard) > 0 : "Tried to draw " + drawnCard + " but there are " + getCount(drawnCard) + " of them!";
        this.subtract(1, drawnCard);

        return drawnCard;
    }

    @Override
    public boolean isValidTradeOffer() {
        boolean giving = (wood > 0 || brick > 0 || sheep > 0 || wheat > 0 || ore > 0);

        return giving && (wood < 0 || brick < 0 || sheep < 0 || wheat < 0 || ore < 0);
    }

    public ResourceList toResourceList(){
        return new ResourceList(brick, ore, wheat, sheep, wood);
    }

    @Override
    public String toString() {
        return "ResourceBank{" +
                "wood=" + wood +
                ", brick=" + brick +
                ", sheep=" + sheep +
                ", wheat=" + wheat +
                ", ore=" + ore +
                '}';
    }
}
