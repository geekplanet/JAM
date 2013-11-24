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
    public void exampleMetaData() throws SQLException {
        String url = "jdbc:postgresql://192.168.1.5/db_builder";
        String user = "defender";
        String password = "1234";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);

        ResultSet rs = null;
            DatabaseMetaData meta = connection.getMetaData();
            rs = meta.getColumns(null, "%", "person", "%");
            while (rs.next()) {

                String columnType = rs.getString("TYPE_NAME");
                String columnName = rs.getString("COLUMN_NAME");
                int size = rs.getInt("COLUMN_SIZE");
                int nullable = rs.getInt("NULLABLE");
                int position = rs.getInt("ORDINAL_POSITION");
                System.out.println("column name=" + columnName);
                System.out.println("type=" + columnType);
                System.out.println("size=" + size);

                if (nullable == DatabaseMetaData.columnNullable) {
                    System.out.println("nullable is true");
                } else {
                    System.out.println("nullable is false");
                }
                System.out.println("position" + position);
            }
            connection.close();


        /*if (rs != null) {
            while (rs.next()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    if (i > 1) {
                        out.print(",");
                    }

                    int type = rsmd.getColumnType(i);
                    boolean autoIncr = rsmd.isAutoIncrement(i);
                    int cc = rsmd.getColumnCount();    // количество колонок
                    int pr = rsmd.getPrecision(1);
                    if (type == Types.VARCHAR || type == Types.CHAR) {
                        out.print(rs.getString(i) + " тип " + type + " инкр " + autoIncr + " columnPrecision " + pr);
                    } else {
                        out.print(rs.getLong(i) + " тип " + type + " инкр " + autoIncr + " columnPrecision " + pr);
                    }
                }

                out.println();
            }
        }*/

        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
