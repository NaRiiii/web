package com.study.servlet;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadTestController {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "/upload/result", method = RequestMethod.POST)
	public String uploadResult(@RequestParam(name = "boFile", required = false) MultipartFile boFile,
			@RequestParam(name = "boTitle", required = false) String boTitle,
			@RequestParam(name = "boWriter", required = false) String boWriter) throws Exception {
		
		logger.debug("boFile={}", boFile);
		logger.debug("boTitle={}", boTitle);
		logger.debug("boWriter={}", boWriter);
		
		if (boFile != null && !boFile.isEmpty()) {
			logger.debug("getName={}", boFile.getName());
			logger.debug("getOriginalFilename={}", boFile.getOriginalFilename());
			logger.debug("getSize={}", boFile.getSize());
			logger.debug("getContentType={}", boFile.getContentType());
			
			//폴더가 존재하지 않으면 오류, FileUtil
			//boFile.transferTo(new File("/home/pc34/upload/abc.zip"));
			FileUtils.copyInputStreamToFile(boFile.getInputStream(), new File("/home/pc34/upload/2020/09/15/def.zip"));
		}
		// 테스트이므로 입력폼으로 리다이렉트
		return "redirect:form";
	}
}
