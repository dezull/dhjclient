package com.blogspot.aptgetmoo.dhjclient.company;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

public class Company {

	private CompanyPage mPage;

    private final String mCompCode;

    private String mCompany;

    private String mWeb;

    private String mAddress;

    private String mPhone;

    private String mFax;

    private String mEmail;

    private String mJakimOfficer;

    private String mJakimRefNo;

	public Company(CompanyPage pPage) {
		mPage = pPage;
		mCompCode = mPage.getCompanyCode();
	}

	public Company parse() {
		Document doc;

		try {
			doc = Jsoup.parse(mPage.getParseable());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		if (doc != null) {
            parseRows(doc.select("table table tr"));
        }

		return this;
	}

    public String getCompanyCode() {
        return mCompCode;
    }

    public String getCompanyName() {
        return mCompany;
    }

    public String getAddress() {
        return mAddress;
    }

    public String getPhone() {
        return mPhone;
    }

    public String getFax() {
        return mFax;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getWeb() {
        return mWeb;
    }

    public String getJakimRefNo() {
        return mJakimRefNo;
    }

    public String getJakimOfficer() {
        return mJakimOfficer;
    }

    private void parseRows(Elements rows) {
        if (rows.size() < 9) return;

        Elements temp;
        if ((temp = rows.get(1).select("td")).size() > 0) mCompany = temp.get(1).text();
        if ((temp = rows.get(2).select("td")).size() > 0) {
            mAddress = temp.get(1).html();
            mAddress = formatAddress(mAddress);
        }
        if ((temp = rows.get(4).select("td")).size() > 0) mPhone = temp.get(1).text();
        if ((temp = rows.get(5).select("td")).size() > 0) mFax = temp.get(1).text();
        if ((temp = rows.get(6).select("td")).size() > 0) mEmail = temp.get(1).text();
        if ((temp = rows.get(7).select("td")).size() > 0) mWeb = temp.get(1).text();
        if ((temp = rows.get(8).select("td")).size() > 0) mJakimRefNo = temp.get(1).text();
        if ((temp = rows.get(9).select("td")).size() > 0) mJakimOfficer = temp.get(1).text();
    }

    private String formatAddress(String pAddress) {
        final Whitelist whiteList = new Whitelist();

        pAddress = Jsoup.clean(pAddress, whiteList.addTags("br"));
        pAddress = pAddress.replace("<br />", "")
                .replace("<br>", "")
                .replaceAll("(?m)(?:^|\\G) ", "")
                .replaceAll("r\r?\n", "\n");

        return pAddress;
    }

}
