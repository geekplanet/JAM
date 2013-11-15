package jam.common;

import java.sql.ResultSet;

/**
 * TODO: написать аннотацию к интерфейсу
 */
public interface IFlowDriver {
    public void insert();
    public ResultSet get();
    public void update();
    public void delete();
}
