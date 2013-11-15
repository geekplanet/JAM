package jam.bundles.orm.dbdriver;

/**
 * TODO: написать аннотацию к интерфейсу
 */
import jam.common.IFlowDriver;

public interface IDBDriver extends IFlowDriver {

    public boolean Connect(String url, String user, String password);
    public boolean closeConnection();
    //public void executeQuery();
}
