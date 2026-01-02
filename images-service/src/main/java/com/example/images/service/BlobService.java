package com.example.images.service;

import com.azure.storage.blob.*;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class BlobService {
    private final BlobServiceClient client;

    public BlobService() {
        this.client = new BlobServiceClientBuilder()
            .connectionString(System.getenv("AZURE_STORAGE_CONNECTION_STRING"))
            .buildClient();
    }

    public String upload(String containerName, String fileName, InputStream data, long size) {
        BlobContainerClient container = client.getBlobContainerClient(containerName);
        if (!container.exists()) container.create();
        BlobClient blob = container.getBlobClient(fileName);
        blob.upload(data, size, true);
        return blob.getBlobUrl();
    }
}