package com.api.beneficiarios.controller.api;


import com.api.beneficiarios.entity.DocumentoEntity;
import com.api.beneficiarios.service.PersistDocumentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.sql.Timestamp;
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
    @ApiOperation(value = "Cadastrar Documentos de um beneficiário")
    public DocumentoEntity salvar(@RequestBody @Valid  DocumentoEntity documentoEntity){
        Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());
        documentoEntity.setDtInclusao_Documento(dataDeHoje.toLocalDateTime());

        return persistDocumentoService.salvar(documentoEntity);
    }

   
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Listar todos os documentos de um beneficiário a partir de seu id")
    public ResponseEntity<List<DocumentoEntity>> buscarPorBeneficiario(@RequestParam Long beneficiarioId){
        List<DocumentoEntity> documentoEntity =  persistDocumentoService.buscarPorBeneficiario(beneficiarioId);
        return ResponseEntity.ok (documentoEntity);
    }

    
//   
}
