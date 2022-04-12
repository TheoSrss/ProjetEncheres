package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Bid;
import fr.eni.encheres.dal.BidDAO;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UserDAO;
import fr.eni.encheres.dal.jdbc.BidJdbcImpl;

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
}
