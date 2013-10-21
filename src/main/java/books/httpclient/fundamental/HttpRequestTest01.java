package books.httpclient.fundamental;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class HttpRequestTest01 {

	public static void httpRequest() throws ClientProtocolException, IOException, URISyntaxException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		//也可以这样初始化，有什么区别？
		//DefaultHttpClient httpclient = new DefaultHttpClient();

		/*
		 * 这里关注http请求，其他的代码先不关心。
		 * 构造http请求可以：
		 * 1 直接通过构造函数，将请求地址的string给出
		 * 2 通过URI来拼装
		 * 3 将参数独立出来
		 */
		
		//第一种方法
		HttpGet httpGet1 = new HttpGet(
				"http://www.google.com/ig/api?hl=zh-cn&weather=94043");
		
		//第二种方法。这里使用了deprecated的方法，后续再修改为建议的方法
		URI uri = URIUtils.createURI("http", "www.google.com", -1, "/ig/api", "hl=zh-cn&weather=94043", null);
		HttpGet httpGet2 = new HttpGet(uri);
		
		//第三种方法
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("hl", "zh-cn"));
		params.add(new BasicNameValuePair("weather", "94043"));
		@SuppressWarnings("deprecation")
		URI uri2 = URIUtils.createURI("http", "www.google.com", -1, "/ig/api", URLEncodedUtils.format(params, "UTF-8"), null);
		HttpGet httpGet3 = new HttpGet(uri2);
		
		
		System.out.println(httpGet1.toString());
		System.out.println(httpGet2.toString());
		System.out.println(httpGet3.toString());
		
		
		CloseableHttpResponse response = httpclient.execute(httpGet1);

		HttpEntity entity = response.getEntity();

		if (entity != null) {
			InputStream instream = entity.getContent();
			int l;
			byte[] tmp = new byte[2048];
			while ((l = instream.read(tmp)) != -1) {
			}
		}
	}

	public static void main(String[] args) throws ClientProtocolException, IOException, URISyntaxException {
		httpRequest();

	}

}
