package com.api.beneficiarios.service;

import com.api.beneficiarios.entity.BeneficiarioEntity;
import com.api.beneficiarios.repositories.BeneficiarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class PersistBeneficiarioService {
    @Autowired
    private BeneficiarioRepository repository;

    public BeneficiarioEntity salvar(BeneficiarioEntity beneficiarioEntity){
        return  repository.save(beneficiarioEntity);
    }
    public Page<BeneficiarioEntity> listBeneficiarios(Pageable pageable){
        return  repository.findAll(pageable);
    }
    public Optional<BeneficiarioEntity> pesquisarPorId(Long id){
        return repository.findById(id);
    }

    public void deletarBeneficiario(Long id){
        repository.deleteById(id);
    }

}