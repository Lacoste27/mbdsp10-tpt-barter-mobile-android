package mbds.barter.data.model;

import java.util.List;

public class Objects {
    private String name;
    private int categoryId;
    private String description;
    private List<String> image;
    private int ownerId;

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
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public Objects(String name, int categoryId, String description, List<String> image, int ownerId) {
        this.name = name;
        this.categoryId = categoryId;
        this.description = description;
        this.image = image;
        this.ownerId = ownerId;
    }


}
