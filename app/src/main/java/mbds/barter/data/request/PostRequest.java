package mbds.barter.data.request;

import java.util.List;

public class PostRequest {
    private int authorId;
    private String description ;
    private List<Integer> objectIds;

    public PostRequest(int authorId, String description, List<Integer> objectIds) {
        this.authorId = authorId;
        this.description = description;
        this.objectIds = objectIds;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getObjectIds() {
        return objectIds;
    }

    public void setObjectIds(List<Integer> objectIds) {
        this.objectIds = objectIds;
    }

}
