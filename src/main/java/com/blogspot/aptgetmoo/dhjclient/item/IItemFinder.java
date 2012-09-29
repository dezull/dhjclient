package com.blogspot.aptgetmoo.dhjclient.item;

import java.io.IOException;
import java.util.ArrayList;

import com.blogspot.aptgetmoo.dhjclient.parser.IFinder;

/**
 * Finds items (eg: product, premises, etc) from Jakim's Halal Web search result
 *
 * @author Dzul Nizam
 */
public interface IItemFinder extends IFinder {

    /**
     * Get Item(s) by keyword, type & page. The maximum number of Item(s) per page retrieved depend highly on the Jakim's Web :(
     *
     * @param 	keyword 	Keyword
     * @param 	type		Item type
     * @param 	page		Page to retrieve
     * @return	List of Item(s) (a maximum of certain row, as returned by the Web page)
     * @throws 	IOException
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
