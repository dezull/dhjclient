package com.blogspot.aptgetmoo.dhjclient.test.company;

import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.blogspot.aptgetmoo.dhjclient.company.CompanyPage;

public class MockCompanyPage extends CompanyPage {

    public MockCompanyPage() {
        super();
    }

    @Override
    public String getParseable() {
    	Class<? extends MockCompanyPage> cls = getClass();
        InputStream is = cls.getResourceAsStream("/" + getCompanyCode() + ".html");

        if (is == null) {
        	is = cls.getResourceAsStream("/COMP-not-found.html");
        }

        try {
            return new Scanner(is).useDelimiter("\\A").next();
        } catch (NoSuchElementException e) {
            return "";
        }
    }

}
