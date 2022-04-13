package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Withdrawal;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.WithDrawalDAO;

import java.sql.SQLException;

public class WithDrawalManager {
    private static WithDrawalManager withDrawalManager;
    private static WithDrawalDAO withDrawalDAO;

    private WithDrawalManager() {
        withDrawalDAO = DAOFactory.getWithDrawalDao();
    }

    public static WithDrawalManager getInstance() {
        if (withDrawalManager == null) {
            withDrawalManager = new WithDrawalManager();
        }
        return withDrawalManager;
    }

    public Withdrawal addAddress(Withdrawal withdrawal) throws DALException, SQLException {
        return withDrawalDAO.addAddress(withdrawal);
    }

    public Withdrawal updateAddress(Withdrawal withdrawal) throws DALException, SQLException {
        return withDrawalDAO.updateAddress(withdrawal);
    }

}
