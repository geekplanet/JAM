package jam.bundles.orm.dbdriver;

import java.sql.ResultSet;

/**
 * TODO: написать аннотацию к классу
 */
public class MySQLDriver extends DbDriver {

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
    public ResultSet get(String query) {
        //To change body of implemented methods use File | Settings | File Templates.
        return null;
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
