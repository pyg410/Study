package PrintProblem.SimpleDatabase;

import PrintProblem.SimpleDatabase.Table.Table;

import java.util.HashMap;

public class SimpleDatabase {

    private final HashMap<String ,Table> tables;


    private SimpleDatabase(){
        this.tables = new HashMap<>();
    }

    private static class SimpleDatabaseHolder{
        private static final SimpleDatabase INSTANCE = new SimpleDatabase();

    }

    public static SimpleDatabase getInstance(){
        return SimpleDatabaseHolder.INSTANCE;
    }


    public HashMap<String, Table> getTables(){
        return tables;
    }

    public void newTables(String tableName, Table table){
        tables.put(tableName, table);
    }




}
