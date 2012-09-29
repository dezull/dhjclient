package com.blogspot.aptgetmoo.dhjclient.company;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.blogspot.aptgetmoo.dhjclient.parser.Webpage;

public class CompanyPage extends Webpage {

    private final static String DEFAULT_BASE_URL =
            "http://www.halal.gov.my/ehalal/directory/slm_viewdetail.php";

    private URL mBaseUrl;

    private String mCompanyCode;

    /**
     * This assigns the default Jakim's web URL. In such a case the URL is invalid, put a new URL
     * in #CompanyPage(String pBaseUrl), or the Web is no longer in service :(
     * @see #CompanyPage(String)
     * @see #getBaseUrl()
     */
    public CompanyPage() {
        try {
            mBaseUrl = new URL(DEFAULT_BASE_URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param pBaseUrl Base URL for Jakim's search result page
     * @throws MalformedURLException If given URL is invalid
     */
    public CompanyPage(String pBaseUrl) throws MalformedURLException {
        mBaseUrl = new URL(pBaseUrl);
    }

    @Override
    public String getBaseUrl() {
        return mBaseUrl.toString();
    }

    @Override
    public String getUrl() {
        return getBaseUrl() + "?comp_code=" + mCompanyCode;
    }

    @Override
    public String getParseable() throws IOException {
        final String companyCode = getCompanyCode();

        if (!companyCode.matches("[a-zA-Z0-9-]{4,}")) {
            throw new IOException("Invalid company code");
        }

        if (companyCode == null || companyCode.trim().isEmpty()) {
            throw new IOException("getCompanyCode() returns null or is empty");
        }

        return super.getParseable();
    }

    public String getCompanyCode() {
        return mCompanyCode;
    }

    public void setCompanyCode(String pCompanyCode) {
        mCompanyCode = pCompanyCode;
    }

}
