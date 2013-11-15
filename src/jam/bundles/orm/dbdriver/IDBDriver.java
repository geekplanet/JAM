package jam.bundles.orm.dbdriver;

/**
 * TODO: написать аннотацию к интерфейсу
 */
import jam.common.IFlowDriver;

import java.util.Map;

public interface IDBDriver extends IFlowDriver {

    public boolean Connect(String url, String user, String password);
    public boolean closeConnection();
    public void query(String query,Map<Object,SqlData> mp);
    //public void executeQuery();
}
