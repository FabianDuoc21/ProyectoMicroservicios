package com.example.evento.service;

import com.example.evento.model.Evento;
import com.example.evento.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    public Evento createEvento(Evento evento) {

        log.info("Registrando evento {}",
                evento.getNombreEvento());

        Evento nuevo = eventoRepository.save(evento);

        log.info("Evento registrado ID {}",
                nuevo.getIdEvento());

        return nuevo;
    }

    public Optional<Evento> getEventoById(Integer id) {
        log.info("Buscando evento con ID {}", id);
        return eventoRepository.findById(id);
    }

    public List<Evento> getAllEventos() {

        log.info("Consultando eventos");

        return eventoRepository.findAll();
    }

    public Evento updateEvento(Integer id,Evento eventoActualizado) {

        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Evento no encontrado"));

        evento.setNombreEvento(
                eventoActualizado.getNombreEvento());

        evento.setFechaEvento(
                eventoActualizado.getFechaEvento());


        return eventoRepository.save(evento);
    }

    public void deleteEvento(Integer id) {

        log.info("Eliminando comuna {}", id);

        eventoRepository.deleteById(id);
    }
}