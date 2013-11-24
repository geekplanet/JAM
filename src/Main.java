import jam.bundles.orm.dbdriver.PostgreSQLDriver;
import jam.bundles.orm.Entity;
import jam.bundles.orm.Person;

import java.sql.SQLException;
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

            /* Работа с БД драйвером */

            /*mp.put("firstname", new SqlData("String","Федор"));    // вставка данных через драйвер
            mp.put("lastname", new SqlData("String","Ониськин"));
            mp.put("age", new SqlData("int","1"));
            pg.insert("person",mp);               */
            /*
            Map<Object,SqlData> mp2=new HashMap<Object,SqlData>();    // обновление данных через драйвер
            mp2.put("firstname", new SqlData("String","Иван"));
            mp2.put("lastname", new SqlData("String","Петров"));
            pg.update("person",mp2,"id=1");*/

            /* Далее работа с ORM */

            /*Person en0 = new Person(pg);    // вставка данных через ORM
            en0.lastname = "Пупкин";
            en0.firstname = "Егор";
            en0.age = 12;
            en0.save();*/

            Person en = new Person(pg);    // параметром передаём объект конкретного DbDrive - рq
            ArrayList<Entity> ar;
            ar = en.select();    // получение всех сущностей таблицы

            Person p1 = (Person) ar.get(0);
            System.out.println(p1.firstname + " " + p1.lastname + " " + p1.age);  //*/

        try {
            p1.exampleMetaData();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

            /*Person p2 = (Person) ar.get(1);
            p2.lastname = "Свиридов";    // изменяем
            p2.save();    // сохраняем
            p2.firstname = "Женёк";
            p2.lastname = "Никитин";
            p2.save();
            System.out.println(p1.age + " " + p1.lastname);

            Person p3 = (Person) ar.get(2);
            p3.firstname = "Лёха";
            p3.lastname = "Абрамов";
            p3.save();
            p3.delete();    // удаляем
            System.out.println(p3.age + " " + p3.lastname);


            Person p4 = (Person) ar.get(3);
            System.out.println(p4.age + " " + p4.lastname + " " + p4.firstname);    // получение данных чарез ОРМ*/
            pg.closeConnection();
    }
}
