package fr.eni.encheres.bo;

import java.util.Date;

public class Article {
    private int id;
    private String name;
    private String description;
    private Date dateStartBid;
    private Date dateEndBid;
    private float initialPrice;
    private float soldPrice;
    private String stateSale;
    private User user;
    private Category category;
    private Withdrawal withdrawal;


    public Article( String name, String description, Date dateStartBid, Date dateEndBid, float initialPrice, float soldPrice, String stateSale, User user, Category category, Withdrawal withdrawal) {
//        this.id = id;
        this.name = name;
        this.description = description;
        this.dateStartBid = dateStartBid;
        this.dateEndBid = dateEndBid;
        this.initialPrice = initialPrice;
        this.soldPrice = soldPrice;
        this.stateSale = stateSale;
        this.user = user;
        this.category = category;
        this.withdrawal = withdrawal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateStartBid() {
        return dateStartBid;
    }

    public void setDateStartBid(Date dateStartBid) {
        this.dateStartBid = dateStartBid;
    }

    public Date getDateEndBid() {
        return dateEndBid;
    }

    public void setDateEndBid(Date dateEndBid) {
        this.dateEndBid = dateEndBid;
    }

    public float getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(float initialPrice) {
        this.initialPrice = initialPrice;
    }

    public float getSoldPrice() {
        return soldPrice;
    }

    public void setSoldPrice(float soldPrice) {
        this.soldPrice = soldPrice;
    }

    public String getStateSale() {
        return stateSale;
    }

    public void setStateSale(String stateSale) {
        this.stateSale = stateSale;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Withdrawal getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(Withdrawal withdrawal) {
        this.withdrawal = withdrawal;
    }
}
