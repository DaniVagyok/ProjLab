package projlab;

import java.util.ArrayList;
import java.util.List;

//logger oszátly behúzások és a kiírások kezelésére.
public class Logger {
	public static List<LoggerRecord> stack = new ArrayList<LoggerRecord>();
	public static int identation = 0;
	public static void Push(LoggerRecord rc){
		String temp = "";
		for(int i = 0; i < Logger.identation; i++){ temp += "  "; }
		Logger.identation++;
		stack.add(rc);
		System.out.println(temp + "-> " + rc.className + '.' + rc.functionName + '(' + rc.params + ')');
	}
	public static void Pop(){
		Logger.identation--;
		String temp = "";
		for(int i = 0; i < Logger.identation; i++){ temp += "  "; }
				
		
		LoggerRecord rc = stack.get(stack.size() - 1);
		stack.remove(stack.size() - 1);
		System.out.println(temp + "<- " + "return " + rc.className + '.' + rc.functionName + '(' + rc.params + ')');
	}
	
}

class LoggerRecord{
	public String className;
	public String functionName;
	public String params;
	
	public LoggerRecord(String c, String f, String p){
		this.className = c;
		this.functionName = f;
		this.params = p;
	}
}