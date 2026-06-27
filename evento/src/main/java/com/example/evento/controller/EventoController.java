package com.example.evento.controller;

import com.example.evento.dto.EventoDTO;
import com.example.evento.model.Evento;
import com.example.evento.service.EventoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/eventos")
@Tag(name= "Eventos", description = "Operaciones relacionadas con los eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los eventos", description = "Obtiene una lista de todos los eventos")
    public List<Evento> getAllEventos() {
        return eventoService.getAllEventos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener evento por código", description = "Obtiene cada evento ingresado por código")
    public ResponseEntity<Evento> getEventoById(@PathVariable Integer id) {
        Optional<Evento> evento = eventoService.getEventoById(id);

        return evento.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear evento",description = "Obtiene cada evento nuevo")
    public ResponseEntity<Evento> createEvento(@Valid @RequestBody EventoDTO dto) {

        Evento evento = new Evento(
                dto.getIdEvento(),
                dto.getNombreEvento(),
                dto.getFechaEvento(),
                dto.getIdComuna(),
                dto.getIdUsuario()
        );

        return ResponseEntity.ok(
                eventoService.createEvento(evento)
        );
    }

    @PutMapping("/{id}")
    @Operation (summary = "Actualizar eventos", description = "Obtiene eventos actualizados")
    public ResponseEntity<Evento> updateEvento(
            @PathVariable Integer id,
            @Valid @RequestBody EventoDTO dto) {

        Evento evento = new Evento(
                id,
                dto.getNombreEvento(),
                dto.getFechaEvento(),
                dto.getIdComuna(),
                dto.getIdUsuario()
        );

        return ResponseEntity.ok(
                eventoService.updateEvento(id, evento)
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar evento", description = "Elimina eventos")
    public ResponseEntity<Void> deleteEvento(@PathVariable Integer id) {
        eventoService.deleteEvento(id);
        return ResponseEntity.noContent().build();
    }


}
