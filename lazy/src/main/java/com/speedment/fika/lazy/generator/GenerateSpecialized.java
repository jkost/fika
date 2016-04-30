package com.speedment.fika.lazy.generator;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author Per Minborg
 */
public class GenerateSpecialized {

    final static Path TEMPLATE = Paths.get("src/main/resources/", "LazyTemplate.txt");
    final static Path TARGET_DIR = Paths.get("src/main/java/com/speedment/fika/lazy/specialized");

    public static void main(String[] args) throws IOException {

        for (final Class<?> clazz : Arrays.asList(
            String.class,
            Boolean.class,
            Byte.class,
            Float.class,
            Number.class,
            Short.class,
            BitSet.class,
            UUID.class,
            BigDecimal.class,
            BigInteger.class,
            Timestamp.class
        )) {
            final List<String> code = Files.lines(TEMPLATE)
                .map(l -> l.replace("{$0}", clazz.getSimpleName()))
                .map(l -> l.replace("{$1}", importStatement(clazz).orElse("")))
                .collect(toList());
            final Path target = Paths.get(TARGET_DIR.toString(), "Lazy" + clazz.getSimpleName() + ".java");
            Files.write(target, code, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
        }

    }

    private static Optional<String> importStatement(Class<?> clazz) {
        if ("java.lang".equals(clazz.getPackage().getName())) {
            return Optional.empty();
        }
        return Optional.of("import " + clazz.getName() + ";");
    }

}
