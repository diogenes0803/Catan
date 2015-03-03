package shared.models.jsonholder;

import java.util.List;

public class Chat {
    private List<Line> Lines;

    public List<Line> getLines() {
        return Lines;
    }

    public void setLines(List<Line> lines) {
        Lines = lines;
    }
}
