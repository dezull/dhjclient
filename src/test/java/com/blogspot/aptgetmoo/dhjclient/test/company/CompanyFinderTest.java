package com.blogspot.aptgetmoo.dhjclient.test.company;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.blogspot.aptgetmoo.dhjclient.company.Company;
import com.blogspot.aptgetmoo.dhjclient.company.CompanyFinder;
import com.blogspot.aptgetmoo.dhjclient.company.ICompanyFinder;

@RunWith(Parameterized.class)
public class CompanyFinderTest {

	private static final String NULL_COMPANY = "COMP-DOES-NOT-EXISTS";

    private ICompanyFinder mFinder;

    private Company mParsedCompany;

    private String mComCode;

    private String mComName;

    private String mComAddress;

    private String mComPhone;

    private String mComFax;

    private String mComEmail;

    private String mComWeb;

    private Object mComJakimRefNo;

    private Object mComJakimRefOfficer;

    public CompanyFinderTest(
            String pCompanyCode,
            String pCompanyName,
            String pAddress,
            String pPhone,
            String pFax,
            String pEmail,
            String pWeb,
            String pJakimRefNo,
            String pJakimRefOfficer) {

        mComCode = pCompanyCode;
        mComName = pCompanyName;
        mComAddress = pAddress;
        mComPhone = pPhone;
        mComFax = pFax;
        mComEmail = pEmail;
        mComWeb = pWeb;
        mComJakimRefNo = pJakimRefNo;
        mComJakimRefOfficer = pJakimRefOfficer;
    }

     @Parameters
     public static Collection<Object[]> data() {
        Object[][] data = new Object[][] {
                    {
                        "COMP-20070116-084125",
                        "KFC (SABAH) SDN BHD",
                        "Wisma KFC Sabah\n43A, Jalan Karamunging\n88000 Kota Kinabalu\nSabah\nMALAYSIA",
                        "60-88-249166",
                        "60-88-210494",
                        "kfcsabah@tm.net.my",
                        "-",
                        "JAKIM/(S)/(22.00)/492/2/ 021-04/2012",
                        "ZALELAWATI HASIN"
                    },
                    {
                        "COMP-20050601-091339",
                        "KFC (PENINSULAR MALAYSIA) SDN BHD",
                        "Level 14 - 17, Wisma KFC\n17, Jalan Sultan Ismail\n50250 Kuala Lumpur",
                        "03-20263388",
                        "03-20265696",
                        "-",
                        "-",
                        "JAKIM/(S)/(22.00)/492/2/ 2 001-07/2004 (SK.1)",
                        "Mohd Roslan Mohd Saludin"
                    },
                    {
                    	NULL_COMPANY,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                    }
                };
        return Arrays.asList(data);
     }

    @Before public void setUp() throws Exception {
        mFinder = new CompanyFinder(new MockCompanyPage());
        mParsedCompany = mFinder.find(mComCode);
    }

    @After public void tearDown() throws Exception {
    }

    @Test public void constructor() {
        assertNotNull(mFinder);
    }

    @Test public void find() {
    	if (mComCode.compareTo(NULL_COMPANY) != 0) {
	        assertNotNull(mParsedCompany);
	        assertEquals(mComCode, mParsedCompany.code);
	        assertEquals(mComName, mParsedCompany.name);
	        assertEquals(mComAddress, mParsedCompany.address);
	        assertEquals(mComPhone, mParsedCompany.phone);
	        assertEquals(mComFax, mParsedCompany.fax);
	        assertEquals(mComEmail, mParsedCompany.email);
	        assertEquals(mComWeb, mParsedCompany.web);
	        assertEquals(mComJakimRefNo, mParsedCompany.jakimRefNo);
	        assertEquals(mComJakimRefOfficer, mParsedCompany.jakimOfficer);
    	} else {
    		assertNull(mParsedCompany);
    	}
    }

}
