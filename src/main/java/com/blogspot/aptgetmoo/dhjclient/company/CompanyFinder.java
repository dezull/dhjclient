package com.blogspot.aptgetmoo.dhjclient.company;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import com.blogspot.aptgetmoo.dhjclient.parser.Webpage;

public class CompanyFinder implements ICompanyFinder {

	private Webpage mPage;

    public CompanyFinder(CompanyPage pPage) {
        mPage = pPage;
    }

	@Override
	public Company find(String pCompCode) throws IOException {
    	((CompanyPage) mPage).setCompanyCode(pCompCode);
        Document doc;

        try {
            doc = Jsoup.parse(mPage.getParseable());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Company company = new Company(pCompCode);
        if (doc != null) {
        	company = parseRows(company, doc.select("table table tr"));
        }

        return company;
    }

    private Company parseRows(Company pCom, Elements pRows) {
        if (pRows.size() < 9) return null;

        Elements temp;
        if ((temp = pRows.get(1).select("td")).size() > 0) pCom.name = temp.get(1).text();
        if (pCom.name.trim().isEmpty()) {
        	// That's it, no need further processing, the company doesn't exists
        	return null;
        }

        if ((temp = pRows.get(2).select("td")).size() > 0) pCom.address = formatAddress(temp.get(1).html());
        if ((temp = pRows.get(4).select("td")).size() > 0) pCom.phone = temp.get(1).text();
        if ((temp = pRows.get(5).select("td")).size() > 0) pCom.fax = temp.get(1).text();
        if ((temp = pRows.get(6).select("td")).size() > 0) pCom.email = temp.get(1).text();
        if ((temp = pRows.get(7).select("td")).size() > 0) pCom.web = temp.get(1).text();
        if ((temp = pRows.get(8).select("td")).size() > 0) pCom.jakimRefNo = temp.get(1).text();
        if ((temp = pRows.get(9).select("td")).size() > 0) pCom.jakimOfficer = temp.get(1).text();

        return pCom;
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
