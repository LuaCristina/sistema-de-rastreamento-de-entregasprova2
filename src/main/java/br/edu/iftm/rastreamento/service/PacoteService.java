package br.edu.iftm.rastreamento.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iftm.rastreamento.dto.PacoteDTO;
import br.edu.iftm.rastreamento.model.Endereco;
import br.edu.iftm.rastreamento.model.Pacote;
import br.edu.iftm.rastreamento.repository.EnderecoRepository;
import br.edu.iftm.rastreamento.repository.PacoteRepository;
import br.edu.iftm.rastreamento.service.exceptions.NaoAcheiException;
import br.edu.iftm.rastreamento.service.util.Converters;

@Service
public class PacoteService {

    @Autowired
    private PacoteRepository pacoteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private Converters converters;

    public List<Pacote> buscarPacotesPorStatus(String status) {
        return pacoteRepository.findByStatus(status);
    }

    public List<Pacote> buscarPacotesPorDestinatario(String destinatario) {
        return pacoteRepository.findByDestinatario(destinatario);
    }

    public List<PacoteDTO> getAllPacotes() {
        Iterable<Pacote> pacotesIterable = pacoteRepository.findAll();
        List<Pacote> pacotesList = new ArrayList<>();
        pacotesIterable.forEach(pacotesList::add);
        return pacotesList.stream()
                          .map(converters::convertToDTO)
                          .collect(Collectors.toList());
    }

    public Optional<PacoteDTO> getPacoteById(Long id) {
        return pacoteRepository.findById(id)
                               .map(converters::convertToDTO);
    }

    public PacoteDTO createPacote(PacoteDTO pacoteDTO) {
        Endereco endereco = enderecoRepository.findById(pacoteDTO.getEndereco().getId())
                                              .orElseThrow(() -> new NaoAcheiException(pacoteDTO.getEndereco().getId()));
        Pacote pacote = converters.convertToEntity(pacoteDTO);
        pacote.setEndereco(endereco);
        Pacote savedPacote = pacoteRepository.save(pacote);
        return converters.convertToDTO(savedPacote);
    }

    public Optional<PacoteDTO> updatePacote(Long id, PacoteDTO pacoteDTO) {
        return pacoteRepository.findById(id)
                               .map(existingPacote -> {
                                   Pacote pacote = converters.convertToEntity(pacoteDTO);
                                   pacote.setId(id);
                                   Pacote updatedPacote = pacoteRepository.save(pacote);
                                   return converters.convertToDTO(updatedPacote);
                               });
    }
}
