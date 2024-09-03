package br.edu.iftm.rastreamento.service.util;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.edu.iftm.rastreamento.dto.EnderecoDTO;
import br.edu.iftm.rastreamento.dto.PacoteDTO;
import br.edu.iftm.rastreamento.dto.RastreamentoDTO;
import br.edu.iftm.rastreamento.model.Endereco;
import br.edu.iftm.rastreamento.model.Pacote;
import br.edu.iftm.rastreamento.model.Rastreamento;

@Component
public class Converters {

    public PacoteDTO convertToDTO(Pacote pacote) {
        PacoteDTO pacoteDTO = new PacoteDTO();
        pacoteDTO.setId(pacote.getId());
        pacoteDTO.setIdUnico(pacote.getIdUnico());
        pacoteDTO.setDestinatario(pacote.getDestinatario());
        pacoteDTO.setEndereco(convertToDTO(pacote.getEndereco()));
        pacoteDTO.setStatus(pacote.getStatus());
        pacoteDTO.setRastreamentos(
                pacote.getRastreamentos().stream().map(this::convertToDTO).collect(Collectors.toList()));
        return pacoteDTO;
    }

    public Pacote convertToEntity(PacoteDTO pacoteDTO) {
        Pacote pacote = new Pacote();
        pacote.setId(pacoteDTO.getId());
        pacote.setIdUnico(pacoteDTO.getIdUnico());
        pacote.setDestinatario(pacoteDTO.getDestinatario());
        pacote.setEndereco(convertToEntity(pacoteDTO.getEndereco()));
        pacote.setStatus(pacoteDTO.getStatus());
        pacote.setRastreamentos(
                pacoteDTO.getRastreamentos().stream().map(this::convertToEntity).collect(Collectors.toList()));
        return pacote;
    }

    public EnderecoDTO convertToDTO(Endereco endereco) {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(endereco.getId());
        enderecoDTO.setRua(endereco.getRua());
        enderecoDTO.setNumero(endereco.getNumero());
        enderecoDTO.setCidade(endereco.getCidade());
        enderecoDTO.setEstado(endereco.getEstado());
        enderecoDTO.setCep(endereco.getCep());
        return enderecoDTO;
    }

    public Endereco convertToEntity(EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco();
        endereco.setId(enderecoDTO.getId());
        endereco.setRua(enderecoDTO.getRua());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setEstado(enderecoDTO.getEstado());
        endereco.setCep(enderecoDTO.getCep());
        return endereco;
    }

    public void updateEntityFromDTO(EnderecoDTO enderecoDTO, Endereco endereco) {
        endereco.setRua(enderecoDTO.getRua());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setEstado(enderecoDTO.getEstado());
        endereco.setCep(enderecoDTO.getCep());
    }

    public RastreamentoDTO convertToDTO(Rastreamento rastreamento) {
        RastreamentoDTO rastreamentoDTO = new RastreamentoDTO();
        rastreamentoDTO.setId(rastreamento.getId());
        rastreamentoDTO.setDataHora(rastreamento.getDataHora());
        rastreamentoDTO.setLocalizacao(rastreamento.getLocalizacao());
        rastreamentoDTO.setStatus(rastreamento.getStatus());
        return rastreamentoDTO;
    }

    public Rastreamento convertToEntity(RastreamentoDTO rastreamentoDTO) {
        Rastreamento rastreamento = new Rastreamento();
        rastreamento.setId(rastreamentoDTO.getId());
        rastreamento.setDataHora(rastreamentoDTO.getDataHora());
        rastreamento.setLocalizacao(rastreamentoDTO.getLocalizacao());
        rastreamento.setStatus(rastreamentoDTO.getStatus());
        return rastreamento;
    }
}
