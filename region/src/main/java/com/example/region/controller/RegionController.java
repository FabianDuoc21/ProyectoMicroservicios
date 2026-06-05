package com.example.region.controller;

import com.example.region.dto.RegionDTO;
import com.example.region.model.Region;
import com.example.region.service.RegionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/regiones")
public class RegionController {

    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping
    public List<Region> getAllRegiones() {
        return regionService.getAllRegiones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Region> getRegionById(@PathVariable Integer id) {
        Optional<Region> region = regionService.getRegionById(id);

        return region.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Region> createRegion(@Valid @RequestBody RegionDTO dto) {

        Region region = new Region(
                dto.getIdRegion(),
                dto.getNombreRegion()
        );

        return ResponseEntity.ok(
                regionService.createRegion(region)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Region> updateRegion(
            @PathVariable Integer id,
            @Valid @RequestBody RegionDTO dto) {

        Region region = new Region(
                id,
                dto.getNombreRegion()
        );

        return ResponseEntity.ok(
                regionService.updateRegion(region)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegion(@PathVariable Integer id) {
        regionService.deleteRegion(id);
        return ResponseEntity.noContent().build();
    }
}
