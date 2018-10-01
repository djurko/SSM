package com.example.SSM;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.TimerTask;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

@Entity
public class MyTask extends TimerTask {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String message;
	
	public MyTask() {}
	
	public MyTask(String message) {
		this.message = message;
	}
	
	public void run() {
		try {
			sendToSlack(message);
		} catch (UnsupportedOperationException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private String sendToSlack(String message) throws ClientProtocolException, IOException, UnsupportedOperationException {
		final String SERVLETURL = "https://hooks.slack.com/services/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";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(SERVLETURL);
		StringEntity params = new StringEntity("{\"text\":\"" + message + "\"}");
		httpPost.addHeader("content-type", "application/json");
		httpPost.setEntity(params);
		CloseableHttpResponse response = httpclient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		String responseBody = "";
		try {
			responseBody = getStringFromInputStream(response.getEntity().getContent());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// Processing here
		try {
			EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseBody;
	}

	private String getStringFromInputStream(InputStream content) throws IOException {
		StringWriter writer = new StringWriter();
		IOUtils.copy(content, writer, "ISO-8859-1");
		String result = writer.toString();
		return result;
	}

}
