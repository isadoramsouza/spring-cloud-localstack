package com.example.springcloudlocalstack.service;

import com.amazonaws.services.s3.AmazonS3;
import com.example.springcloudlocalstack.configuration.bucketS3.BucketConfiguration;
import com.example.springcloudlocalstack.dto.FileDTO;
import com.example.springcloudlocalstack.exception.GetFileException;
import com.example.springcloudlocalstack.exception.SaveFileException;
import io.awspring.cloud.core.io.s3.PathMatchingSimpleStorageResourcePatternResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FileService {

    private BucketConfiguration bucketConfiguration;

    private final ResourceLoader resourceLoader;
    private ResourcePatternResolver resourcePatternResolver;
    private final String LOCATION_PATTERN = "s3://%s/%s";
    private final String FILE_TYPE = ".txt";
    private final String FILE_NAME_PREFIX= "prefix-";


    @Autowired
    public FileService(ResourceLoader resourceLoader, ResourcePatternResolver resourcePatternResolver, BucketConfiguration bucketConfiguration) {
        this.resourceLoader = resourceLoader;
        this.resourcePatternResolver = resourcePatternResolver;
        this.bucketConfiguration = bucketConfiguration;
    }

    @Autowired
    public void setupResolver(ApplicationContext applicationContext, AmazonS3 amazonS3) {
        this.resourcePatternResolver = new PathMatchingSimpleStorageResourcePatternResolver(amazonS3,
                applicationContext);
    }

    public FileDTO saveFile() throws SaveFileException, IOException {
        Path fileToSave = createFile();
        InputStream from = Files.newInputStream(fileToSave);
        String to = fileToSave.toFile().getName();
        Resource resource = getResource(to);
        WritableResource writableResource = (WritableResource) resource;
        try (OutputStream outputStream = writableResource.getOutputStream()) {
            from.transferTo(outputStream);
            return FileDTO.getInstance(to, null);
        } catch (Exception ex) {
            throw new SaveFileException(ex.getMessage(), ex);
        }
    }

    private Path createFile() throws SaveFileException {
        try {
            Path file = Files.createTempFile(FILE_NAME_PREFIX, FILE_TYPE);
            Files.writeString(file, "File content: " + UUID.randomUUID() + "",
                    StandardCharsets.ISO_8859_1, StandardOpenOption.APPEND);
            return file;
        } catch (Exception ex) {
            throw new SaveFileException("Error creating file", ex);
        }
    }

    public List<FileDTO> getFiles(String fileName) throws GetFileException {
        Resource[] resources;
        List<FileDTO> fileList = new ArrayList<>();
        try {
            resources = resourcePatternResolver.getResources(String.format(LOCATION_PATTERN+"*.*", bucketConfiguration.getDirectory(), fileName));
        } catch (Exception ex) {
            throw new GetFileException(ex.getMessage(), ex);
        }
        List<Resource> resourceList = Arrays.stream(resources).toList();
        resourceList.forEach(resource -> fileList.add(FileDTO.getInstance(resource.getFilename(), null)));
        return fileList;
    }


    public FileDTO getFileWithContent(String file) throws GetFileException {
        try {
            Resource resource = getResource(file);
            String content = new BufferedReader(
                    new InputStreamReader(resource.getInputStream(), StandardCharsets.ISO_8859_1))
                    .lines()
                    .collect(Collectors.joining("\n"));
            return FileDTO.getInstance( resource.getFilename(), content);
        } catch (Exception ex) {
            throw new GetFileException(ex.getMessage(), ex);
        }
    }

    private Resource getResource(String file) {
        return resourceLoader.getResource(String.format(LOCATION_PATTERN, bucketConfiguration.getDirectory(), file));
    }
}
