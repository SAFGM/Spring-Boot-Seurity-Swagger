package com.api.beneficiarios.service;

import com.api.beneficiarios.entity.DocumentoEntity;
import com.api.beneficiarios.repositories.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;
@Service
public class PersistDocumentoService {
    @Autowired
    private DocumentoRepository repository;

    public DocumentoEntity salvar(DocumentoEntity documentoEntity){
        return  repository.save(documentoEntity);
    }
    public Page<DocumentoEntity> listDocumentos(Pageable pageable){
        return  repository.findAll(pageable);
    }
    public Optional<DocumentoEntity> pesquisarPorId(Long id){
        return repository.findById(id);
    }

    public void deletarDocumento(Long id){
        repository.deleteById(id);
    }
    public List<DocumentoEntity> buscarPorBeneficiario(Long id){
        return repository.findBeneficiario(id);

    }
}
