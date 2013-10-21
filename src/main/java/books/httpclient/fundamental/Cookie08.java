package books.httpclient.fundamental;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;

public class Cookie08 {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(
				"http://my.yunos.com/sms.htm?spm=0.0.0.0.yxpYrD#/index/page/1/id/sms_3/number/13735802411");

		String cookie="tmp_ticket=e4836ee7a006c0c4a3a92a52460f153c0071f86e8eb2c1568628c081f06ed685; CNZZDATA30080709=cnzz_eid%3D695297168-1381125022-http%253A%252F%252Fmy.yunos.com%26ntime%3D1381125022%26cnzz_a%3D5%26sin%3Dnone%26ltime%3D1381124974274; _cnzz_CV=";
		httpGet.addHeader(new BasicHeader("Cookie",cookie));
		HttpResponse response = httpclient.execute(httpGet);
		System.out.print(response.getStatusLine().toString());

		HttpEntity entity = response.getEntity();
		File file = new File("D:\\ce.txt");
		FileOutputStream fout = new FileOutputStream(file);
		entity.writeTo(fout);
		
		for(Header header:response.getAllHeaders()){
			System.out.println(header.toString());
		}
		
		
		
	}

}
