package com.blogspot.aptgetmoo.dhjclient.parser;

import java.io.IOException;

public interface IParseable {

	/**
	 * @return Parse-able String
	 * @throws IOException On any errors while retrieving the parse-able String
	 */
	String getParseable() throws IOException;

}
