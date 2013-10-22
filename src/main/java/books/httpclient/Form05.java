package books.httpclient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;

public class Form05 {

	public static void test() throws ClientProtocolException, IOException{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("firstname", "value1"));
		formparams.add(new BasicNameValuePair("lastname", "value2"));
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "UTF-8");
		HttpPost httppost = new HttpPost("http://localhost:8080/examples/servlets/servlet/RequestParamExample");
		httppost.setEntity(entity);
		
		System.out.println(httppost.toString());
		File file = new File("D:\\ce.txt");
		FileOutputStream fout = new FileOutputStream(file);
		httppost.getEntity().writeTo(fout);
		HttpResponse response = httpclient.execute(httppost);
		response.getEntity().writeTo(fout);

	}
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		test();

	}

}
