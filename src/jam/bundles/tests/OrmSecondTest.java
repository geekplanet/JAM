package jam.bundles.tests;

import jam.bundles.orm.dbdriver.PostgreSQLDriver;
import jam.bundles.orm.dbdriver.SqlData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.junit.runners.JUnit4;

import java.util.HashMap;
import java.util.Map;

public class OrmSecondTest {

    private PostgreSQLDriver pg;

    @Before
    public void before() throws Exception
    {
        pg = new PostgreSQLDriver();
        String url = "jdbc:postgresql://192.168.1.5/db_builder";
        String user = "defender";
        String password = "1234";
        pg.Connect(url,user,password);    // коннектимся к БД //*/
    }

    @Test//(expected = Throwable.class)
    public void update() throws Exception
    {
        Map<Object,SqlData> mp2=new HashMap<Object,SqlData>();    // обновление данных через драйвер
        mp2.put("firstname", new SqlData("String","Иван"));
        mp2.put("lastname", new SqlData("String","Петров2"));
        pg.update("person",mp2,"id=184");
    }

    @After
    public void after() throws Exception
    {
        pg.closeConnection();
    }
}
