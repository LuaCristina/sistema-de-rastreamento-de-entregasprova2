package br.edu.iftm.rastreamento.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iftm.rastreamento.dto.EnderecoDTO;
import br.edu.iftm.rastreamento.model.Endereco;
import br.edu.iftm.rastreamento.repository.EnderecoRepository;
import br.edu.iftm.rastreamento.service.exceptions.NaoAcheiException;
import br.edu.iftm.rastreamento.service.util.Converters;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private Converters converters;

    public List<EnderecoDTO> getAllEnderecos() {
        Iterable<Endereco> enderecosIterable = enderecoRepository.findAll();
        List<Endereco> enderecosList = new ArrayList<>();
        enderecosIterable.forEach(enderecosList::add);
        return enderecosList.stream()
                            .map(converters::convertToDTO)
                            .collect(Collectors.toList());
    }

    public EnderecoDTO getEnderecoById(Long id) {
        Endereco endereco = enderecoRepository.findById(id)
                                              .orElseThrow(() -> new NaoAcheiException("Endereco não encontrado"));
        return converters.convertToDTO(endereco);
    }

    public EnderecoDTO createEndereco(EnderecoDTO enderecoDTO) {
        Endereco endereco = converters.convertToEntity(enderecoDTO);
        Endereco savedEndereco = enderecoRepository.save(endereco);
        return converters.convertToDTO(savedEndereco);
    }

    public EnderecoDTO updateEndereco(Long id, EnderecoDTO enderecoDTO) {
        Endereco endereco = enderecoRepository.findById(id)
                                              .orElseThrow(() -> new NaoAcheiException("Endereco não encontrado"));

        // Atualiza os dados do endereço existente com os dados do DTO
        converters.updateEntityFromDTO(enderecoDTO, endereco);

        Endereco updatedEndereco = enderecoRepository.save(endereco);
        return converters.convertToDTO(updatedEndereco);
    }
}
