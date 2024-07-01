package com.book.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.book.api.helper.FileHelper;

@Controller
public class FileController {

	@Autowired
	private FileHelper fileHelper;

	@PostMapping("/upload-file")
	public ResponseEntity<String> fileUpload(@RequestParam("file") MultipartFile file) {
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getContentType());
		System.out.println(file.getSize());
		System.out.println(file.getName());

		try {
			if (file.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("file is Entpy, Try Again!");
			}
			if (!file.getContentType().equals("image/png")) {
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("require image in png format only");
			}
			boolean bool = fileHelper.uploadFile(file);
			if (bool) {
				return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/")
						.path(file.getOriginalFilename()).toUriString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File not uploaded.. Try Again!!");
	}
}
