package com.foodbox.backend.entity;

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
    private Integer id;

    @Column(name = "image_name", nullable = false)
    private String name;

    @Lob
    @Column(name = "image_data", nullable = false)
    private byte[] imageData;

    public Image(String name, byte[] data) {
        this.name = name;
        this.imageData = data;
    }

   

}