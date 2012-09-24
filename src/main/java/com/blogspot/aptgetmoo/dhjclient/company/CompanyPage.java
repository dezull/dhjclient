package com.blogspot.aptgetmoo.dhjclient.company;

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
    public CompanyPage(String pCompanyCode) {
        try {
            mBaseUrl = new URL(DEFAULT_BASE_URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        mCompanyCode = pCompanyCode;
    }

    /**
     * @param pBaseUrl Base URL for Jakim's search result page
     * @throws MalformedURLException If given URL is invalid
     */
    public CompanyPage(String pBaseUrl, String pCompanyCode) throws MalformedURLException {
        mBaseUrl = new URL(pBaseUrl);
        mCompanyCode = pCompanyCode;
    }

    @Override
    public String getBaseUrl() {
        return mBaseUrl.toString();
    }

    @Override
    public String getUrl() {
        return getBaseUrl() + "?comp_code=" + mCompanyCode;
    }

    public String getCompanyCode() {
        return mCompanyCode;
    }

}
