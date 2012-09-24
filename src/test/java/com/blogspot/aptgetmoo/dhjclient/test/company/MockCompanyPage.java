package com.blogspot.aptgetmoo.dhjclient.test.company;

import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.blogspot.aptgetmoo.dhjclient.company.CompanyPage;

public class MockCompanyPage extends CompanyPage {

	public MockCompanyPage(String pCompanyCode) {
		super(pCompanyCode);
	}

	@Override
	public String getParseable() {
		InputStream is = getClass().getResourceAsStream("/" + getCompanyCode() + ".html");

        try {
            return new Scanner(is).useDelimiter("\\A").next();
        } catch (NoSuchElementException e) {
            return "";
        }
	}

}
