package practice;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Test1 {

	public static Map<String, String> lowwerCaseMap(Map<String, String> header){
		Map<String, String> header_new= new HashMap();
		for(Entry<String, String> en : header.entrySet()){
			header_new.put(en.getKey().toLowerCase(), en.getValue());
		}
		return header_new;
	}
	
	@SafeVarargs
	public static Map<String, String> lowwerCaseMap(Map<String, String>...maps){
		Map<String, String> header_new = new HashMap<String, String>();
		for(Map<String, String> map : maps){
			for(Entry<String, String> entry : map.entrySet()){
				header_new.put(entry.getKey().toLowerCase(), entry.getValue());
			}
		}
		return header_new;
	}
	
	public static void main(String[] args) {
		Map<String, String> header = new HashMap();
		header.put("a", "1");
		header.put("B", "2");
		
		Map<String, String> header2 = new HashMap();
		header2.put("A", "111");
		header2.put("C", "2");
		header2.put("bbbb", "zcvzv");
		
		Map<String, String> header3 = new HashMap();
		header3.put("Aheader3", "111");
		header3.put("Cheader3", "2");
		header3.put("bbbheader3b", "zcvzv");
		
		
		System.out.print(lowwerCaseMap(header, header2, header3));

	}

}
