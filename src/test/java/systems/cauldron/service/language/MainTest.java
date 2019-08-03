package systems.cauldron.service.language;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MainTest {

    @Test
    void test() {
        Set<TableReference> extract = Main.extract("SELECT hello.test, world.fart FROM hello.world INNER JOIN foo.bar on world.asdf = bar.asdf");
        assertEquals(new HashSet<>(Arrays.asList("hello.world", "foo.bar")), extract.stream().map(TableReference::toString).collect(Collectors.toSet()));
    }

}
