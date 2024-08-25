package mbds.barter.data.response;

import java.util.List;
import mbds.barter.data.model.Objects;

public class ObjectsResponse {
    private List<Objects> data;

    public List<Objects> getData() {
        return data;
    }

    public void setData(List<Objects> data) {
        this.data = data;
    }
}
