package br.com.rdevs.tc.controller;

import br.com.rdevs.tc.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
public class ProdutoController {
    @Autowired
    ProdutoService produtoService;
    @GetMapping("/produto")
    public ResponseEntity produto(){

        return ResponseEntity.ok().body(produtoService.listarProduto());
    }

<<<<<<< HEAD
    @GetMapping("/produto/codigo/{codigo}")
=======
    @GetMapping("/produto/{codigo}")
>>>>>>> APIRESERVA3
    public ResponseEntity produtoId(@PathVariable("codigo") BigInteger cdProduto){

        return ResponseEntity.ok().body(produtoService.listarPorId(cdProduto));
    }
<<<<<<< HEAD

    @GetMapping("/produto/nome/{nome}")
    public ResponseEntity produtoNome(@PathVariable("nome") String nomeProduto){

        return ResponseEntity.ok().body(produtoService.listarPorNome(nomeProduto));
    }
=======
>>>>>>> APIRESERVA3
}
