package com.blogspot.aptgetmoo.dhjclient.test;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.blogspot.aptgetmoo.dhjclient.ResultPage;

public class MockResultPage extends ResultPage {

	public MockResultPage() {
		super();
	}

	/**
	 * @param pUrl URL will be ignored.
	 * @throws MalformedURLException
	 * @see #fetchHtml()
	 */
	public MockResultPage(String pUrl) throws MalformedURLException {
		super(pUrl);
	}

	/**
	 * Should return the first page of 4-page result, containing 20 items (of 69).
	 * keyword, page & type of HTTP GET parameters are not handled (ignored).
	 *
	 * @return Mocked HTML String, similar to what Jakim's web should to return
	 */
	@Override
	public String fetchHtml() {
		InputStream is = getClass().getResourceAsStream("/kfc_1.html");

		try {
	        return new Scanner(is).useDelimiter("\\A").next();
	    } catch (NoSuchElementException e) {
	        return "";
	    }
	}

}
