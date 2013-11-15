package jam.bundles.orm.dbdriver;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TODO: написать аннотацию к классу
 */
public class PostgreSQLDriver implements DBDriver {

    private Connection con;

    @Override
    public boolean Connect() {

        String url;
        String user;
        String password;

        try{
            url = "jdbc:postgresql://192.168.1.5/db_builder";
            user = "defender";
            password = "1234";
            con = DriverManager.getConnection(url, user, password);
        }
        catch (SQLException ex) {

        }


    return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean closeConnection() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void executeQuery() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void insert() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResultSet get()
    {
        return null;
    }

    public void lget() {
            PreparedStatement pst = null;
            ResultSet rs = null;
            try {
                String url = "jdbc:postgresql://192.168.1.5/db_builder";
                String user = "defender";
                String password = "1234";
                con = DriverManager.getConnection(url, user, password);
                pst = con.prepareStatement("SELECT * FROM person");
                rs = pst.executeQuery();
                while (rs.next())
                {
                    System.out.print(rs.getInt(1));    // @TODO: для теста, убрать
                    System.out.print(" ");
                    System.out.print(rs.getString(2));
                    System.out.print(" ");
                    System.out.print(rs.getString(3));
                    System.out.print(" ");
                    System.out.println(rs.getInt(4));
                }
                //return rs;
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(PostgreSQLDriver.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
        //return null;
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
