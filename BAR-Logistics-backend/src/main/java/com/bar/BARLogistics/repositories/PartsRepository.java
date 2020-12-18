package com.bar.BARLogistics.repositories;

import com.bar.BARLogistics.entities.Parts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.parser.Part;

import java.math.BigInteger;
import java.util.Optional;

public interface PartsRepository extends JpaRepository<Parts, BigInteger> {
    @Query("SELECT p FROM Parts p WHERE lower(p.part_name) = :name")
    Optional<Parts> findPartsByPart_name(String name);

    @Query("SELECT p FROM Parts p " +
            "WHERE (p.part_num = :part_num OR :part_num IS NULL) " +
            "AND lower(p.part_name) " +
            "LIKE :#{#part_name == null || #part_name.isEmpty()? '%' : '%'+#part_name+'%'} ")
    Page<Parts> findPageParts(Pageable page, BigInteger part_num, String part_name);

    @Query("SELECT p FROM Parts p WHERE (p.part_num) = :part_num")
    Parts findPartsByPart_num(BigInteger part_num);
}
