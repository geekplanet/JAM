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
import java.util.Set;

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

        Class clazz = Entity.class;

        //Field f = this.getClass().getDeclaredField("name1");

        //Class  aClass = this.class;
        //Field field = c.getField("name");


        //constr.setAccessible(true);


        Object someObject = this;
        //this.setAccessible(true);
        /*for (Field field : someObject.getClass().getDeclaredFields()) {
            field.setAccessible(true); // You might want to set modifier to public first.
            Object value = new Object();
            value = field.get(someObject);
            if (value != null) {
                System.out.println(field.getName() + "=" + value);
            }
        }*/

      /*  Class<?> clazz = PublicClass.class.getDeclaredClasses()[0];
      Constructor<?> constr = this.getDeclaredConstructors()[0];
      constr.setAccessible(true);
      PublicClass pc = new PublicClass();*/


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

    public void select()
    {
        ResultSet rs = driver.get("SELECT * FROM " + this.getClass().getName());
        Class c = this.getClass();

        Field[] publicFields = c.getFields();    // получаем поля класса

        Map<Object,SqlData> mp;

        for (Field field : publicFields) {
            //mp.put(field.getName(), new SqlData(field.getType(),field.get(this)));    // @TODO: разобраться с динамическим получением значения
        }
    }

    public static void save()
    {

    }
}
