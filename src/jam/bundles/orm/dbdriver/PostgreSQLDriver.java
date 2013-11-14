package jam.bundles.orm.dbdriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TODO: написать аннотацию к классу
 */
public class PostgreSQLDriver implements DBDriver {

    @Override
    public boolean Connect() {

        String url = "jdbc:postgresql://192.168.1.5/db_builder";
        String user = "defender";
        String password = "1234";
        Connection con = DriverManager.getConnection(url, user, password);

        try{    // а так работает, но всё только в пределах одного блока
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
    public void get() {
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
