package fr.eni.encheres.dal;



import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Bid;
import fr.eni.encheres.bo.User;


public interface BidDAO {
    public Bid getLastBidForIdArticle(int id) throws DALException;
    public Bid setNewBid(Bid b) throws DALException;
    public Bid getBidForUserAndArticle(User u, Article a) throws DALException;

}
