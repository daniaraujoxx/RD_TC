package br.com.rdevs.tc.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;



@Entity
@Table(name = "TB_PRODUTO")
@Data
public class ProdutoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_PRODUTO")
    private BigInteger cdProduto;

    @OneToOne
    @JoinColumn(name = "ID_STATUS_PRODUTO")
    private StatusProdutoEntity statusProduto;

    @ManyToOne
    @JoinColumn(name = "ID_CATEGORIA")
    private CategoriaProdutoEntity categoria;

    @OneToOne
    @JoinColumn(name = "ID_SUB_CATEGORIA")
    private SubCategoriaProdutoEntity subCategoria;

    @Column(name = "ID_TIPO_PRODUTO")
    private BigInteger idTipoProduto;

    @Column(name = "NM_FANTASIA")
    private String nmFantasia;

    @Column(name = "NM_FABRICANTE")
    private String nmFabricante;

    @Column(name = "VL_UNIDADE")
    private double vlUnidade;

    @Column(name="DS_PRODUTO")
    private String dsProduto;

    @OneToMany
    @JoinColumn(name = "CD_PRODUTO")
    private List<LmpmItemEntity> lmpmLista;





}
