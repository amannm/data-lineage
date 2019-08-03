package systems.cauldron.service.language;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import systems.cauldron.service.language.gen.SQLiteLexer;
import systems.cauldron.service.language.gen.SQLiteParser;

import java.util.Set;

public class Main {

    public static void main(final String[] args) {
        extract(args[0]);
    }

    public static Set<TableReference> extract(String text) {
        CodePointCharStream characters = CharStreams.fromString(text);
        SQLiteLexer lexer = new SQLiteLexer(characters);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SQLiteParser parser = new SQLiteParser(tokens);
        TableDependencyListener listener = new TableDependencyListener();
        SQLiteParser.ParseContext tree = parser.parse();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, tree);
        return listener.getTableReferences();
    }


}
