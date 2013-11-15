package jam.bundles.xmlparser;

/**
 * TODO: написать аннотацию к классу
 */
import jam.bundles.orm.dbdriver.SqlData;
import jam.common.IFlowDriver;

import java.sql.ResultSet;
import java.util.Map;

public class Parser implements IFlowDriver {

    @Override
    public void insert(String tableName, Map<Object,SqlData> mp) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResultSet get(String query) {
        //To change body of implemented methods use File | Settings | File Templates.
        return null;
    }

    @Override
    public void update(String tableName, Map<Object,SqlData> mp, String where) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(String tableName, String where) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}
