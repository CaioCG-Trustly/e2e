package org.e2e.utils;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.e2e.config.execution.EnvBaseUrl;
import org.e2e.config.execution.ExecutionConfig;
import org.openqa.selenium.Cookie;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.webdriver;

public class AdminConsoleUtils {

    static final String adminConsoleUser = "admin";
    static final String adminConsolePassword = "superadmin";
    static final String localPort = ":9002";

    public static void loginAdminConsole() {
        String body = "username=" + adminConsoleUser + "&password=" + adminConsolePassword + "&remember=true&timezone=America/Sao_Paulo&originalUrl=/transactions";
        String url = handleBaseUrl() + "/home/login";
        String authLogin = "";

        try {
            Headers headers = post(body, url, authLogin).priorResponse().priorResponse().headers();
            String auth = headers.get("Set-Cookie").replace("auth=", "").split(";")[0];
            webdriver().driver().getWebDriver().manage().addCookie(new Cookie("auth", auth));
        } catch (IOException e) {
            throw new RuntimeException("Unable to login at Admin Console", e);
        }
    }

    private static String handleBaseUrl() {
        var baseUrl = EnvBaseUrl.valueOf(ExecutionConfig.targetEnvironment).baseUrl;
        var port = EnvBaseUrl.Local.equals(
            EnvBaseUrl.valueOf(ExecutionConfig.targetEnvironment)
        ) ? localPort : "";

        return baseUrl + port;
    }

    private static Response post(String body, String url, String auth) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody requestBody = RequestBody.create(mediaType, body);


        String cookieValue = "tz=America/Sao_Paulo; auth=" + auth;

        Request request = new Request.Builder()
            .url(url)
            .method("POST", requestBody)
            .addHeader("Accept-Encoding", "gzip, deflate, br")
            .addHeader("Connection", "keep-alive")
            .addHeader("Content-Type", "application/x-www-form-urlencoded")
            .addHeader("Cookie", cookieValue)
            .build();

        Response response = client.newCall(request).execute();

        return response;
    }
}
