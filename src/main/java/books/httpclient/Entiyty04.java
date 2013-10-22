package books.httpclient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Entiyty04 {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		HttpGet httpGet = new HttpGet("http://sishuok.com/forum/blogCategory/showByCategory.html?categories_id=101&user_id=2");

		httpGet.addHeader("Accept-Encoding", "gzip");
		HttpResponse response = httpclient.execute(httpGet);
		
		for(Header header: response.getAllHeaders()){
			System.out.println(header.toString());
		}
		
		
		HttpEntity entity = response.getEntity();

		if (entity != null) {
			//打印实体头
			if(entity.getContentType() != null){
				System.out.println(entity.getContentType().toString());
			}
			
			if(entity.getContentLength() >0){
				System.out.println(entity.getContentLength());
			}else{
				System.out.println("===");
			}
			
			if(entity.getContentEncoding() != null){
				System.out.println(entity.getContentEncoding().toString());
			}else{
				System.out.println("===");
			}
			

			
			//如何解压。。。
			Header ceheader = entity.getContentEncoding();
			if (ceheader != null) {
			for (HeaderElement element : ceheader.getElements()) {
			if (element.getName().equalsIgnoreCase("gzip")) {
			response.setEntity(new GzipDecompressingEntity(response.getEntity()));}}

			InputStream instream = entity.getContent();
			int l;
			byte[] buffer = new byte[2048];
			File file = new File("D:\\ce.txt");
			FileOutputStream fout = new FileOutputStream(file);
			while ((l = instream.read(buffer)) != -1) {
				fout.write(buffer);
			}
		}

	}

}
}
