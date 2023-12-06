package com.foodbox.backend.entity;

import org.springframework.beans.factory.annotation.Autowired;

import com.foodbox.backend.repository.ImageRepository;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "images", schema = "foodbox")
public class Image {
    

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id", nullable = false)
    private Integer imageId;

 

    @Lob
    @Column(name = "image_data", nullable = false)
    private byte[] imageData;

    public Image(byte[] data) {
        this.imageData = data;
    }

    // public Image(Integer imageId) {
    //     this.imageId = imageId;
    //    this.imageData = imageRepository.findImageByImageId(imageId).imageData;
    // }
   

}