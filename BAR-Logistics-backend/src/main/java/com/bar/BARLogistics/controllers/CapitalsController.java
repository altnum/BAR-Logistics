package com.bar.BARLogistics.controllers;

import com.bar.BARLogistics.entities.Capitals;
import com.bar.BARLogistics.repositories.CapitalsRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class CapitalsController {

    private CapitalsRepository capitalsRepository;

    public CapitalsController(CapitalsRepository capitalsRepository) {
        this.capitalsRepository = capitalsRepository;
    }

    @GetMapping("/all/capitals/all")
    public List<Capitals> getAllCapitals() {
        List<Capitals> capitals = capitalsRepository.findAll();

        capitals = capitals.stream().sorted(Comparator.comparing(Capitals::getName)).collect(Collectors.toList());

        return capitals;
    }

    @GetMapping("user/capitals/search")
    public Capitals getCapitalByName(String name) {
        return capitalsRepository.findCapitalsByName(name);
    }
}
