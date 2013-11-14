package jam.common.model;

/**
 * TODO: написать аннотацию к классу
 */
import jam.bundles.orm.dbdriver.DBDriver;
import jam.common.exception.LogicException;

import java.util.Map;
import java.util.Vector;

public class Table implements DataModel {
    private class Field {
        public String fieldName;
        public String fieldType;
    }

    public Vector<Field> schema;
    public Vector<Entity> entities;


    public void addField(String name, String type) {
        /**
         * TODO: написать реализацию метода
         */
    }

    public void deleteField(String name, String type) {
        /**
         * TODO: написать реализацию метода
         */
    }

    public void changeName(String currentName, String newName) {
        /**
         * TODO: написать реализацию метода
         */
    }

    public void changeType(String name, String newType) {
        /**
         * TODO: написать реализацию метода
         */
    }

    @Override
    public void insert(Map<String, String> fieldSet, DBDriver driver) throws LogicException {
        /**
         * TODO: написать реализацию метода
         */
    }

    @Override
    public void get(Map<String, String> fieldSet, DBDriver driver) {
        /**
         * TODO: написать реализацию метода
         */
    }

    @Override
    public void update(Map<String, String> fieldSet, Map<String, String> where, DBDriver driver) {
        /**
         * TODO: написать реализацию метода
         */
    }

    @Override
    public void delete(Map<String, String> fieldSet, DBDriver driver) {
        /**
         * TODO: написать реализацию метода
         */
    }
}
