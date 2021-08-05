/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exam.herbert.util;

/**
 *
 * @author Herbert
 */
import static com.exam.herbert.constants.Constants.RESPONSE;
import static com.exam.herbert.constants.Constants.STATUS;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author herbert
 */
@Service
public class ResponseRestClientUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResponseRestClientUtil.class);

	protected static final int CONNECTION_TIMEOUT = 6000;
	protected static final int CONNECTION_REQUEST_TIMEOUT = 6000;
	protected static final int SOCKET_TIMEOUT = 6000;
	private static final RequestConfig requestConfig = requestConfig();

	private static RequestConfig requestConfig() {
		return RequestConfig.custom().setConnectTimeout(CONNECTION_TIMEOUT)
				.setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
	}

	public ObjectNode responsePost(HttpPost request) {
		try (CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(requestConfig)
				.build()) {
			try (CloseableHttpResponse response = httpclient.execute(request)) {
				return returnObjectNode(response);
			}
		}catch (IOException ex) {
			LOGGER.error(ex.getMessage());
		}
		return null;
	}

	public ObjectNode responseGet(HttpGet request) {
		try (CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(requestConfig)
				.build()) {
			try (CloseableHttpResponse response = httpclient.execute(request)) {
				return returnObjectNode(response);
			}
		}catch (IOException ex) {
			LOGGER.error(ex.getMessage());
		}
		return null;
	}

	private static ObjectNode returnObjectNode(CloseableHttpResponse response) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode json = mapper.createObjectNode();
		int statusCode = response.getStatusLine().getStatusCode();
		String body = EntityUtils.toString(response.getEntity());
		LOGGER.info("Code Status: {}", response.getStatusLine().getReasonPhrase());
		LOGGER.info("Http Status: {}", statusCode);
		LOGGER.info("Body Response: {}", body);
		LOGGER.info("Response: {}", response);
		if (statusCode == 200 || StringUtils.startsWith(String.valueOf(statusCode), "4")) {
			JsonNode responseNew = mapper.readTree(body);
			json.put(STATUS, statusCode);
			json.set(RESPONSE, responseNew);
		} else {
			HttpStatus status = HttpStatus.resolve(statusCode);
			if (status == null) {
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
			if (body != null) {
				throw new ResponseStatusException(status, body);
			}
		}
		return json;
	}

}
