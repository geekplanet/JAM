package jam.bundles.orm.dbdriver;

public class SqlData<V> {

    public SqlData(String type, V value)
    {
        this.type = type;
        this.value = value;
    }

    public String type;    // тип значения в таблице
    public V value;    // значение в таблице
}
