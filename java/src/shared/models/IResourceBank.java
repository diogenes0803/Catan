package shared.models;

import shared.definitions.ResourceType;

import java.io.Serializable;
import java.util.Random;

/**
 * A bank of resources. Resources can be added and subtracted as ResourceBanks.
 * This can represent, e.g., the resources a player is holding or the game's resource bank.
 * @author Kent
 */
public interface IResourceBank extends Serializable {
    /**
     * Add the specified resources to the resource bank.
     * @param resources the bundle of resources to add to the bank
     */
    public void add(IResourceBank resources);

    /**
     * Add the specified resources to the resource bank.
     * @param amount the number of a resource to add to the bank
     * @param rType the type of resource to add
     */
    public void add(int amount, ResourceType rType);

    /**
     * Subtract the specified resources from the resource bank.
     * If the number of resources being subtracted exceeds the number of
     * resources in the resource bank, an exception is thrown.
     * @param resources the bundle of resources to remove from the bank
     */
    public void subtract(IResourceBank resources);

    /**
     * Subtract the specified resources from the resource bank.
     * If the number of resources being subtracted exceeds the number of
     * resources in the resource bank, an exception is thrown.
     * @param amount the number to subtract
     * @param rType the type of resource to subtract
     */
    public void subtract(int amount, ResourceType rType);

    /**
     * Add one of the specified resource to the resource bank.
     * @param resource the type of resource to increment
     */
    public void increment(ResourceType resource);

    /**
     * Subtract one of the specified resource from the resource bank.
     * @param resource the type of resource to remove from the bank
     */
    public void decrement(ResourceType resource);

    /**
     * Checks if the resource bank has enough resources for the purchase
     * @param purchase the resource bank with the collection of resources to subtract
     * @return whether the resource bank has enough resources for the purchase
     */
    public boolean canAfford(IResourceBank purchase);

    /**
     * Checks if the resource bank has enough resources for the purchase
     * @param amount the number of resources to check that the player has
     * @param rType the type of resource to check the amount of
     * @return whether the resource bank has enough resources for the purchase
     */
    public boolean canAfford(int amount, ResourceType rType);

    /**
     * Get the amount of wood in the resource collection.
     * @return the amount of wood in the resource collection
     */
    public int getWood();

    /**
     * Set the amount of wood in the resource collection.
     * @param wood
     */
    public void setWood(int wood);

    /**
     * Get the amount of brick in the resource collection.
     * @return the amount of brick in the resource collection
     */
    public int getBrick();

    /**
     * Set the amount of brick in the resource collection.
     * @param brick
     */
    public void setBrick(int brick);

    /**
     * Get the amount of sheep in the resource collection.
     * @return the amount of sheep in the resource collection
     */
    public int getSheep();

    /**
     * Set the amount of sheep in the resource collection.
     * @param sheep
     */
    public void setSheep(int sheep);

    /**
     * Get the amount of wheat in the resource collection.
     * @return the amount of wheat in the resource collection
     */
    public int getWheat();

    /**
     * Set the amount of wheat in the resource collection.
     * @param wheat
     */
    public void setWheat(int wheat);

    /**
     * Get the amount of ore in the resource collection.
     * @return the amount of ore in the resource collection
     */
    public int getOre();

    /**
     * Set the amount of ore in the resource collection.
     * @param ore
     */
    public void setOre(int ore);

    /**
     * Get the amount of the specified type of resource.
     * @param resourceType the type of resource for which to get the amount
     * @return the amount of the specified resource
     */
    public int getCount(ResourceType resourceType);

    /**
     * Set the count of the specified resource type.
     * @param resourceType
     * @param amount
     */
    public void setCount(ResourceType resourceType, int amount);

    /**
     * Get the amount of resources a player has.
     * @return the amount of resources
     */
    public int getCount();

    /**
     * Get whether the resource bank contains any resources, i.e., if any of the resource counts
     * are greater than 0.
     * @return true if the bank contains resources
     */
    public boolean containsResources();

    /**
     * Negate all of the values in the bank.
     * This turns trade offers into trade requests and requests into offers.
     * @return a new, negated bank
     */
    public IResourceBank negate();

    /**
     * Draws a random card from the ResourceBank
     *
     * @return the type of resource card that was drawn
     * @param rand the random number generator to use
     */
    public ResourceType drawRandom(Random rand);

    /**
     * Check if a resource bank includes both positive and negative values for resources.
     * This checks if a trade offer is valid (trader both sends and receives -- no giveaways)
     * @return true if at least one resource is positive and at least one is negative
     */
    public boolean isValidTradeOffer();

}
