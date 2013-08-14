package com.kurento.demo.junit;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class PlayerIT extends BaseArquillianTst {

	@Test
	public void testPlay() throws ClientProtocolException, IOException {
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet("http://localhost:8180/content-demo/test");
		HttpResponse response = client.execute(httpGet);
		HttpEntity resEntity = response.getEntity();
		Header contentType = resEntity.getContentType();
		EntityUtils.consume(resEntity);

		log.debug("contentType " + contentType.getValue());
		Assert.assertEquals(
				"Content-Type in response header must be video/webm",
				"video/webm", contentType.getValue());
	}
}