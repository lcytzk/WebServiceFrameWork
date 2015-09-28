package org.yolk.common.restful;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/**
 * @author Liang Chenye
 * @version $Id: RestfulClient, v 0.1 2015/8/11 10:30
 */

public class RestfulClient {

    private static final Logger log = LoggerFactory.getLogger(RestfulClient.class);

    private HttpClient client;

    public RestfulClient() {
        client = HttpClients.createDefault();
    }

    public <T> T sendRequest(Request request, Class<T> clazz) {
        try {
            HttpResponse response = client.execute(request.toHttpPost());
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            return convert(sb.toString(), clazz);
        } catch (UnsupportedEncodingException e) {
            log.error("请求转换出错", e);
        } catch (JsonSyntaxException e) {
            log.error("返回结果转换出错", e);
        } catch (IOException e) {
            log.error("发送消息出错", e);
        }
        return null;
    }

    private <T> T convert(String responseContent, Class<T> clazz) throws JsonSyntaxException {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(responseContent, clazz);
    }
}
