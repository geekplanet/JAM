package jam.bundles.orm;
import jam.bundles.orm.dbdriver.IDBDriver;
import jam.bundles.orm.dbdriver.SqlData;

import java.lang.reflect.Field;
import java.lang.Class;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс Entity - абстрактный класс сущности, один объект которой есть строчка в таблице БД (Паттерн ActiveRecord - одна
 * из реализаций паттерна ORM)
 */
public class Entity {
    protected boolean inDb;   // true - объект в БД, false - только в предметной облости
    protected IDBDriver driver;
    protected Map<String,String> meta;    // мета данные в виде пар: имя_столбца -> тип_стоблца

    public Entity(IDBDriver driver)
    {
        this.driver = driver;
    }

    /*public void getField()    // Было нужно для теста
    {
        Class c = this.getClass();
        Field[] publicFields = c.getFields();
        for (Field field : publicFields) {
            Class fieldType = field.getType();
            System.out.println("Имя: " + field.getName());
            System.out.println("Тип: " + fieldType.getName());
            field.setAccessible(true);
            try{
                Object value = field.get(this);
                System.out.println("Значение: " + value);
            }
             catch (IllegalAccessException e) {
                System.out.println("Ошибка IllegalAccessException");
            }

        }
    }*/

    public ArrayList select()    // Извлекаем все данные из таблицы
    {
        String className = this.getClass().getName();
        className = className.substring(className.lastIndexOf(".")+1);    // получаем имя класса без пакета
        ResultSet rs = driver.get("SELECT * FROM " + className);    // из объекта ResultSet будем считывать данные
        ArrayList ar = new ArrayList();
        try {
            Class c = this.getClass();
            Field[] publicFields = c.getFields();
            while (rs.next()) {
                Person objectInstance = new Person(this.driver);
                Person en = new Person(this.driver);    // @TODO: Найти способ инстанциировать любой класс
                Class enCl = en.getClass();
                int i = 1;
                for (Field field : publicFields) {
                    try{
                        try{
                            Object value = field.get(objectInstance);
                            String fieldName = field.getName();
                            String fieldType = findValueOfKey(meta,fieldName);
                            //System.out.println("FieldName: " + fieldName + " ; FieldType: " + fieldType);
                            Field f1 = enCl.getField(fieldName);
                            if (fieldType == "varchar")
                                f1.set(en,rs.getString(i));
                            else
                                f1.set(en,rs.getInt(i));
                            field.set(objectInstance, value);
                            //System.out.println("Значение: " + value);
                        }
                    catch (IllegalAccessException e) {
                        System.out.println("Ошибка IllegalAccessException");
                    }
                    }
                    catch (NoSuchFieldException e)
                    {
                        System.out.println("NoSuchFieldException");
                    }
                    i++;
                }
                ar.add(en);
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Person.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return ar;
    }

    private String findValueOfKey(Map<String,String> meta, String key2)
    {
        Set s=meta.entrySet();
        Iterator it=s.iterator();
        boolean t = true;
        String value = null;
        while(it.hasNext() && t)
        {
            Map.Entry m =(Map.Entry)it.next();
            String key=(String)m.getKey();
            if (key.equals(key2))
            {
                t = false;
                value=(String)m.getValue();
            }
        }
        return value;
    }

    public void save()    // @TODO: реализовать метод сохранения в БД
    {

    }

    public void delete()    // @TODO: реализовать метод удаления из БД
    {

    }
}