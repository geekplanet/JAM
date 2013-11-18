package jam.bundles.orm;
import jam.bundles.orm.dbdriver.IDBDriver;
import jam.bundles.orm.dbdriver.SqlData;

import java.lang.reflect.Field;
import java.lang.Class;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
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
    //public int id;

    public Entity(IDBDriver driver)
    {
        this.driver = driver;
    }

    public void setInDb(boolean inDb)
    {
        this.inDb = inDb;
    }

    /*
     * Извлекает все данные из таблицы и возвращает объекты (ORM)
     */
    public ArrayList select()    // Извлекаем все данные из таблицы    // @TODO: Сделать полиморфные функции для более точных выборок (например по id)
    {
        String tableName = this.getClass().getName();
        tableName = tableName.substring(tableName.lastIndexOf(".")+1);    // получаем имя класса без пакета
        ResultSet rs = driver.get("SELECT * FROM " + tableName);    // из объекта ResultSet будем считывать данные
        ArrayList ar = new ArrayList();
        try {
            Class c = this.getClass();
            Field[] publicFields = c.getFields();
            while (rs.next()) {
                Person objectInstance = new Person(this.driver);
                Person en = new Person(this.driver);    // @TODO: Найти способ инстанциировать любой класс
                en.setInDb(true);    // показываем, что сущности уже содержаться в БД
                Class enCl = en.getClass();
                int i = 1;
                for (Field field : publicFields) {
                    try{
                        try{
                            Object value = field.get(objectInstance);
                            String fieldName = field.getName();
                            String fieldType = findValueOfKey(meta,fieldName);    // находим в мета данных тип значения по имени столбца
                            Field f1 = enCl.getField(fieldName);
                            if (fieldType == "varchar")
                                f1.set(en,rs.getString(i));    // заполняем значениями очередную сущность выборки
                            else
                                f1.set(en,rs.getInt(i));
                            field.set(objectInstance, value);
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

    /*
     * Находит в мета данных тип столбца по названию
     */
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

    /*
     * Добавляет/обновляет строчку в БД, соответствующую этой таблице
     */
    public void save()
    {
        String tableName = this.getClass().getName();
        tableName = tableName.substring(tableName.lastIndexOf(".")+1);    // получаем имя класса без пакета
        Map<Object,SqlData> map = new HashMap<Object, SqlData>();
        Class c = this.getClass();
        Field[] publicFields = c.getFields();
        for (Field field : publicFields) {
            try{
                Object value = field.get(this);
                String fieldName = field.getName();
                String fieldType = findValueOfKey(meta,fieldName);
                if (fieldName != "id")
                {
                    if (fieldType == "varchar")
                    {
                        fieldType = "String";
                        map.put(fieldName,new SqlData(fieldType,value));
                    }
                    if (fieldType == "integer")
                    {
                        fieldType = "int";
                        map.put(fieldName,new SqlData(fieldType,value.toString()));
                    }
                    //System.out.println(fieldName + "  " + fieldType + "  " + value);
                }
            }
            catch (IllegalAccessException e) {
                System.out.println("Ошибка IllegalAccessException");
            }
        }
        if(!inDb)
        {
            driver.insert(tableName,map);
            inDb = true;    // сущность (строчка таблицы) сохранена в БД
        }
        else
        {
            driver.update(tableName,map,"id=" + getId());
        }
    }

    /*
     * Удаляет объект из БД (потом можно вызвать метод save() и восстановить)
     */
    public void delete()    // @TODO: реализовать метод удаления из БД
    {
        if (getInDb())
        {
            setInDb(false);
            String tableName = this.getClass().getName();
            tableName = tableName.substring(tableName.lastIndexOf(".")+1);    // получаем имя класса без пакета
            String where = "id=" + getId();
            driver.delete(tableName,where);
        }
    }

    public int getId()    // переопределяется
    {
        return -1;
    }

    public boolean getInDb()    // переопределяется
    {
        return false;
    }
}