package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Bid;
import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.BidDAO;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;

public class BidManager {
    private static BidManager bidManager;
    private static BidDAO bidDAO;

    private BidManager() {
        bidDAO = DAOFactory.getBidDao();
    }

    public static BidManager getInstance() {
        if (bidManager == null) {
            bidManager = new BidManager();
        }
        return bidManager;
    }

    public Bid getLastBidForIdArticle(int id) throws DALException {
        return bidDAO.getLastBidForIdArticle(id);
    }

    public Bid setNewBid(Bid b) throws DALException {
        return bidDAO.setNewBid(b);
    }

    public Bid getBidForUserAndArticle(User u, Article a) throws DALException {
        return bidDAO.getBidForUserAndArticle(u, a);
    }

}
