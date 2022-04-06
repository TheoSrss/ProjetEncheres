package fr.eni.encheres.bo;

import java.util.Date;

public class articleSold {
    private int id;
    private String name;
    private String description;
    private Date dateStartBid;
    private Date dateEndBid;
    private float initialPrice;
    private float soldPrice;
    private String StateSale;
    private User user;
    private Category category;
    private Withdrawal withdrawal;

    public articleSold(int id, String name, String description, Date dateStartBid, Date dateEndBid, float initialPrice, float soldPrice, String stateSale, User user, Category category, Withdrawal withdrawal) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateStartBid = dateStartBid;
        this.dateEndBid = dateEndBid;
        this.initialPrice = initialPrice;
        this.soldPrice = soldPrice;
        StateSale = stateSale;
        this.user = user;
        this.category = category;
        this.withdrawal = withdrawal;
    }


}
