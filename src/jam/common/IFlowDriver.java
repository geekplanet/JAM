package jam.common;

import jam.bundles.orm.dbdriver.SqlData;

import java.sql.ResultSet;
import java.util.Map;

/**
 * TODO: написать аннотацию к интерфейсу
 */
public interface IFlowDriver {
    /**
     * Принимает имя таблицы в БД и Map имя_ячейки => значение
     */
    public void insert();
    public ResultSet get(String query);
    public void update();
    public void delete();
}
