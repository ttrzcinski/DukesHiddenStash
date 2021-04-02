package main.java.org.ttrzcinski.interfaces;

import java.util.List;

public interface IConsoleMenuBuilder<T> {

    /**
     * Adds header above the menu.
     *
     * @param header given header
     * @return handle to console menu
     */
    T withHeader(String header);

    /**
     * Adds new item to kept items list.
     *
     * @param item given item
     * @return handle to console menu
     */
    T withItem(String item);

    /**
     * Adds given list of items to kept list of items.
     *
     * @param items given list of items
     * @return handle to console menu
     */
    T withItems(List<String> items);

    /**
     * Creates new instance from line with items to parse.
     *
     * @param line given line to parse
     * @return created new instance
     */
    T fromLine(String line);

    /**
     * Builds the menu
     *
     * @return
     */
    T build();
}
