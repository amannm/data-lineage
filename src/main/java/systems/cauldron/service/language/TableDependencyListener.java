package systems.cauldron.service.language;

import systems.cauldron.service.language.gen.SQLiteBaseListener;
import systems.cauldron.service.language.gen.SQLiteParser;

import java.util.HashSet;
import java.util.Set;

public class TableDependencyListener extends SQLiteBaseListener {


    private final Set<TableReference> referenceList = new HashSet<>();

    private String currentDatabaseName = null;
    private String currentTableName = null;

    @Override
    public void enterDatabase_name(SQLiteParser.Database_nameContext ctx) {
        currentDatabaseName = ctx.getText();
    }

    @Override
    public void enterTable_name(SQLiteParser.Table_nameContext ctx) {
        currentTableName = ctx.getText();
    }

    @Override
    public void exitTable_reference(SQLiteParser.Table_referenceContext ctx) {
        TableReference tableReference = new TableReference(currentDatabaseName, currentTableName);
        referenceList.add(tableReference);
        currentDatabaseName = null;
        currentTableName = null;
    }


    public Set<TableReference> getTableReferences() {
        return referenceList;
    }


}
