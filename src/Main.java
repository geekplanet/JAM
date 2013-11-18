import jam.bundles.orm.dbdriver.PostgreSQLDriver;
import java.sql.ResultSet;
import java.sql.SQLException;
import jam.bundles.orm.Entity;
import jam.bundles.orm.Person;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import jam.bundles.orm.dbdriver.SqlData;

public class Main {

    public static void main(String[] args) {
            PostgreSQLDriver pg = new PostgreSQLDriver();

            String url = "jdbc:postgresql://192.168.1.5/db_builder";
            String user = "defender";
            String password = "1234";
            pg.Connect(url,user,password);    // коннектимся к БД

            Map<Object,SqlData> mp=new HashMap<Object, SqlData>();

            /*mp.put("firstname", new SqlData("String","Федор"));    // вставка данных через драйвер
            mp.put("lastname", new SqlData("String","Ониськин"));
            mp.put("age", new SqlData("int","1"));
            pg.insert("person",mp);               */
            /*
            Map<Object,SqlData> mp2=new HashMap<Object,SqlData>();    // обновление данных через драйвер
            mp2.put("firstname", new SqlData("String","Иван"));
            mp2.put("lastname", new SqlData("String","Петров"));
            pg.update("person",mp2,"id=1");*/

            Person en = new Person(pg);    // параметром передаём объект конкретного DbDrive - ра
            ArrayList<Entity> ar;
            ar = en.select();
            Person p = (Person) ar.get(0);
            System.out.println(p.age + " " + p.lastname);

            Person p1 = (Person) ar.get(1);
            p1.lastname = "Свиридов";
            p1.save();
            System.out.println(p1.age + " " + p1.lastname);    // получение данных чарез ОРМ
            Person p3 = (Person) ar.get(2);
            System.out.println(p3.age + " " + p3.lastname);
            Person p4 = (Person) ar.get(3);
            System.out.println(p4.age + " " + p4.lastname + " " + p4.firstname);    // получение данных чарез ОРМ*/

            pg.closeConnection();
    }
}
