package model;

public class Transactions {
    private int id;
    private int userId;
    private String userName;
    private int gameId;
    private String gameName;
    private int totalPrice;

    public Transactions(int id, int userId, String userName, int gameId, String gameName, int totalPrice) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.gameId = gameId;
        this.gameName = gameName;
        this.totalPrice = totalPrice;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }


}
