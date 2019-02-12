package com.revolut.task.rest.controller;

import com.google.gson.Gson;
import com.revolut.task.rest.Messages;
import com.revolut.task.rest.datasource.Storage;
import com.revolut.task.rest.model.Account;
import com.revolut.task.rest.service.impl.AccountServiceImpl;
import org.junit.*;
import spark.Spark;
import spark.utils.IOUtils;
import spark.utils.StringUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.junit.Assert.fail;

public class AccountControllerIntegrationTest {

    private final Map<Long, Account> TESTING_STORAGE = Storage.ACCOUNT_STORAGE.getInstance();

    @BeforeClass
    public static void setUp() throws Exception {
        Spark.port(8080);
        new AccountController(new AccountServiceImpl());
    }

    @After
    public void after() throws Exception {
        TESTING_STORAGE.clear();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        Spark.stop();
    }

    @Test
    public void create() throws Exception {
        String requestBody = "{\"name\": \"visa\", \"balance\": 23223}";
        TestResponse response = request("POST", "/account/create", requestBody);
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.status);
        Assert.assertEquals("Account has been successfully created. ID: 0.", response.body);
    }

    @Test
    public void get() throws Exception {
        Account account = new Account(0L, "visa", new BigDecimal(2000));
        TESTING_STORAGE.put(account.getId(), account);
        TestResponse response = request("GET", "/account/0", "");
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.status);
        Assert.assertEquals(new Gson().toJson(account), response.body);
    }

    @Test
    public void transfer() throws Exception {
        Account from = new Account(0L, "visa", new BigDecimal(2000));
        Account to = new Account(1L, "mastercard", new BigDecimal(2000));
        TESTING_STORAGE.put(from.getId(), from);
        TESTING_STORAGE.put(to.getId(), to);
        String reqiestBody = "{\"fromAccount\": \"0\", \"toAccount\": \"1\", \"amount\": 2000}";
        TestResponse response = request("POST", "/account/transfer", reqiestBody);
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.status);
        Assert.assertEquals(String.format(Messages.SUCCESS_TRANSFER.getMsg(), "0", "1", "2000"), response.body);
        Assert.assertEquals(new BigDecimal(0), TESTING_STORAGE.get(0L).getBalance());
        Assert.assertEquals(new BigDecimal(4000), TESTING_STORAGE.get(1L).getBalance());
    }

    private TestResponse request(String method, String path, String reqBody) {
        try {
            URL url = new URL("http://localhost:8080" + path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            if (StringUtils.isNotEmpty(reqBody)) {
                OutputStream os = connection.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
                osw.write(reqBody);
                osw.flush();
                osw.close();
            }
            connection.connect();
            String body = IOUtils.toString(connection.getInputStream());
            return new TestResponse(connection.getResponseCode(), body);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Sending request failed: " + e.getMessage());
            return null;
        }
    }

    private static class TestResponse {

        final String body;
        final int status;

        TestResponse(int status, String body) {
            this.status = status;
            this.body = body;
        }
    }

}