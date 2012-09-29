package com.blogspot.aptgetmoo.dhjclient.test.company;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Ignore;
import org.junit.Test;

import com.blogspot.aptgetmoo.dhjclient.company.CompanyPage;

public class CompanyPageTest {

    private static final String COMP_CODE_1 = "COMP-CODE-1234";

    @Test public void constructor() throws URISyntaxException {
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
        } catch (URISyntaxException e) {
            fail("Unexpected URISyntaxException");
        }
    }

    @Test public void constructorWithInvalidUrl() {
        final String invalidUrl = ":invalidUrl";

        try {
            new CompanyPage(invalidUrl);
            fail("Expected URISyntaxException");
        } catch (URISyntaxException e) {}
    }

    @Test public void getBaseUrlDefault() throws URISyntaxException {
        CompanyPage page = new CompanyPage();

        try {
            page.setCompanyCode(COMP_CODE_1);
        } catch (URISyntaxException e) {
            fail("Unexpected URISyntaxException");
        }

        assertNotNull(page.getBaseUrl());
    }

    @Test public void getBaseUrlCustom() {
        final String validUrl = "http://www.google.com";
        CompanyPage page;

        try {
            page = new CompanyPage(validUrl);
            assertEquals(validUrl, page.getBaseUrl());
        } catch (URISyntaxException e) {
            fail("Unexpected URISyntaxException");
        }
    }

    @Test public void getUrlDefault() throws URISyntaxException {
        CompanyPage page = new CompanyPage();
        page.setCompanyCode(COMP_CODE_1);
        final String baseUrl = page.getBaseUrl();
        final String url = page.getUrl();

        assertEquals(url, baseUrl + "?comp_code=" + COMP_CODE_1);
    }

    @Test public void getUrlDefaultCustom() throws URISyntaxException {
        final String validUrl = "http://www.google.com";
        CompanyPage page = new CompanyPage(validUrl);
        page.setCompanyCode(COMP_CODE_1);

        final String baseUrl = page.getBaseUrl();
        final String url = page.getUrl();

        assertEquals(url, baseUrl + "?comp_code=" + COMP_CODE_1);
    }

    @Ignore("Potentially long running HTTP request")
    @Test public void getParseableValidCompCode() throws URISyntaxException {
        CompanyPage page = new CompanyPage();;
        page.setCompanyCode(COMP_CODE_1);

        try {
            page.getParseable();
        } catch (IOException e) {
            fail("Unexpected " + IOException.class.getName());
        }
    }

    @Test public void getParseableInvalidCompCode() throws URISyntaxException {
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
