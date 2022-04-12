package fr.eni.encheres.bo;

import java.util.Date;

public class Bid {
    private int id;
    private User user;
    private Article articleSold;
    private Date date;
    private float amount;

    public Bid(User user, Article articleSold, Date date, float amount) {
        this.user = user;
        this.articleSold = articleSold;
        this.date = date;
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticleSold() {
        return articleSold;
    }

    public void setArticleSold(Article articleSold) {
        this.articleSold = articleSold;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
