package br.edu.iftm.rastreamento.controller;

import java.util.List;

import br.edu.iftm.rastreamento.model.Pacote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.edu.iftm.rastreamento.dto.PacoteDTO;
import br.edu.iftm.rastreamento.service.PacoteService;
import br.edu.iftm.rastreamento.service.exceptions.NaoAcheiException;

@RestController
@RequestMapping("/pacotes")
public class PacoteController {

    @Autowired
    private PacoteService pacoteService;

    @GetMapping("/status")
    public List<Pacote> buscarPorStatus(@RequestParam String status) {
        return pacoteService.buscarPacotesPorStatus(status);
    }

    @GetMapping("/destinatario")
    public List<Pacote> buscarPorDestinatario(@RequestParam String destinatario) {
        return pacoteService.buscarPacotesPorDestinatario(destinatario);
    }
    @GetMapping
    public List<PacoteDTO> getAllPacotes() {
        return pacoteService.getAllPacotes();
    }

    @GetMapping("/{id}")
    public PacoteDTO getPacoteById(@PathVariable Long id) {
        return pacoteService.getPacoteById(id)
                .orElseThrow(() -> new NaoAcheiException(id));
    }

    @PostMapping
    public PacoteDTO createPacote(@RequestBody PacoteDTO pacoteDTO) {
        return pacoteService.createPacote(pacoteDTO);
    }

    @PutMapping("/{id}")
    public PacoteDTO updatePacote(@PathVariable Long id, @RequestBody PacoteDTO pacoteDTO) {
        return pacoteService.updatePacote(id, pacoteDTO)
                .orElseThrow(() -> new NaoAcheiException(id));
    }
}
