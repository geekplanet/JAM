package jam.bundles.tests;

import jam.bundles.orm.Entity;
import jam.bundles.orm.Person;
import jam.bundles.orm.dbdriver.PostgreSQLDriver;
import junit.framework.TestCase;

import java.util.ArrayList;

public class OrmFirstTest extends TestCase {

    private PostgreSQLDriver pg;

    protected void setUp() throws Exception
    {
        pg = new PostgreSQLDriver();
        String url = "jdbc:postgresql://192.168.1.5/db_builder";
        String user = "defender";
        String password = "1234";
        pg.Connect(url,user,password);    // коннектимся к БД //*/
    }

    public void testSelect() throws Exception
    {
        Person en = new Person(pg);    // параметром передаём объект конкретного DbDrive - рq
        ArrayList<Entity> ar;
        ar = en.select();    // получение всех сущностей таблицы

        Person p1 = (Person) ar.get(0);
        System.out.println(p1.firstname + " " + p1.lastname + " " + p1.age);
    }

    protected void tearDown() throws Exception{
        pg.closeConnection();
    }
}
