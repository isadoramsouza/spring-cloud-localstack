package com.example.springcloudlocalstack.controller;

import com.example.springcloudlocalstack.dto.FileDTO;
import com.example.springcloudlocalstack.exception.GetFileException;
import com.example.springcloudlocalstack.exception.SaveFileException;
import com.example.springcloudlocalstack.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileS3Controller {

    private final FileService fileService;

    @Autowired
    public FileS3Controller(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    public ResponseEntity<FileDTO> saveFile() throws SaveFileException, IOException {
        FileDTO file = fileService.saveFile();
        return ResponseEntity.ok(file);
    }

    @GetMapping
    public ResponseEntity<List<FileDTO>> getFiles(@RequestParam("fileName") String fileName) throws GetFileException {
        List<FileDTO> files = fileService.getFiles(fileName);
        if(files.isEmpty()){
            return new ResponseEntity<>(files, HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(files);
    }

    @GetMapping("/{file}")
    public ResponseEntity<FileDTO> getFileContent(@PathVariable("file") String fileName) throws GetFileException {
        FileDTO file = fileService.getFileWithContent(fileName);
        return ResponseEntity.ok(file);
    }
}