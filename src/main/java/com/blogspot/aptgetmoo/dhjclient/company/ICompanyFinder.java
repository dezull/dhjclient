package com.blogspot.aptgetmoo.dhjclient.company;

import java.io.IOException;

public interface ICompanyFinder {

	/**
	 * Finds company by code
	 *
	 * @param pCompCode Company code
	 * @return Company or null if the company with the given code is not found
	 * @throws IOException On any errors when retrieving the company
	 */
	Company find(String pCompCode) throws IOException;

}
