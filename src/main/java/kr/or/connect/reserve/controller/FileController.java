package kr.or.connect.reserve.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kr.or.connect.reserve.service.FileService;

@Controller
public class FileController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	// 이미지를 c드라이브 경로에서 가져옴
	@GetMapping("/img/{directory}/{imagename}.{extension}")
	public void download(HttpServletResponse response, @PathVariable(name = "directory") String directory,
			@PathVariable(name = "imagename") String imagename, @PathVariable(name = "extension") String extension) {

		String fileName = imagename;
		String filePath = FileService.pathDir + "img/" + directory + "/" + imagename + "." + extension;

		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");

		// 파일 다운로드
		try (FileInputStream fis = new FileInputStream(filePath);
				BufferedInputStream bis = new BufferedInputStream(fis);
				OutputStream out = response.getOutputStream();
				BufferedOutputStream bos = new BufferedOutputStream(out);) {
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while ((readCount = bis.read(buffer)) != -1) {
				bos.write(buffer, 0, readCount);
			}
		} catch (Exception e) {
			logger.info("이미지 파일 랜더링 error");
		}
		
		
	}
}
