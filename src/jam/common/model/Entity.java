package jam.common.model;

/**
 * TODO: написать аннотацию к классу
 */
import jam.bundles.orm.dbdriver.DBDriver;
import jam.common.exception.LogicException;


import java.util.Map;
import java.util.Vector;

public class Entity {
    private class Field {
        public String fieldName;
        public String fieldValue;
    }
    Vector<Field> data;

    public Entity() {
        data = new Vector<Field>();
    }




}
