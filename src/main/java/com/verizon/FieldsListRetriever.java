package com.verizon;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FieldsListRetriever {
	
	public static List<String> getFieldsList(String filePath){
		List<String> ls= new ArrayList<String>();
		StringBuilder contentBuilder = new StringBuilder();
		try {
		    BufferedReader in = new BufferedReader(new FileReader(filePath));
		    String str;
		    while ((str = in.readLine()) != null) {
		        contentBuilder.append(str);
		    }
		    in.close();
		} catch (IOException e) {
		}
		String content = contentBuilder.toString();
		
		String value="<link rel=\"stylesheet\" href=\"//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css\">"
				+"\n"+	"<script  src=\"resource.js\"></script>"
				+"\n"+	"<script src=\"//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js\"></script>"
				+"\n"+	 "<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>"
				+"\n"+	 "<script src=\"//code.jquery.com/jquery-1.10.2.js\"></script>"
				+"\n"+	  "<script src=\"//code.jquery.com/ui/1.11.4/jquery-ui.js\"></script>"
				+"\n"+	     "<link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\">"
				+"\n"+	    "<script src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\"></script>"
				+"\n"+	    "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>"
				+"\n"+		"<link rel=\"stylesheet\" href=\"http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/themes/smoothness/jquery-ui.css\" type=\"text/css\" media=\"screen\" />"		
				+"\n"+	    "<script type=\"text/javascript\" src=\"http://code.jquery.com/jquery-1.8.3.min.js\"></script>"
				+"\n"+	   "	    <link type=\"text/css\" href=\"jquery-ui-chatbox.css\" rel=\"stylesheet\" />"
				+"\n"+	    "<script type=\"text/javascript\" src=\"http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js\"></script>"
				+"\n"+	    "<script  src=\"handle.js\"></script>";
		
		
	
		
		String fileout="";
		String[] div=content.split(">");
		for(int i=0;i<div.length;i++)
		{ 	//System.out.println(div[i]);
		  div[i].replaceAll("\\s+","");
			if(div[i].contains("input") && !(div[i].toLowerCase().contains("submit")))
			{
				if(div[i].contains("id=\""))
				{
					System.out.println(div[i].substring(div[i].toLowerCase().indexOf("id=\"")+4,div[i].indexOf("\"",(div[i].toLowerCase().indexOf("id=\"")+4)) ));
				ls.add(div[i].substring(div[i].toLowerCase().indexOf("id=\"")+4,div[i].indexOf("\"",(div[i].toLowerCase().indexOf("id=\"")+4)) ));
				}
			else if(div[i].contains("name=\""))
				{
					String name=div[i].substring(div[i].toLowerCase().indexOf("name=\"")+6,div[i].indexOf("\"",(div[i].toLowerCase().indexOf("name=\"")+6)) );
				div[i]=div[i]+" "+"id="+"\"id_"+name+"\" ";
				ls.add("id_"+name);
				System.out.println("id_"+name);
				}
			}
		if(i>0)
		{
			fileout=fileout+"> "+"\n"+div[i];
		}
		else
		{
		fileout=""+div[i];
		}
		
	}
		fileout=fileout+">";
		if(fileout.toLowerCase().contains("<head>") && !(fileout.toLowerCase().contains("handle.js")))
		{
			System.out.println("fileout    "+fileout);
			System.out.println(fileout.substring(0,fileout.indexOf("<head>")+7));
			System.out.println("-----------------------------------------");
			System.out.println(fileout.substring(fileout.indexOf("<head>")+6));
			fileout = fileout.substring(0,fileout.indexOf("<head>")+7)+"\n" + value +"\n"+ fileout.substring(fileout.indexOf("<head>")+6);
		}
		
		System.out.println("file out is"+fileout);
		try {
			FileWriter fout=new FileWriter(new File(filePath));
			fout.append(fileout);
			fout.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ls);
		return ls;
		}
	
		
	}


