package shared.models;

import shared.definitions.PortType;
import shared.locations.EdgeLocation;

public class Port {

    private int ratio;
    private PortType type;
    private EdgeLocation location;

    public Port(int ratio, PortType type, EdgeLocation location) {
        this.ratio = ratio;
        this.type = type;
        this.location = location;
    }

    public int getRatio() {
        return ratio;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }

    public PortType getType() {
        return type;
    }

    public void setType(PortType type) {
        this.type = type;
    }

    public EdgeLocation getLocation() {
        return location;
    }

    public void setLocation(EdgeLocation location) {
        this.location = location;
    }
}
