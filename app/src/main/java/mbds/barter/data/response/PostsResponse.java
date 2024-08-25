package mbds.barter.data.response;

import java.util.List;

import mbds.barter.data.model.Objects;
import mbds.barter.data.model.QRCPost;

public class PostsResponse {
    List<QRCPost> data;

    public List<QRCPost> getData() {
        return data;
    }

    public void setData(List<QRCPost> data) {
        this.data = data;
    }
}
