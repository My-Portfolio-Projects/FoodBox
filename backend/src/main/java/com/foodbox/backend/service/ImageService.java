package com.foodbox.backend.service;

import com.foodbox.backend.entity.Image;
import com.foodbox.backend.repository.ImageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    
    @Autowired
    private ImageRepository imageRepository;
   

  
    public Image getImageById(int imageId) {
        return this.imageRepository.findImageByImageId(imageId);
    }

    public Image createImage(byte[] data)  {
        
    Image image = new Image(data);
    return this.imageRepository.save(image);
    }

    public void updateImage(int imageId, byte[] image) {

         imageRepository.updateImage(image, imageId);    }

    public void deleteImage(int imageId) {
        imageRepository.deleteById(imageId);
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }
}
