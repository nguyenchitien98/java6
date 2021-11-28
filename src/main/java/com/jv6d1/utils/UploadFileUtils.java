package com.jv6d1.utils;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileUtils {
	public  File handleUploadFile(
			MultipartFile uploadFile
	) {
		String folderPath= "D:\\jee-2020-12\\assimentJV6\\src\\main\\resources\\static\\assets\\imagesuser";
		File myUploadFolder = new File(folderPath);
		
		//Kiem tra thu muc luu tru file co ton tai hay ko thi tao moi thu muc
		if(!myUploadFolder.exists()) {
			myUploadFolder.mkdirs();
		}
		File savedFile = null;
		try {
			// luu file vao thu muc da chon
			String uuid= UUID.randomUUID().toString();
			String fileName= uuid+ "_" + uploadFile.getOriginalFilename();
			
			savedFile = new File(myUploadFolder,uploadFile.getOriginalFilename());
			uploadFile.transferTo(savedFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return savedFile;
	}
}
