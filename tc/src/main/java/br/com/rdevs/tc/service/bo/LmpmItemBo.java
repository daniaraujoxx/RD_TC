package br.com.rdevs.tc.service.bo;


import br.com.rdevs.tc.model.dto.LmpmItemDTO;
import br.com.rdevs.tc.model.entity.LmpmItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LmpmItemBo {

    @Autowired
    LmpmBo lmpmBo;

    public LmpmItemDTO parseToDto(LmpmItemEntity lmpmItemEntity){
        LmpmItemDTO lmpmItemDTO = new LmpmItemDTO();
        lmpmItemDTO.setIdLmpmItem(lmpmItemEntity.getIdLmpmItem());
        lmpmItemDTO.setPcDesconto(lmpmItemEntity.getPcDesconto());
        lmpmItemDTO.setQtProduto(lmpmItemEntity.getQtProduto());
        lmpmItemDTO.setLmpm(lmpmBo.parseToDto(lmpmItemEntity.getLmpmEntitty()));
        return lmpmItemDTO;

    }


    public LmpmItemEntity parseEntity(LmpmItemDTO lmpmItemDTO){
        LmpmItemEntity lmpmItemEntity = new LmpmItemEntity();
        lmpmItemEntity.setIdLmpmItem(lmpmItemDTO.getIdLmpmItem());
        lmpmItemEntity.setPcDesconto(lmpmItemDTO.getPcDesconto());
        lmpmItemEntity.setQtProduto(lmpmItemDTO.getQtProduto());
        lmpmItemEntity.setLmpmEntitty(lmpmBo.parseToEntity(lmpmItemDTO.getLmpm()));
        return lmpmItemEntity;

    }


}
