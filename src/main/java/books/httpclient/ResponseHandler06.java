package books.httpclient;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @author yu.liuyly
 * 
 *         控制响应的最简便和最方便的方式是使用ResponseHandler接口。
 * 
 *         这个放完完全减轻了用户关于连接管理的担心。当使用ResponseHandler时，HttpClient将会自动关注
 * 
 *         并保证释放连接到连接管理器中去，而不管请求执行是否成功或引发了异常。
 * 
 */
public class ResponseHandler06 {

	public static void test() throws ClientProtocolException, IOException {
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet("http://localhost:8080/examples/servlets/servlet/RequestParamExample");
		
		ResponseHandler<byte[]> handler = new ResponseHandler<byte[]>() {
			@Override
			public byte[] handleResponse(HttpResponse response)
					throws ClientProtocolException, IOException {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					return EntityUtils.toByteArray(entity);
				} else {
					return null;
				}
			}
		};
		byte[] response = httpclient.execute(httpget, handler);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
