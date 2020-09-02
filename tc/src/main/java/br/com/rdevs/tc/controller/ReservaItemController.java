package br.com.rdevs.tc.controller;

import br.com.rdevs.tc.model.dto.ReservaItemDTO;
import br.com.rdevs.tc.model.entity.ReservaItemEntity;
import br.com.rdevs.tc.service.ReservaItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/reservaitem")
public class ReservaItemController {

    @Autowired
    ReservaItemService reservaItemService;

    @GetMapping("/{idTcReserva}/{cdProduto}")
    public ReservaItemDTO buscarItem(@PathVariable("idTcReserva")BigInteger idTcReserva, @PathVariable("cdProduto")
    BigInteger cdProduto) {
        return reservaItemService.buscarItem(idTcReserva, cdProduto);

    }

    @PutMapping
    public ReservaItemDTO alterar(@RequestBody ReservaItemDTO dto) {
        return reservaItemService.alterar(dto);
    }
}
