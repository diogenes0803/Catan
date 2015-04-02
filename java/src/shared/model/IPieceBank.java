package shared.model;

import java.io.Serializable;


public interface IPieceBank extends Serializable {

    public void takeRoad();


    public void setAvailableRoads(int availableRoads) throws ModelException;


    public int availableRoads();


    public void swapSettlementForCity();


    public void setAvailableCities(int availableCities) throws ModelException;


    public int availableCities();


    public void takeSettlement();


    public void setAvailableSettlements(int availableSettlements) throws ModelException;


    public int availableSettlements();
}