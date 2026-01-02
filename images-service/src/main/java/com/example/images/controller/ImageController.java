package com.example.images.controller;

import com.example.images.model.Image;
import com.example.images.service.BlobService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/images")
public class ImageController {
    private final BlobService blobService;

    public ImageController(BlobService blobService) {
        this.blobService = blobService;
    }

    @PostMapping("/upload")
    public Image upload(@RequestParam("file") MultipartFile file) throws IOException {
        String url = blobService.upload("images",
                                        file.getOriginalFilename(),
                                        file.getInputStream(),
                                        file.getSize());

        Image img = new Image();
        img.setBlobUrl(url);                // now works thanks to Lombok
        img.setContentType(file.getContentType());
        return img;
    }
}
