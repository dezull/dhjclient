package com.blogspot.aptgetmoo.dhjclient.company;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;

import com.blogspot.aptgetmoo.dhjclient.parser.Webpage;

public class CompanyPage extends Webpage {

    private final static String DEFAULT_BASE_URL =
            "http://www.halal.gov.my/ehalal/directory/slm_viewdetail.php";

    private URI mBaseUrl;

    private URI mUrl;

    private String mCompanyCode;

    /**
     * This assigns the default Jakim's web URL. In such a case the URL is invalid, put a new URL
     * in #CompanyPage(String pBaseUrl), or the Web is no longer in service :(
     * @see #CompanyPage(String)
     * @see #getBaseUrl()
     */
    public CompanyPage() throws URISyntaxException {
        mBaseUrl = new URI(DEFAULT_BASE_URL);
    }

    /**
     * @param pBaseUrl Base URL for Jakim's search result page
     * @throws URISyntaxException If given URL is invalid
     */
    public CompanyPage(String pBaseUrl) throws URISyntaxException {
        mBaseUrl = new URI(pBaseUrl);
    }

    @Override
    public String getBaseUrl() {
        return mBaseUrl.toString();
    }

    @Override
    public String getUrl() {
        return mUrl.toString();
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

    /**
     * @return Company code
     */
    public String getCompanyCode() {
        return mCompanyCode;
    }

    /**
     * @param pCompanyCode Company code
     * @throws URISyntaxException
     */
    public void setCompanyCode(String pCompanyCode) throws URISyntaxException {
        mCompanyCode = pCompanyCode;

        URIBuilder builder = new URIBuilder(mBaseUrl);
        builder.addParameter("comp_code", mCompanyCode);

        mUrl = builder.build();
    }

}
