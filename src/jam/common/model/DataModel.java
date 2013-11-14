package jam.common.model;

/**
 * TODO: написать аннотацию к интерфейсу
 */
import jam.bundles.orm.dbdriver.DBDriver;
import jam.common.exception.LogicException;

import java.util.Map;

public interface DataModel {

    public void insert(Map<String, String> fieldSet, DBDriver driver) throws LogicException;
    public void get(Map<String, String> fieldSet, DBDriver driver);
    public void update(Map<String, String> fieldSet, Map<String, String> where, DBDriver driver);
    public void delete(Map<String, String> fieldSet, DBDriver driver);
}
