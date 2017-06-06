package com.szymongrochowiak.androidstarterpack;

import io.appflate.restmock.RESTMockServer;

/**
 * @author Szymon Grochowiak
 */
public class TestApplication extends StarterPackApplication {

    @Override
    public String getApiEndpoint() {
        return RESTMockServer.getUrl();
    }
}