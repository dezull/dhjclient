package com.blogspot.aptgetmoo.dhjclient.finder;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Client for retrieving results from Jakim's Halal Web
 *
 * @author Dzul Nizam
 */
public interface IFinder {
    /**
     * Get Item(s) by keyword, type & page. The maximum number of Item(s) per page retrieved depend highly on the Jakim Web :(
     *
     * @param keyword 	Keyword
     * @param type		Item type
     * @param page		Page to retrieve
     * @return			List of Item(s) (a maximum of certain row, as returned by the Web server)
     * @throws IOException
     */
    ArrayList<Item> find(final String keyword,
            final ItemType type, final int page) throws IOException;

    /**
     * Returns the number of pages available for the most recent invocation of find()
     * find() must be invoked first
     *
     * @return total page
     */
    int getTotalPage();

    /**
     * Returns the number of results for the most recent invocation of find()
     * find() must be invoked first
     *
     * @return total result, or -1 on error
     */
    int getResultCount();

    /**
     * @return Maximum number of Item(s) retrievable per page
     */
    int getItemsPerPage();
}
