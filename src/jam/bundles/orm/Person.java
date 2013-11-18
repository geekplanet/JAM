package jam.bundles.orm;

import jam.bundles.orm.dbdriver.IDBDriver;

public class Person extends Entity {

    public Person(IDBDriver dbDriver){
        super(dbDriver);
    }

}
