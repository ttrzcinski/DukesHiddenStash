package org.ttrzcinski.interfaces;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public interface IConsoleMenuOf<T> {

    /**
     * Creates new instance with given list of items.
     *
     * @param items given items
     * @return newly created instance
     */
    T of(List<String> items);

    /**
     * Creates new instance with given array of items.
     *
     * @param items given items
     * @return newly created instance
     */
    T of(String[] items);

    /**
     * Creates new instance with given items as line to parse.
     *
     * @param line line to parse
     * @return newly created instance
     */
    T of(String line);

    /**
     * Creates new instance with given items.
     *
     * @param items given items
     * @return newly created instance
     */
    T of(HashSet<String> items);

    /**
     * Creates new instance with given items as options.
     *
     * @param items given items
     * @return newly created instance
     */
    T of(HashMap<String, String> items);
}
