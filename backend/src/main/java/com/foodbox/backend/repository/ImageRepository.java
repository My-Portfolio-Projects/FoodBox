package com.foodbox.backend.repository;

import com.foodbox.backend.entity.Image;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ImageRepository extends CrudRepository<Image, Integer> {
    
    @Transactional
    @Modifying
    @Query("update Image i set i.imageData = ?1 where i.imageId = ?2")
    void updateImage(@Nullable byte[] imageData, @Nullable Integer id);
    
    Image findImageByImageId(int imageId);

    Image save(Image image);
    
    List<Image> findAll();
}