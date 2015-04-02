package shared.model;

import shared.definitions.DevCardType;

import java.io.Serializable;
import java.util.Random;


public interface IDevCardHand extends Serializable {

    public int getCount();


    public int getCount(DevCardType devCardType);


    public IDevCardHand sum(IDevCardHand other);


    public void add(DevCardType devCardType);


    public void remove(DevCardType devCardType);


    public void transferAllCardsToHand(IDevCardHand devCardHand);


    public DevCardType drawCard(Random rand);
}
