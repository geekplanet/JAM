package jam.bundles.orm.dbdriver;

import java.sql.*;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Iterator;
import java.util.Set;

/**
 * Класс PostgreSQLDriver выполняет запросы к БД PgSQL
 */
public class PostgreSQLDriver implements IDBDriver {

    private Connection con;

    private ResultSet rs;    // ResultSet - БД возвращает response в виде объекта этого типа
    private PreparedStatement pst;    // Нужно для запроса с плейсхолдерами (защита от SQL инъекций)

    private String user;
    private String url;
    private String password;


    @Override
    public boolean Connect(String url, String user, String password) {    // Подключаемся к БД
        try{
            this.url = url;
            this.user = user;
            this.password = password;
            con = DriverManager.getConnection(url, user, password);
        }
        catch (SQLException ex) {
            Logger lgr = Logger.getLogger(PostgreSQLDriver.class.getName());
            lgr.log(Level.WARNING, ex.getMessage(), ex);
        }
        return false;
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
        return false;
    }

    @Override
    public void insert()    // @TODO: Метод заглушка для реализации интерфейса IFlowDriver. Подумать, что с этим делать
    {

    }

    public void insert(String tableName, Map<Object,SqlData> mp)
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
            keys.append(key + ",");
            values.append("?,");
        }
        // удаляем последние запятые
        keys.delete(keys.length() - 1,keys.length());
        values.delete(values.length() - 1,values.length());
        query.append(keys + ") VALUES (");
        query.append(values + ");");
        System.out.println(query);
        query(query.toString(),mp);    // выполняем запрос без возвращения ResultSet
    }

    @Override
    public void update()
    {

    }

    public void update(String tableName, Map<Object,SqlData> mp, String where)
    {
        StringBuffer query = new StringBuffer();
        query.append("Update " + tableName + " SET ");
        Set s=mp.entrySet();
        Iterator it=s.iterator();
        while(it.hasNext())    // @TODO: Переделать в foreach
        {
            Map.Entry m =(Map.Entry)it.next();
            String key=(String)m.getKey();
            query.append(key);
            query.append("=");
            query.append('?');
            query.append(",");
        }
        query.delete(query.length()-1,query.length());
        query.append(" WHERE ");
        query.append(where);
        query.append(";");
        query(query.toString(),mp);    // выполняем запрос без возвращения ResultSet
        System.out.println(query.toString());
    }

    public void delete(String tableName, String where)
    {
        String query = "DELETE FROM " + tableName + " WHERE " + where;
        query(query);
    }

    @Override
    public void delete()
    {

    }

    @Override
    public void get()
    {

    }

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
    public void query(String query, Map<Object,SqlData> mp)    // с плейсхолдерами (SELECT, UPDATE, CREATE ...)
    {
        try {
            pst = con.prepareStatement(query);
            Set s=mp.entrySet();
            Iterator it=s.iterator();
            int i = 0;
            while(it.hasNext())    // @TODO: Переделать в foreach
            {
                i++;
                Map.Entry m =(Map.Entry)it.next();
                SqlData temp = (SqlData) m.getValue();

                if (temp.type == "String")    // @TODO: Здесь будет case со всеми типати
                    pst.setString(i,(String) temp.value);
                else if (temp.type == "int")
                    pst.setInt(i, Integer.parseInt ((String) temp.value));
            }
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(PostgreSQLDriver.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public void query(String query)    // без плейсхолдеров (DELETE ...)
    {
        try {
            pst = con.prepareStatement(query);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(PostgreSQLDriver.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
