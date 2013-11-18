package jam.bundles.orm.dbdriver;

import java.sql.ResultSet;
import java.util.Map;

/**
 * TODO: написать аннотацию к классу
 */
public abstract class MySQLDriver implements IDBDriver {

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
    public void insert() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void get() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResultSet get(String query) {
        //To change body of implemented methods use File | Settings | File Templates.
        return null;
    }

    @Override
    public void query(String query, Map<Object,SqlData> mp)
    {

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
