package com.speedment.fika.lazy.demo;

import com.speedment.fika.lazy.Lazy;
import com.speedment.fika.lazy.LazyInt;
import com.speedment.fika.lazy.specialized.LazyByte;
import com.speedment.fika.lazy.specialized.LazyString;
import java.time.Instant;
import java.util.Date;

/**
 *
 * @author Per Minborg
 */
public class Demo {

    private final Lazy<Date> lazyDate = Lazy.create();
    private final LazyString lazyString = LazyString.create();
    private final LazyInt lazyInt = LazyInt.create();
    private final LazyByte lazyByte = LazyByte.create();

    public static void main(String[] args) {

    }

    private void lazyInit() {

        Date date = lazyDate.getOrCompute(() -> Date.from(Instant.now()));
        String string = lazyString.getOrCompute(() -> "used");
        int intVal = lazyInt.getOrCompute(() -> 1 + 1);
        byte intByte = lazyByte.getOrCompute(() -> 127);

    }

}
