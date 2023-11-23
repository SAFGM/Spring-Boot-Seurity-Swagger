package com.api.beneficiarios.controller.api;



import com.api.beneficiarios.entity.DocumentoEntity;
import com.api.beneficiarios.repositories.DocumentoRepository;
import com.api.beneficiarios.service.PersistDocumentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
@CrossOrigin(origins = "*, maxAge = 3600")
@Api(value = "API REST Documentos")
@RequestMapping("/api/doc")
public class DocumentoController {

    @Autowired
    private PersistDocumentoService persistDocumentoService;

    public DocumentoController (PersistDocumentoService persistDocumentoService){
        this.persistDocumentoService = persistDocumentoService;
    }


    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Salva Documento")
    public DocumentoEntity salvar(@RequestBody @Valid  DocumentoEntity documentoEntity){
        Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());
        documentoEntity.setDtInclusao_Documento(dataDeHoje.toLocalDateTime());

        return persistDocumentoService.salvar(documentoEntity);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Lista de  Documentos")
    public  ResponseEntity<Page<DocumentoEntity>> listDocumentos(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(persistDocumentoService.listDocumentos(pageable));
    }

    @GetMapping(value = "buscarPorBeneficiario")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Pesquisar Documentos de um Beneficiário")
    public ResponseEntity<List<DocumentoEntity>> buscarPorBeneficiario(@RequestParam Long beneficiarioId){
        List<DocumentoEntity> documentoEntity =  persistDocumentoService.buscarPorBeneficiario(beneficiarioId);
        return ResponseEntity.ok (documentoEntity);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Remover Documento")
    public void removerDocumento(@PathVariable("id") Long id){
        persistDocumentoService.pesquisarPorId(id)
                .map(documentoEntity ->  {
                    persistDocumentoService.deletarDocumento(documentoEntity.getId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Documento não encontrado!"));
    }

//    @GetMapping(value = "/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    @ApiOperation(value = "Pesquisar Documento por ID")
//    public DocumentoEntity pesquisarDocumentoPorID(@PathVariable("id") Long id){
//        return  persistDocumentoService.pesquisarPorId(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Documento não encontrado!"));
//    }


//    @PutMapping(value = "/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @ApiOperation(value = "Atualizar Documento")
//    public void atualizarDocumento(@PathVariable("id") Long id, @RequestBody DocumentoEntity documentoEntity){
//        Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());
//        documentoEntity.setDtAtualizacaoDocumento(dataDeHoje.toLocalDateTime());
//        persistDocumentoService.pesquisarPorId(id)
//                .map( documentoBase -> {
//                    modelMapper.map(documentoEntity, documentoBase);
//                    persistDocumentoService.salvar(documentoBase);
//                    return Void.TYPE;
//                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Documento não encontrado!"));
//    }
}
