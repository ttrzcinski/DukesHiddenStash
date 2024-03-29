package org.ttrzcinski.utils;

import java.util.List;
import java.util.Random;

/**
 * Sets of randomization methods -  used mostly in tests.
 */
public class RandomExt {

    /**
     * Hidden constructor.
     */
    private RandomExt() {
    }

    /**
     * Generates pseudo-random name.
     *
     * @return full name with location
     */
    public static String randomName() {
        Random randy = new Random();

        List<String> names = List.of("Johny", "Jenny", "Jack", "Bob", "Mary", "Jimmy", "Johnathan", "Jill");
        List<String> lastnames = List.of("Jellopsiless", "von Horn", "Willis", "Quals", "Black", "Schultz", "Smith", "Novak");
        List<String> pseudo = List.of("Egghead", "Jenkins", "Clog", "Skip", "Doob", "Darks", "Nops", "Fats");
        List<String> location = List.of("Praha", "Dublin", "Redmine", "Malmo", "Bremen", "Malacka", "Langley", "Dallas");

        return new StringBuilder(names.get(randy.nextInt() * 8 - 1)).append(" ")
                .append(lastnames.get(randy.nextInt() * 8 - 1)).append(" \"")
                .append(pseudo.get(randy.nextInt() * 8 - 1)).append("\" from ")
                .append(location.get(randy.nextInt() * 8 - 1))
                .toString();
    }

}
