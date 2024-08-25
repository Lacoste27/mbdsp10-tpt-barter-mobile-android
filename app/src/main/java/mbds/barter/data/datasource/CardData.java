package mbds.barter.data.datasource;

public class CardData {
    private String userName;
    private String itemList;
    private String timeStamp;
    private String viewsCount;
    // Ajoute d'autres champs comme les tags, les images, etc.

    // Constructeur, getters, et setters
    public CardData(String userName, String itemList, String timeStamp, String viewsCount) {
        this.userName = userName;
        this.itemList = itemList;
        this.timeStamp = timeStamp;
        this.viewsCount = viewsCount;
    }

    public String getUserName() {
        return userName;
    }

    public String getItemList() {
        return itemList;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getViewsCount() {
        return viewsCount;
    }

    // Ajoute les autres getters et setters
}
