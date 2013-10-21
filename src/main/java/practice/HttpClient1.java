package practice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClient1 {

	public static void test() throws Exception{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://yunapi.tmall.com/api/activity/dianxinHeyue/userState");
		//Header header = new Header();
		httpGet.addHeader("Cookie", "_tb_token_=lyNx4AUmNua5; l=elanliuyu::1381211661397::11; tracknick=elanliuyu; qK0ZzCfGjqH%2FyxA%3D&lg2=VFC%2FuZ9ayeYq2g%3D%3D; lgc=elanliuyu; lastgetwwmsg=MTM4MTIwOTg3Mw%3D%3D; cookie1=VvaFCWNjf%2FIQS1%2F5oZ9hLLr5qxUv60KDTIqftC8Aki4%3D; cookie2=86ed43eca4a190fe6250ce73121116ab; cookie17=VAFbTpCIsMQn; tlut=UoLU6gCiE1iDPA%3D%3D; t=3540bbd9eb918cf7f78c38c7c66554f2; unb=704959781; _nk_=elanliuyu; mt=ci=18_1; uc1=lltime=1381208938&cookie14=UoLU6gCiE1iDOw%3D%3D&existShop=false&cookie16=VFC%2FuZ9az08KUQ56dCrZDlbNdA%3D%3D&cookie21=UtASsssme%2BBq&tag=3&cookie15=W5iHLLyFOGW7aA%3D%3D");
		
		
		URI uri = URIUtils.createURI("http", "www.google.com", -1, "/ig/api", "hl=zh-cn&weather=94043", null);
		HttpGet httpGet2 = new HttpGet(uri);
		CloseableHttpResponse response1 = httpclient.execute(httpGet);
		// The underlying HTTP connection is still held by the response object
		// to allow the response content to be streamed directly from the network socket.
		// In order to ensure correct deallocation of system resources
		// the user MUST either fully consume the response content  or abort request
		// execution by calling CloseableHttpResponse#close().

		try {
			
		    System.out.println(response1.getStatusLine());
		    System.out.println(httpGet.toString());
		    
		    HttpEntity entity1 = response1.getEntity();
		    String strResult = EntityUtils.toString(entity1);
		    File file = new File("D:\\ce.txt");
		    FileOutputStream fout = new FileOutputStream(file);
//		    entity1.writeTo(fout);
		    System.out.println("=======");
		    System.out.println(strResult);
		    // do something useful with the response body
		    // and ensure it is fully consumed
		    EntityUtils.consume(entity1);
		} finally {
		    response1.close();
		}

		/*HttpPost httpPost = new HttpPost("http://targethost/login");
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		nvps.add(new BasicNameValuePair("username", "vip"));
		nvps.add(new BasicNameValuePair("password", "secret"));
		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		CloseableHttpResponse response2 = httpclient.execute(httpPost);

		try {
		    System.out.println(response2.getStatusLine());
		    HttpEntity entity2 = response2.getEntity();
		    // do something useful with the response body
		    // and ensure it is fully consumed
		    EntityUtils.consume(entity2);
		} finally {
		    response2.close();
		}*/
	}
	
	
	
	public static void main(String[] args) throws Exception {
		test();

	}

}
