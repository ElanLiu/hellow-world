package books.httpclient.fundamental;

import java.io.IOException;

import javax.net.ssl.SSLHandshakeException;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;

public class Retry07 {

	public void test() {

	}

	public static void main(String[] args) throws ClientProtocolException, IOException {
		//CloseableHttpClient httpclient = HttpClients.createDefault();
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(
				"http://localhost:8081/examples/servlets/servlet/RequestParamExample");

		HttpRequestRetryHandler retry = new HttpRequestRetryHandler() {

			@Override
			public boolean retryRequest(IOException exception,
					int executionCount, HttpContext context) {
				if (executionCount >= 5) {
					// 如果超过最大重试次数，那么就不要继续了
					return false;
				}
				if (exception instanceof NoHttpResponseException) {
					// 如果服务器丢掉了连接，那么就重试
					return true;
				}
				if (exception instanceof SSLHandshakeException) {
					// 不要重试SSL握手异常
					return false;
				}

				HttpRequest request = (HttpRequest) context
						.getAttribute(ExecutionContext.HTTP_REQUEST);
				boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
				if (idempotent) {
					// 如果请求被认为是幂等的，那么就重试
					System.out.println("=====");
					return true;
				}
				return false;
			}
		};
		httpclient.setHttpRequestRetryHandler(retry);
		httpclient.execute(httpGet);

	}

}
