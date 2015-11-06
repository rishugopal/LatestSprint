package com.verizon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet("/UploadServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR = "uploadFiles";

	public UploadServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*String appPath = request.getServletContext().getRealPath("/");
		System.out.println("ap path is:" + appPath);
		String savePath = appPath + File.separator + SAVE_DIR;

		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		String fileName = null;
		Collection<Part> parts = request.getParts();
		for (Part part : parts) {
			fileName = extractFileName(part);
			// part.write(savePath +File.separator+ fileName);
			part.write(fileName);
		}
		System.out.println(fileName);

		String filePath = ReadWriteTextFileJDK7.mainReadWrite(fileName, savePath);
		HttpSession session = request.getSession();
		session.setAttribute("filePath", filePath);
		request.setAttribute("message", "Upload has been done successfully!");
		getServletContext().getRequestDispatcher("/FieldsListServlet").forward(request, response);*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.setContentType("text/html;charset=UTF-8");

		// Create path components to save the file
		String appPath = request.getServletContext().getRealPath("/");
		String savePath = appPath +  "uploadFiles";
		final String path = savePath;
		final Part filePart = request.getPart("file");
		final String fileName = extractFileName(filePart);
		
	    
	    
	    
		System.out.println("path :"+path);
		System.out.println("filename: "+fileName);
		OutputStream out = null;
		InputStream filecontent = null;
		// final PrintWriter writer = response.getWriter();

		try {
			out = new FileOutputStream(new File(path + File.separator + fileName));
			filecontent = filePart.getInputStream();

			int read = 0;
			final byte[] bytes = new byte[1024];

			while ((read = filecontent.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			// writer.println("New file " + fileName + " created at " + path);
			HttpSession session = request.getSession();
			String filePath = path + File.separator + fileName;
			session.setAttribute("filePath", filePath);
			session.setAttribute("modifiedPageName", fileName);
			request.setAttribute("message", "Upload has been done successfully!");
			getServletContext().getRequestDispatcher("/FieldsListServlet").forward(request, response);

		} catch (FileNotFoundException fne) {
			// writer.println("You either did not specify a file to upload or
			// are "
			// + "trying to upload a file to a protected or nonexistent "
			// + "location.");
			// writer.println("<br/> ERROR: " + fne.getMessage());
			fne.printStackTrace();

		} finally {
			if (out != null) {
				out.close();
			}
			if (filecontent != null) {
				filecontent.close();
			}
			// if (writer != null) {
			// writer.close();
		}
	}

	private String extractFileName(Part part) {
		final String partHeader = part.getHeader("content-disposition");
	    
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}

}
