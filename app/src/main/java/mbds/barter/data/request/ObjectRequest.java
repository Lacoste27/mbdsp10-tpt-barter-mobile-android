package mbds.barter.data.request;

import java.util.List;

public class ObjectRequest {
    private String name;
    private int categoryId;
    private String description;
    private List<String> image;
    private int OwnerId;

    public ObjectRequest() {

    }
    public ObjectRequest(String name, int categoryId, String description, List<String> image, int ownerId) {
        this.name = name;
        this.categoryId = categoryId;
        this.description = description;
        this.image = image;
        OwnerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public int getOwnerId() {
        return OwnerId;
    }

    public void setOwnerId(int ownerId) {
        OwnerId = ownerId;
    }
}
