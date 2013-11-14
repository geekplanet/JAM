package jam.bundles.orm.dbdriver;

/**
 * TODO: написать аннотацию к интерфейсу
 */
import jam.common.IFlowDriver;

public interface DBDriver extends IFlowDriver {

    public boolean Connect();
    public boolean closeConnection();
}
