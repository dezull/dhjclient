package com.blogspot.aptgetmoo.dhjclient.company;

import java.io.IOException;

import com.blogspot.aptgetmoo.dhjclient.parser.IFinder;

/**
 * Finds Company instance
 *
 * @author Dzul Nizam
 */
public interface ICompanyFinder extends IFinder {

    /**
     * Finds company by code
     *
     * @param 	pCompCode	Company code
     * @return 	Company
     * @throws 	IOException	On any errors when retrieving the company
     */
    Company find(String pCompCode) throws IOException;

}
