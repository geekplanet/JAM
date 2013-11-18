package jam.bundles.orm;

import jam.bundles.orm.dbdriver.IDBDriver;

public class Person extends Entity {

    public int id;
    public String firstname;
    public String lastname;
    public int age;

    public Person(IDBDriver dbDriver){
        super(dbDriver);
    }

}
