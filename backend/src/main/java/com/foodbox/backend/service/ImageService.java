package com.foodbox.backend.service;

import com.foodbox.backend.entity.Image;
import com.foodbox.backend.repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    ImageRepository imageRepository;

    ImageService(ImageRepository imageRepository){
        this.imageRepository = imageRepository;
    }
    public Image getImageById(int imageId) {
        return this.imageRepository.getImageById(imageId);
    }

    public Image createImage(String name,byte[] data)  {
        
    Image image = new Image(name,data);
    return this.imageRepository.save(image);
    }

    public void updateImage(int imageId, byte[] image) {

         imageRepository.updateImageDataById(image, imageId);    }

    public void deleteImage(int imageId) {
        imageRepository.deleteById(imageId);
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }
}
