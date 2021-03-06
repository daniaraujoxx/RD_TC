package br.com.rdevs.tc.model.entity;


import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;


@Entity
@Table(name = "TB_TC_RESERVA_ITEM")
@Data
@IdClass(ReservaItemPK.class)
public class ReservaItemEntity implements Serializable {

    @Id
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TC_RESERVA")
    private ReservaEntity reserva;

    @Id
    @OneToOne
    @JoinColumn(name = "CD_PRODUTO")
    private ProdutoEntity produto;

    @Column(name = "QT_PRODUTO")
    private Integer qtProduto;

    @Column(name = "ST_SEPARADO")
    private Boolean stSeparado;

}
