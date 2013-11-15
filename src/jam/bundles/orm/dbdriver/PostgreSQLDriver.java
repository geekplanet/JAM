package jam.bundles.orm.dbdriver;

import java.sql.*;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Iterator;
import java.util.Set;

/**
 * TODO: написать аннотацию к классу
 */
public class PostgreSQLDriver implements IDBDriver {

    private Connection con;
    private ResultSet rs;
    private PreparedStatement pst;
    private String user;
    private String url;
    private String password;


    @Override
    public boolean Connect(String url, String user, String password) {
        try{
            this.url = url;
            this.user = user;
            this.password = password;
            con = DriverManager.getConnection(url, user, password);
        }
        catch (SQLException ex) {

        }
    return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean closeConnection() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(PostgreSQLDriver.class.getName());
            lgr.log(Level.WARNING, ex.getMessage(), ex);
        }
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

   // @Override
   // public void executeQuery() {
   //     //To change body of implemented methods use File | Settings | File Templates.
   // }

    public void linsert(String tableName, Map<Object,Object> mp)
    {
        String query = "INSERT INTO " + tableName;
        Set s=mp.entrySet();
        Iterator it=s.iterator();

        while(it.hasNext())    // @TODO: Переделать в foreach
        {
            Map.Entry m =(Map.Entry)it.next();
            int key=(Integer)m.getKey();
            String value=(String)m.getValue();
            System.out.println("Key :"+key+"  Value :"+value);
        }

    }

    @Override
    public void insert() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResultSet get(String query) {
            try {
                pst = con.prepareStatement(query);
                rs = pst.executeQuery();
                return rs;
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(PostgreSQLDriver.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
        return null;
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void query(String query)
    {
        try {
            pst = con.prepareStatement(query);    // @TODO: Сделать подстановку плейсхолдеров
            //pst.setString(1, getFirstName());
            //pst.setString(2, getLastName());
            //pst.setInt(3, getAge());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(PostgreSQLDriver.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
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
