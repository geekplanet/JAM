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
    public void insert(String tableName, Map<Object,SqlData> mp);
    public ResultSet get(String query);
    public void update(String tableName, Map<Object,SqlData> mp, String where);
    public void delete(String tableName, String where);
}
