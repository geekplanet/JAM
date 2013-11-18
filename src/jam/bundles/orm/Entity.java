package jam.bundles.orm;
import jam.bundles.orm.dbdriver.IDBDriver;
import java.lang.reflect.Field;
import java.lang.Class;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Entity {
    protected boolean inDb;   // true - объект в БД, false - только в предметной облости
    protected IDBDriver driver;
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
        className = className.substring(className.lastIndexOf(".")+1);
        ResultSet rs = driver.get("SELECT * FROM " + className);
        ArrayList ar = new ArrayList();
        try {
            Class c = this.getClass();
            Field[] publicFields = c.getFields();
            while (rs.next()) {
                // @TODO: сделать заполнение методов Entity
                Person en = new Person(this.driver);
                en.id = rs.getInt(1);
                en.firstname = rs.getString(2);
                en.lastname = rs.getString(3);
                en.age = rs.getInt(4);
                ar.add(en);
                for (Field field : publicFields) {
                    try{
                        Object value = field.get(this);
                        System.out.println("Значение: " + value);
                    }
                    catch (IllegalAccessException e) {
                        System.out.println("Ошибка IllegalAccessException");
                    }
                }
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Person.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return ar;
    }

    public static void save()
    {

    }
}