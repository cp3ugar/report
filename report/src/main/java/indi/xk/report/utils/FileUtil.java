package indi.xk.report.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件工具类
 * 
 * @author fyj
 * 
 */
public class FileUtil {

	/**
	 * 文件上传
	 * 
	 * @param multipartFile
	 * @param saveRealPath
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static String uploadFile(MultipartFile multipartFile,
                                    String saveRealPath, String imageSavePath) throws IllegalStateException, IOException {

		String filePath = null;
        String relativeFilePath = null;
		
		if (multipartFile != null) {
			// 获取文件的后缀
			String suffix = multipartFile.getOriginalFilename().substring(
					multipartFile.getOriginalFilename().lastIndexOf("."));
			// 再通过UUID加后缀生成新的文件名
			String logoImageName = UUID.randomUUID().toString() + suffix;
			// 拼成完整的路径加文件
			filePath = saveRealPath + File.separator + logoImageName;
			relativeFilePath = imageSavePath + logoImageName;
			File file = new File(filePath);
			multipartFile.transferTo(file);

		} 

		return relativeFilePath;
	}
	
	/**
	 * 拷贝文件.
	 * @param sourceFile
	 * @param targetFile
	 * @throws Exception
	 */
	public static void copyFile(String sourceFile, String targetFile) throws Exception {
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			File fileDir = new File(sourceFile);
		    if (!fileDir.isDirectory()) {
		    	fileDir.mkdir();
		    }
			
		    in = new FileInputStream(sourceFile);
		    out = new FileOutputStream(targetFile);
		    byte[] buffer = new byte[1024];
		    int readed = -1; 
		    while((readed = in.read(buffer)) > 0) 
		        out.write(buffer, 0, readed); 
		}
		catch (IOException e) {
			throw new RuntimeException(e.toString());
		}
		finally {
			if (in != null) {
				in.close(); 
			}
			if (out != null) {
				out.close();
			}
		}
	}
}
