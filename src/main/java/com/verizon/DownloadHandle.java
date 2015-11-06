package com.verizon;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadHandle
 */
@WebServlet("/DownloadHandle")
public class DownloadHandle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadHandle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private static final int BYTES_DOWNLOAD = 1024;
    public  InputStream downloadPage(String ctx,  HttpServletResponse response) throws IOException{
    	
		response.setHeader("Content-Disposition",
	                     "attachment;filename=InitForm.html");
			
		InputStream is=new FileInputStream(ctx+File.separator+"uploadFiles"+File.separator+"InitForm.html");
		return is;
		
    }
public  InputStream downloadResource(String ctx,  HttpServletResponse response) throws IOException{
    	
		response.setHeader("Content-Disposition",
	                     "attachment;filename=resource.js");
			
		InputStream is=new FileInputStream(ctx+File.separator+"uploadFiles"+File.separator+"resource.js");
		return is;
		
    }
public  InputStream downloadHandle(String ctx,  HttpServletResponse response) throws IOException{
	
	response.setHeader("Content-Disposition",
                     "attachment;filename=handle.js");
		
	InputStream is=new FileInputStream(ctx+File.separator+"uploadFiles"+File.separator+"handle.js");
	return is;
	
}
public  InputStream downloadCss(String ctx,  HttpServletResponse response) throws IOException{
	
	response.setHeader("Content-Disposition",
                     "attachment;filename=jquery-ui-chatbox.css");
		
	InputStream is=new FileInputStream(ctx+File.separator+"uploadFiles"+File.separator+"jquery-ui-chatbox.css");
	return is;
	
}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//String SOURCE_FOLDER=ctx+File.separator+"uploadFiles";
		//String OUTPUT_ZIP_FILE=ctx+File.separator+"test.zip";
		//Zip.createZip(SOURCE_FOLDER, OUTPUT_ZIP_FILE);
		String ctx = getServletContext().getRealPath("/");
		System.out.println("ctx is"+ctx);
		response.setContentType("application/octet-stream");
			
		//InputStream is=downloadPage(ctx, response);
		//dwnldFile(is, response);
		
		//is=downloadCss(ctx, response);
		//dwnldFile(is, response);
		
		InputStream is=downloadHandle(ctx, response);
		dwnldFile(is, response);
		
		//is=downloadResource(ctx, response);
		//dwnldFile(is, response);
			
	
	}
	

	/**
	 * @throws IOException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
		public void dwnldFile(InputStream is,HttpServletResponse response ) throws IOException{
			System.out.println(is);	
			int read=0;
			byte[] bytes = new byte[BYTES_DOWNLOAD];
			OutputStream os = response.getOutputStream();
				
			while((read = is.read(bytes))!= -1){
				os.write(bytes, 0, read);
			}
			os.flush();
			os.close();	
		   }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
