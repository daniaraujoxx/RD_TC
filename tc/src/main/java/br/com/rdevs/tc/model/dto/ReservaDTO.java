package br.com.rdevs.tc.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDTO {


    private ClienteDTO clienteDTO;
    private Date dtInicialReserva;
    private Date dtFinalReserva;
    private BigInteger idTcReserva;
    private List<ReservaItemDTO> itens;



}



