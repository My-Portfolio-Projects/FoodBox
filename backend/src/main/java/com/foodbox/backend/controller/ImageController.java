package com.foodbox.backend.controller;

import com.foodbox.backend.entity.Image;
import com.foodbox.backend.service.ImageService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@CrossOrigin(value="http://localhost:4200")
@RequestMapping(value="/api")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(value="/image" , produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public ResponseEntity<List<Image>> getAllImages(){return ResponseEntity.ok(imageService.getAllImages());}



    @GetMapping(value="/image/{imageId}",produces = MediaType.IMAGE_JPEG_VALUE)
    public InputStreamResource getImageById(@PathVariable int imageId) throws IOException {

        if(imageService.getImageById(imageId).getImageData() == null) {throw new IllegalArgumentException("Image should not be null but was null\n");
        }

        byte[] image = imageService.getImageById(imageId).getImageData();

        InputStream is = new ByteArrayInputStream(image);
        BufferedImage bi = ImageIO.read(is);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bi, "JPEG", outputStream);

        return new InputStreamResource(new ByteArrayInputStream(outputStream.toByteArray()));
    }

    @PostMapping(value = "/image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public ResponseEntity<Integer> createImage(@RequestPart("image") MultipartFile data) throws IOException {
        Image image = imageService.createImage( data.getBytes());
        return ResponseEntity.ok(image.getImageId());
        }



    @PutMapping(value="/image/{imageId}",consumes =MediaType.MULTIPART_FORM_DATA_VALUE )
    public void updateImage(@PathVariable int imageId, @RequestBody MultipartFile image) throws IOException {
        byte[] imageData = image.getBytes() ;
        imageService.updateImage(imageId, imageData);
    }

    @DeleteMapping("/image/{imageId}")
    public void deleteImage(@PathVariable int imageId) {
        imageService.deleteImage(imageId);
    }

}
