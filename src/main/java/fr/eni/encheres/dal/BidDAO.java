package fr.eni.encheres.dal;



import fr.eni.encheres.bo.Bid;


public interface BidDAO {
    public Bid getLastBidForIdArticle(int id) throws DALException;
    public Bid setNewBid(Bid b) throws DALException;


}
