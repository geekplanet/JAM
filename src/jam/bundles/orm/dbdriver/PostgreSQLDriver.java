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

    @Override
    public void insert(String tableName, Map<Object,Object> mp)
    {
        StringBuffer query = new StringBuffer();
        query.append("INSERT INTO " + tableName + "(");
        StringBuffer keys = new StringBuffer();
        StringBuffer values = new StringBuffer();
        Set s=mp.entrySet();
        Iterator it=s.iterator();
        while(it.hasNext())    // @TODO: Переделать в foreach
        {
            Map.Entry m =(Map.Entry)it.next();
            String key=(String)m.getKey();
            String value=(String)m.getValue();
            keys.append(key + ",");
            values.append("'" + value + "',");
        }
        // удаляем последние запятые
        keys.delete(keys.length() - 1,keys.length());
        values.delete(values.length() - 1,values.length());
        query.append(keys + ") VALUES (");
        query.append(values + ");");
        System.out.println(query);
        query(query.toString());    // выполняем запрос без возвращения ResultSet
    }

    @Override
    public void update(String tableName, Map<Object,Object> mp, String where)
    {
        StringBuffer query = new StringBuffer();
        query.append("Update " + tableName + " SET ");
        Set s=mp.entrySet();
        Iterator it=s.iterator();
        while(it.hasNext())    // @TODO: Переделать в foreach
        {
            Map.Entry m =(Map.Entry)it.next();
            String key=(String)m.getKey();
            String value=(String)m.getValue();
            query.append(key);
            query.append("='");
            query.append(value);
            query.append("',");
        }
        query.delete(query.length()-1,query.length());
        query.append(" WHERE ");
        query.append(where);
        query.append(";");
        query(query.toString());    // выполняем запрос без возвращения ResultSet
        System.out.println(query.toString());
    }

    @Override
    public void delete(String tableName, String where)
    {
        String query = "DELETE FROM " + tableName + " WHERE " + where;
        query(query);
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
}
