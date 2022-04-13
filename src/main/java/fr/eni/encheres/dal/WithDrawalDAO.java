package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Withdrawal;

import java.sql.SQLException;

public interface WithDrawalDAO extends DAO<Withdrawal> {
    public Withdrawal addAddress(Withdrawal w) throws DALException, SQLException;
    public Withdrawal updateAddress(Withdrawal w) throws DALException, SQLException;

}
