package com.example.evento.controller;

import com.example.evento.dto.EventoDTO;
import com.example.evento.model.Evento;
import com.example.evento.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping
    public List<Evento> getAllEventos() {
        return eventoService.getAllEventos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable Integer id) {
        Optional<Evento> evento = eventoService.getEventoById(id);

        return evento.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
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
    public ResponseEntity<Void> deleteEvento(@PathVariable Integer id) {
        eventoService.deleteEvento(id);
        return ResponseEntity.noContent().build();
    }


}
