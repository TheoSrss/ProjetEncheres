package fr.eni.encheres.bo;

import java.util.Date;

public class bid {
    private User user;
    private articleSold articleSold;
    private Date date;
    private float amount;

    public bid(User user, fr.eni.encheres.bo.articleSold articleSold, Date date, float amount) {
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

    public fr.eni.encheres.bo.articleSold getArticleSold() {
        return articleSold;
    }

    public void setArticleSold(fr.eni.encheres.bo.articleSold articleSold) {
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
