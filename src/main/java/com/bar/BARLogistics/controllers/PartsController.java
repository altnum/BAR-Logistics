package com.bar.BARLogistics.controllers;

import com.bar.BARLogistics.entities.Parts;
import com.bar.BARLogistics.repositories.PartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("parts")
public class PartsController {

    @Autowired
    PartsRepository partsRepository;

    @GetMapping("/all")
    public List<Parts> getAllParts() {
        return partsRepository.findAll();
    }

    @GetMapping("/search/part_name")
    public ResponseEntity<?> getPartByPart_name(@RequestParam(required = false) String part_name) {
        if (part_name == null || part_name.isBlank()) {
            return ResponseEntity.ok().body("Неправилни  критерии за намиране на частта!");
        }

        Optional<Parts> result = partsRepository.findPartsByPart_name(part_name.toLowerCase());
        return result.isPresent() ? ResponseEntity.ok(result.get()) : ResponseEntity.ok("Няма намерена част!");
    }

    @GetMapping("/search/part_num")
    public ResponseEntity<?> getPartByPart_num(@RequestParam(required = false) BigInteger part_num) {
        if (part_num == null) {
            return ResponseEntity.ok().body("Неправилни  критерии за намиране на частта!");
        }

        Optional<Parts> result = partsRepository.findById(part_num);
        return result.isPresent() ? ResponseEntity.ok(result.get()) : ResponseEntity.ok("Няма намерена част!");
    }

    @PostMapping("/save")
    public ResponseEntity<?> savePart(@RequestParam(required = false) BigInteger part_num, String part_name, String vehicle_type, String location, Double price) {
        boolean isNew = part_num == null;

        Parts part = new Parts(part_num, part_name, vehicle_type, location, price);
        part = partsRepository.save(part);
        Map<String, Object> response = new HashMap<>();
        response.put("Генериран номер на частта:", part.getPart_num());

        if (isNew) {
            response.put("message", "Частта е успешно записана!");
        } else {
            response.put("message", "Частта е успешно редактирана!");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
