package jam.bundles.orm;
import jam.bundles.orm.dbdriver.IDBDriver;

import java.io.PrintStream;
import java.sql.*;
import java.util.HashMap;

public class Person extends Entity {

    public int id;
    public String firstname;
    public String lastname;
    public int age;

    public Person(IDBDriver dbDriver){
        super(dbDriver);
        this.meta = new HashMap<String, String>();
        meta.put("firstname", "varchar");
        meta.put("lastname", "varchar");
        meta.put("age", "integer");
    }

    public int getId()
    {
        return id;
    }

    public boolean getInDb()
    {
        return inDb;
    }

/*
 * Пример получения мета данных
 */
    public void exampleMetaData()
    {
        String url = "jdbc:postgresql://192.168.1.5/db_builder";
        String user = "defender";
        String password = "1234";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM person;");
        ResultSet rs = null;
            rs = statement.executeQuery();
        PrintStream out = System.out;

        if (rs != null) {
            while (rs.next()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    if (i > 1) {
                        out.print(",");
                    }

                    int type = rsmd.getColumnType(i);
                    boolean autoIncr = rsmd.isAutoIncrement(i);
                    if (type == Types.VARCHAR || type == Types.CHAR) {
                        out.print(rs.getString(i) + " тип " + type + " инкр " + autoIncr);
                    } else {
                        out.print(rs.getLong(i) + " тип " + type + " инкр " + autoIncr);
                    }
                }

                out.println();
            }
        }
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
