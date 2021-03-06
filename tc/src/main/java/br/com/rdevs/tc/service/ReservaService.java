package br.com.rdevs.tc.service;

import br.com.rdevs.tc.model.dto.ReservaDTO;

import br.com.rdevs.tc.model.dto.ResultData;
import br.com.rdevs.tc.model.entity.ReservaEntity;
import br.com.rdevs.tc.repository.ClienteRepository;
import br.com.rdevs.tc.repository.ReservaRepository;
import br.com.rdevs.tc.service.bo.ClienteBO;

import br.com.rdevs.tc.service.bo.ReservaBO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ReservaRepository repository;

    @Autowired
    private ClienteBO clienteBO;

    @Autowired
    private ReservaBO reservaBo;

    @Autowired
    private  AlterarEstoqueService alterarEstoqueService;

    public ResponseEntity listarPorClienteReservasValidas( BigInteger cdFilial, BigInteger idCliente) {
        ResultData resultData = null;
        if(Integer.parseInt(idCliente.toString()) <= 0){
            resultData = new ResultData(HttpStatus.BAD_REQUEST.value(), "Erro id cliente invalido! ");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultData);
        }
        try {
            Date date = new Date();
//            List<ReservaEntity> listEntity = repository.findByClienteIdCliente(idCliente);
            List<ReservaEntity> listEntity = repository.findByCdFilialAndClienteIdClienteAndDtBaixaIsNullAndDtFinalReservaGreaterThanEqual( cdFilial, idCliente, date);;
            List<ReservaDTO> listDTO = new ArrayList<>();
            for (ReservaEntity entity : listEntity) {

                ReservaDTO dto = reservaBo.parseToDTO(entity);

                listDTO.add(dto);
            }
            resultData = new ResultData(HttpStatus.ACCEPTED.value(),"Consulta de reserva do cliente realizada com sucesso!",listDTO);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultData);
        }
        catch (Exception e){
            resultData = new ResultData(HttpStatus.BAD_REQUEST.value(),"Erro ao consultar reserva do cliente! " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultData);
        }

    }

    public ResponseEntity inserir(ReservaDTO dto) {
        ResultData resultData = null;
        if(Integer.parseInt(dto.getClienteDTO().getIdCliente().toString()) <= 0){
            resultData = new ResultData(HttpStatus.BAD_REQUEST.value(), "Erro id cliente invalido! ");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultData);
        }
        if(dto.getDtInicialReserva() == null){
            resultData = new ResultData(HttpStatus.BAD_REQUEST.value(), "Erro Data Inicial invalida! ");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultData);
        }
        if(dto.getDtFinalReserva() == null){
            resultData = new ResultData(HttpStatus.BAD_REQUEST.value(), "Erro Data Final invalida! ");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultData);
        }
        if(dto.getItens() == null){
            resultData = new ResultData(HttpStatus.BAD_REQUEST.value(), "Itens da reserva Invalido! ");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultData);
        }
        try {
            ReservaEntity entity = reservaBo.parseToEntity(dto, null);
            if (entity.getCliente() != null) {
                repository.save(entity);
                alterarEstoqueService.adicionarReserva(entity);
            }
            ReservaDTO retornoReserva = reservaBo.parseToDTO(entity);
            resultData = new ResultData(HttpStatus.CREATED.value(), "Reserva gravada com sucesso!", retornoReserva);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultData);
        }catch(Exception e){
            resultData = new ResultData(HttpStatus.BAD_REQUEST.value(), "Erro ao gravar reserva! " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultData);
        }

    }

    public ResponseEntity listarPorClienteReservasVencidas() {
        ResultData resultData = null;

        try {
            Date date = new Date();
//            List<ReservaEntity> listEntity = repository.findByClienteIdCliente(idCliente);
            List<ReservaEntity> listEntity = repository.findByDtBaixaIsNotNull();
            List<ReservaDTO> listDTO = new ArrayList<>();
            for (ReservaEntity entity : listEntity) {

                ReservaDTO dto = reservaBo.parseToDTO(entity);

                listDTO.add(dto);
            }
            resultData = new ResultData(HttpStatus.ACCEPTED.value(),"Consulta de reserva do cliente realizada com sucesso!",listDTO);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultData);
        }
        catch (Exception e){
            resultData = new ResultData(HttpStatus.BAD_REQUEST.value(),"Erro ao consultar reserva do cliente! " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultData);
        }

    }


}

