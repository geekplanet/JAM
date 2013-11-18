package jam.bundles.orm;
import jam.bundles.orm.dbdriver.IDBDriver;
import jam.bundles.orm.dbdriver.SqlData;

import java.util.HashMap;

public class Person extends Entity {

    public int id;
    public String firstname;
    public String lastname;
    public int age;

    public Person(IDBDriver dbDriver){
        super(dbDriver);
        this.meta = new HashMap<String, String>();
        //meta.put("id", "integer");
        meta.put("firstname", "varchar");
        meta.put("lastname", "varchar");
        meta.put("age", "integer");
    }

    public int getId()
    {
        return id;
    }

}
