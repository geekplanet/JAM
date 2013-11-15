package jam.bundles.orm.dbdriver;

public class SqlData {

    public SqlData(String type, String value)
    {
        this.type = type;
        this.value = value;
    }

    public String type;    // тип значения в таблице
    public String value;    // значение в таблице
}
