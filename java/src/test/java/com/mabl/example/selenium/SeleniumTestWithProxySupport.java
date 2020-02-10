package com.mabl.example.selenium;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Collections;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.http.HttpClient;
import org.openqa.selenium.remote.internal.OkHttpClient;

import com.google.common.base.Preconditions;

import okhttp3.ConnectionPool;

/**
 * Base class for all Selenium tests that initializes/configures the web driver
 * to connect through the Selenium importer proxy running in the mabl CLI.
 */
abstract public class SeleniumTestWithProxySupport {
    private static final String WEB_DRIVER_URL = "http://localhost:9515";
    private static final String MABL_CLI_PROXY = "localhost:8889";
    protected RemoteWebDriver driver;

    @Before
    public void setUp() {
        driver = createRemoteDriver(WEB_DRIVER_URL, MABL_CLI_PROXY);
    }

    @After
    public void tearDown() {
        Optional.ofNullable(driver).ifPresent(RemoteWebDriver::quit);
    }

    private static RemoteWebDriver createRemoteDriver(final String url, final String proxy) {
        return createRemoteDriver(url, Optional.ofNullable(proxy));
    }

    private static RemoteWebDriver createRemoteDriver(final String url, final Optional<String> proxy) {
        try {
            final Capabilities capabilities = new MutableCapabilities();
            final HttpClient.Factory httpClientFactory = new HttpClientFactory(proxy);
            final HttpCommandExecutor commandExecutor = new HttpCommandExecutor(Collections.emptyMap(), new URL(url), httpClientFactory);
            return new RemoteWebDriver(commandExecutor, capabilities);
        } catch (Exception e) {
            throw new RuntimeException("Error initializing remote web driver", e);
        }
    }

    private static class HttpClientFactory implements HttpClient.Factory {
        private final ConnectionPool pool = new ConnectionPool();
        private final Optional<String> proxy;

        public HttpClientFactory(final Optional<String> proxy) {
            this.proxy = Preconditions.checkNotNull(proxy);
        }

        @Override
        public org.openqa.selenium.remote.http.HttpClient.Builder builder() {
            return new Builder();
        }

        @Override
        public void cleanupIdleClients() {
            pool.evictAll();
        }

        private class Builder extends HttpClient.Builder {
            public Builder() {
                HttpClientFactory.this.proxy.map(value -> value.split(":"))
                    .map(parts -> InetSocketAddress.createUnresolved(parts[0], Integer.parseInt(parts[1])))
                    .map(address -> new Proxy(Proxy.Type.HTTP, address))
                    .ifPresent(this::proxy);
            }
            @Override
            public HttpClient createClient(final URL url) {
                okhttp3.OkHttpClient.Builder client = new okhttp3.OkHttpClient.Builder()
                    .connectionPool(pool)
                    .followRedirects(true)
                    .followSslRedirects(true)
                    .readTimeout(readTimeout.toMillis(), MILLISECONDS)
                    .connectTimeout(connectionTimeout.toMillis(), MILLISECONDS);

                Optional.ofNullable(proxy).ifPresent(client::proxy);

                return new OkHttpClient(client.build(), url);
            }
        }
    }
}
