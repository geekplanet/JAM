package jam.bundles.orm.dbdriver;

import java.sql.ResultSet;
import java.util.Map;

/**
 * TODO: написать аннотацию к классу
 */
public class MySQLDriver implements IDBDriver {

    @Override
    public boolean Connect(String url, String user, String password) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean closeConnection() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

   // @Override
  //  public void executeQuery() {
   //     //To change body of implemented methods use File | Settings | File Templates.
    //}

    @Override
    public void insert(String tableName, Map<Object,Object> mp) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResultSet get(String query) {
        //To change body of implemented methods use File | Settings | File Templates.
        return null;
    }

    @Override
    public void query(String query)
    {

    }

    @Override
    public void update(String tableName, Map<Object,Object> mp, String where) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
