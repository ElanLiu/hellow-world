package books.httpclient;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.message.BasicHttpResponse;

public class Header03 {

	/**
	 * @param args
	 * HttpClient提供获取，添加，移除和枚举头部信息的方法。
	 */
	public static void main(String[] args) {
		HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
		response.addHeader("Set-Cookie", "c1=a; path=/; domain=localhost");
		response.addHeader("Set-Cookie","c2=b; path=\"/\" ,c3=c; domain=\"localhost\" ");
		
		Header h1 = response.getFirstHeader("Set-Cookie");
		Header h2 = response.getLastHeader("Set-Cookie");
		Header[] hs = response.getAllHeaders();
		
		System.out.println(h1);
		System.out.println(h2);
		System.out.println(hs.length);
		System.out.println(response.toString());

	}

}
