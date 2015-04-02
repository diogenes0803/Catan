package shared.model;

import shared.definitions.ResourceType;

import java.io.Serializable;
import java.util.Random;


public interface IResourceBank extends Serializable {

    public void add(IResourceBank resources);


    public void add(int amount, ResourceType rType);


    public void subtract(IResourceBank resources);


    public void subtract(int amount, ResourceType rType);


    public void increment(ResourceType resource);


    public void decrement(ResourceType resource);


    public boolean canAfford(IResourceBank purchase);


    public boolean canAfford(int amount, ResourceType rType);


    public int getWood();


    public void setWood(int wood);


    public int getBrick();


    public void setBrick(int brick);


    public int getSheep();


    public void setSheep(int sheep);


    public int getWheat();


    public void setWheat(int wheat);


    public int getOre();


    public void setOre(int ore);


    public int getCount(ResourceType resourceType);


    public void setCount(ResourceType resourceType, int amount);


    public int getCount();


    public boolean containsResources();


    public IResourceBank negate();


    public ResourceType drawRandom(Random rand);


    public boolean isValidTradeOffer();

}
