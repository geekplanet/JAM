import jam.bundles.orm.dbdriver.PostgreSQLDriver;
import jam.bundles.orm.dbdriver.SqlData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Array;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
	// write your code here
        try{
            PostgreSQLDriver pg = new PostgreSQLDriver();
            String url = "jdbc:postgresql://192.168.1.5/db_builder";
            String user = "defender";
            String password = "1234";
            pg.Connect(url,user,password);
            ResultSet rs = pg.get("SELECT * FROM person");
            while (rs.next())
            {
                System.out.print(rs.getInt(1));    // @TODO: для теста, убрать
                System.out.print(" ");
                System.out.print(rs.getString(2));
                System.out.print(" ");
                System.out.print(rs.getString(3));
                System.out.print(" ");
                System.out.println(rs.getInt(4));
            }

            Map<Object,SqlData> mp=new HashMap<Object, SqlData>();

            mp.put("firstname", new SqlData("String","Федор"));
            mp.put("lastname", new SqlData("String","Ониськин"));
            //mp.put("age", new SqlData("Integer","Ониськин"));
            pg.insert("person",mp);

            Map<Object,SqlData> mp2=new HashMap<Object,SqlData>();
            mp2.put("firstname", new SqlData("String","Иван"));
            mp2.put("lastname", new SqlData("String","Петров"));
            pg.update("person",mp2,"id=112");

            pg.delete("person","id=119");

            pg.closeConnection();
        } catch (SQLException ex) {

        }
    }
}
