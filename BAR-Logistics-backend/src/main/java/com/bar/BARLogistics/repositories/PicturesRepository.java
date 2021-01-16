package com.bar.BARLogistics.repositories;

import com.bar.BARLogistics.entities.Pictures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface PicturesRepository extends JpaRepository<Pictures, Integer> {

    @Query("SELECT p FROM Pictures p WHERE p.id = :id")
    Pictures findPicturesBy(Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE Pictures SET path = :path WHERE type = :type AND img = :img")
    void setPicture(String path, String type, byte[] img);
}
