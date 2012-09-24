package com.blogspot.aptgetmoo.dhjclient.test.company;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.blogspot.aptgetmoo.dhjclient.company.Company;

@RunWith(Parameterized.class)
public class CompanyTest {

	private Company mCompany;

	private String mCompanyCode;

	private String mCompanyName;

	private Company mParsedCompany;

	private String mAddress;

	private String mPhone;

	private String mFax;

	private String mEmail;

	private String mWeb;

	private Object mJakimRefNo;

	private Object mJakimRefOfficer;

	public CompanyTest(
			String pCompanyCode,
			String pCompanyName,
			String pAddress,
			String pPhone,
			String pFax,
			String pEmail,
			String pWeb,
			String pJakimRefNo,
			String pJakimRefOfficer) {

		mCompanyCode = pCompanyCode;
		mCompanyName = pCompanyName;
		mAddress = pAddress;
		mPhone = pPhone;
		mFax = pFax;
		mEmail = pEmail;
		mWeb = pWeb;
		mJakimRefNo = pJakimRefNo;
		mJakimRefOfficer = pJakimRefOfficer;
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
					}
				};
		return Arrays.asList(data);
	 }

	@Before public void setUp() throws Exception {
		mCompany = new Company(new MockCompanyPage(mCompanyCode));
		mParsedCompany = mCompany.parse();
	}

	@After public void tearDown() throws Exception {
	}

	@Test public void constructor() {
		assertNotNull(mCompany);
	}

	@Test public void parse() {
		assertSame(mParsedCompany, mCompany);
	}

	@Test public void testGetCompanyCode() {
		assertEquals(mCompanyCode, mParsedCompany.getCompanyCode());
	}

	@Test public void testGetCompanyName() {
		assertEquals(mCompanyName, mParsedCompany.getCompanyName());
	}

	@Test public void testGetAddress() {
		assertEquals(mAddress, mParsedCompany.getAddress());
	}

	@Test public void testGetPhone() {
		assertEquals(mPhone, mParsedCompany.getPhone());
	}

	@Test public void testGetFax() {
		assertEquals(mFax, mParsedCompany.getFax());
	}

	@Test public void testGetEmail() {
		assertEquals(mEmail, mParsedCompany.getEmail());
	}

	@Test public void testGetWeb() {
		assertEquals(mWeb, mParsedCompany.getWeb());
	}

	@Test public void testGetJakimRefNo() {
		assertEquals(mJakimRefNo, mParsedCompany.getJakimRefNo());
	}

	@Test public void testGetJakimOfficer() {
		assertEquals(mJakimRefOfficer, mParsedCompany.getJakimOfficer());
	}

}
