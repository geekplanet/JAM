package jam.bundles.orm;
import jam.bundles.orm.dbdriver.IDBDriver;
import jam.bundles.orm.dbdriver.SqlData;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.Class;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;

public class Entity {

    public int id;
    public boolean inDb;   // true - объект в БД, false - только в предметной облости
    public IDBDriver driver;

    public String name;
    //private String name1;
    //public int age;
    //public boolean flag;


    public Entity(IDBDriver driver)
    {
        this.driver = driver;
        this.name = "Иван";
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void getField()
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
    }

    public ArrayList select()
    {
        String className = this.getClass().getName();
        className = className.substring(className.lastIndexOf(".")+1);
        ResultSet rs = driver.get("SELECT * FROM " + className);
        //String s = this.getClass().getName();
        //s= s.substring(s.lastIndexOf(".")+1);
        //System.out.println(s);
        Class c = this.getClass();

        Field[] publicFields = c.getFields();    // получаем поля класса

        Map<Object,SqlData> mp;

        return null;
    }

    public static void save()
    {

    }
}