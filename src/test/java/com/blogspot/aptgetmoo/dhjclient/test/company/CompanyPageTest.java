package com.blogspot.aptgetmoo.dhjclient.test.company;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Ignore;
import org.junit.Test;

import com.blogspot.aptgetmoo.dhjclient.company.CompanyPage;

public class CompanyPageTest {

    private static final String COMP_CODE_1 = "COMP-CODE-1234";

    @Test public void constructor() {
        CompanyPage page = new CompanyPage();
        page.setCompanyCode(COMP_CODE_1);

        assertNotNull(page);
    }

    @Test public void constructorWithValidUrl() {
        final String validUrl = "http://www.google.com";
        CompanyPage page;

        try {
            page = new CompanyPage(validUrl);
            page.setCompanyCode(COMP_CODE_1);
            assertNotNull(page);
        } catch (MalformedURLException e) {
            fail("Unexpected MalformedURLException");
        }
    }

    @Test public void constructorWithInvalidUrl() {
        final String invalidUrl = "invalidUrl";

        try {
            new CompanyPage(invalidUrl);
            fail("Expected MalformedURLException");
        } catch (MalformedURLException e) {}
    }

    @Test public void getBaseUrlDefault() {
        CompanyPage page = new CompanyPage();
        page.setCompanyCode(COMP_CODE_1);

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

    @Test public void getUrlDefault() {
        CompanyPage page = new CompanyPage();
        page.setCompanyCode(COMP_CODE_1);
        final String baseUrl = page.getBaseUrl();
        final String url = page.getUrl();

        assertFalse(baseUrl.compareTo(url) == 0);
        assertTrue(url.startsWith(baseUrl));
    }

    @Test public void getUrlDefaultCustom() {
        final String validUrl = "http://www.google.com";
        CompanyPage page;

        try {
            page = new CompanyPage(validUrl);
            page.setCompanyCode(COMP_CODE_1);
            final String baseUrl = page.getBaseUrl();
            final String url = page.getUrl();

            assertEquals(validUrl, baseUrl);
            assertFalse(baseUrl.compareTo(url) == 0);
            assertTrue(url.startsWith(baseUrl));
        } catch (MalformedURLException e) {
            fail("Unexpected MalformedURLException");
        }
    }

    @Ignore("Potentially long running HTTP request")
    @Test public void getParseableValidCompCode() {
        CompanyPage page;

        try {
            page = new CompanyPage();
            page.setCompanyCode(COMP_CODE_1);
            page.getParseable();
        } catch (IOException e) {
            fail("Unexpected " + IOException.class.getName());
        }
    }

    @Test public void getParseableInvalidCompCode() {
        final String invalidCompCode = "COMP=CODE=1234";
        CompanyPage page;

        try {
            page = new CompanyPage();
            page.setCompanyCode(invalidCompCode);
            page.getParseable();
            fail("Expected " + IOException.class.getName());
        } catch (IOException e) {
            assertTrue(e.getMessage().compareTo("Invalid company code") == 0);
        }
    }

}
