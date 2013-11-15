package jam.common;

import java.sql.ResultSet;

/**
 * TODO: написать аннотацию к интерфейсу
 */
public interface IFlowDriver {
    public void insert();
    public ResultSet get(String query);
    public void update();
    public void delete();
}
