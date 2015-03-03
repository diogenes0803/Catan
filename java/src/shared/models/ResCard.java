package shared.models;

import shared.definitions.ResourceType;

/**
 * Model for Resource Cards
 *
 * @author HojuneYoo
 */
public class ResCard {

    private ResourceType type;

    public ResCard() {
    }

    public ResCard(ResourceType type) {
        this.type = type;
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

}
