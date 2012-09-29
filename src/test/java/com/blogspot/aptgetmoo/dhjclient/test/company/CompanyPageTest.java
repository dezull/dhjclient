package com.blogspot.aptgetmoo.dhjclient.test.company;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Ignore;
import org.junit.Test;

import com.blogspot.aptgetmoo.dhjclient.company.CompanyPage;

public class CompanyPageTest {

    private static final String COMP_CODE_1 = "COMP-CODE-1234";

    @Test public void constructor() throws MalformedURLException {
        CompanyPage page = new CompanyPage();
        page.setCompanyCode(COMP_CODE_1);

        assertNotNull(page);
    }

    @Test public void constructorWithValidUrl() {
        final String validUrl = "http://www.google.com";
        CompanyPage page;

        try {
            page = new CompanyPage(validUrl);
            assertNotNull(page);
        } catch (MalformedURLException e) {
            fail("Unexpected MalformedURLException");
        }
    }

    @Test public void constructorWithInvalidUrl() {
        final String invalidUrl = ":invalidUrl";

        try {
            new CompanyPage(invalidUrl);
            fail("Expected MalformedURLException");
        } catch (MalformedURLException e) {}
    }

    @Test public void getBaseUrlDefault() throws MalformedURLException {
        CompanyPage page = new CompanyPage();

        try {
            page.setCompanyCode(COMP_CODE_1);
        } catch (MalformedURLException e) {
            fail("Unexpected MalformedURLException");
        }

        assertNotNull(page.getBaseUrl());
    }

    @Test public void getBaseUrlCustom() {
        final String validUrl = "http://www.google.com";
        CompanyPage page;

        try {
            page = new CompanyPage(validUrl);
            assertEquals(validUrl, page.getBaseUrl());
        } catch (MalformedURLException e) {
            fail("Unexpected MalformedURLException");
        }
    }

    @Test public void getUrlDefault() throws MalformedURLException {
        CompanyPage page = new CompanyPage();
        page.setCompanyCode(COMP_CODE_1);
        final String baseUrl = page.getBaseUrl();
        final String url = page.getUrl();

        assertEquals(url, baseUrl + "?comp_code=" + COMP_CODE_1);
    }

    @Test public void getUrlDefaultCustom() throws MalformedURLException {
        final String validUrl = "http://www.google.com";
        CompanyPage page = new CompanyPage(validUrl);
        page.setCompanyCode(COMP_CODE_1);

        final String baseUrl = page.getBaseUrl();
        final String url = page.getUrl();

        assertEquals(url, baseUrl + "?comp_code=" + COMP_CODE_1);
    }

    @Ignore("Potentially long running HTTP request")
    @Test public void getParseableValidCompCode() throws MalformedURLException {
        CompanyPage page = new CompanyPage();;
        page.setCompanyCode(COMP_CODE_1);

        try {
            page.getParseable();
        } catch (IOException e) {
            fail("Unexpected " + IOException.class.getName());
        }
    }

    @Test public void getParseableInvalidCompCode() throws MalformedURLException {
        final String invalidCompCode = "COMP=CODE=1234";
        CompanyPage page = new CompanyPage();
        page.setCompanyCode(invalidCompCode);

        try {
            page.getParseable();
            fail("Expected " + IOException.class.getName());
        } catch (IOException e) {
            assertEquals("Invalid company code", e.getMessage());
        }
    }

}
