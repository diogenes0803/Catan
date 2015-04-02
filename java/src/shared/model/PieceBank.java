package shared.model;


public class PieceBank implements IPieceBank {

    private int m_numRoads;
    private int m_numSettlements;
    private int m_numCities;


    public PieceBank() {
        m_numRoads = 0;
        m_numSettlements = 0;
        m_numCities = 0;
    }


    public PieceBank(int numRoads, int numSettlements, int numCities) throws ModelException {
        setAvailableRoads(numRoads);
        setAvailableSettlements(numSettlements);
        setAvailableCities(numCities);
    }



    @Override
    public void takeRoad() {
        m_numRoads--;
    }


    @Override
    public void setAvailableRoads(int availableRoads) throws ModelException {
        if (availableRoads < 0) {
            throw new ModelException("Attempted to set a negative number of roads (" + availableRoads + ").");
        }
        m_numRoads = availableRoads;
    }


    @Override
    public int availableRoads() {
        return m_numRoads;
    }


    @Override
    public void swapSettlementForCity() {
        m_numCities--;
        m_numSettlements++;
    }


    @Override
    public void setAvailableCities(int availableCities) throws ModelException {
        if (availableCities < 0) {
            throw new ModelException("Attempted to set a negative number of cities.");
        }
        m_numCities = availableCities;
    }


    @Override
    public int availableCities() {
        return m_numCities;
    }


    @Override
    public void takeSettlement() {
        m_numSettlements--;
    }


    @Override
    public void setAvailableSettlements(int availableSettlements) throws ModelException {
        if (availableSettlements < 0) {
            throw new ModelException("Attempted to set a negative number of settlements.");
        }
        m_numSettlements = availableSettlements;
    }


    @Override
    public int availableSettlements() {
        return m_numSettlements;
    }

    public static PieceBank generateInitial() {
        try {
            return new PieceBank(
                    CatanConstants.MAX_ROADS,
                    CatanConstants.MAX_SETTLEMENTS,
                    CatanConstants.MAX_CITIES
            );
        } catch (ModelException e) {
            assert false : "CatanConstants are messed up!";
            e.printStackTrace();
            return null;
        }
    }
}
