package com.example.springcloudlocalstack.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.File;

public class FileDTO {

    private String fileName;
    private String fileContent;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public static FileDTO getInstance(String fileName, String content) {
        FileDTO file = new FileDTO();
        file.setFileName(fileName);
        file.setFileContent(content);
        return file;
    }

}
