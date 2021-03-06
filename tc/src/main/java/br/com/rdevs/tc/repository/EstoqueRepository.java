package br.com.rdevs.tc.repository;

import br.com.rdevs.tc.model.entity.EstoqueEntitty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface EstoqueRepository extends JpaRepository<EstoqueEntitty, BigInteger> {

    List<EstoqueEntitty> findByFilialCdFilialAndProdutoCdProduto(BigInteger cdFilial, BigInteger cdProduto);

    //EstoqueEntitty findByProdutoCdProduto(BigInteger cdProduto);

    List<EstoqueEntitty> findByFilialCdFilial(BigInteger cdFilial);

    List<EstoqueEntitty> findByProdutoCdProduto(BigInteger cdProduto);

    List<EstoqueEntitty> findByFilialCdFilialAndProdutoNmFantasiaContaining(BigInteger cdFilial, String nmFantasia);
}
