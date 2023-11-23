package com.api.beneficiarios.controller.api;

import com.api.beneficiarios.entity.BeneficiarioEntity;
import com.api.beneficiarios.entity.DocumentoEntity;
import com.api.beneficiarios.service.PersistBeneficiarioService;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@Api(value = "API REST Beneficiarios")
@RequestMapping("/api/benef")
public class BeneficiarioController {

    @Autowired
    private PersistBeneficiarioService persistBeneficiarioService;

    @Autowired
    private PersistDocumentoService persistDocumentoService;

    public BeneficiarioController (PersistBeneficiarioService persistBeneficiarioService){
        this.persistBeneficiarioService = persistBeneficiarioService;
    }

    @Autowired
    private ModelMapper modelMapper;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Salva Beneficiário")
    public BeneficiarioEntity salvar(@RequestBody @Valid BeneficiarioEntity beneficiarioEntity){
        Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());
        beneficiarioEntity.setDtInclusao(dataDeHoje.toLocalDateTime());
        return persistBeneficiarioService.salvar(beneficiarioEntity);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Lista Beneficiários")
    public Page<BeneficiarioEntity> listBeneficiarios(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return persistBeneficiarioService.listBeneficiarios(pageable);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Pesquisar Beneficiário")
    public BeneficiarioEntity pesquisarBeneficiarioPorID(@PathVariable("id") Long id){
        return  persistBeneficiarioService.pesquisarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Beneficiario não encontrado!"));
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Remover Beneficiário")
    public void removerBeneficiario(@PathVariable("id") Long id){
        List<DocumentoEntity> pesquisaDocsBeneficiario = persistDocumentoService.buscarPorBeneficiario(id);
        if (pesquisaDocsBeneficiario != null) {
            pesquisaDocsBeneficiario.forEach(pesquisa -> {
                persistDocumentoService.deletarDocumento(pesquisa.getId());
            });
        }
        persistBeneficiarioService.pesquisarPorId(id)
                .map(beneficiarioEntity ->  {
                    persistBeneficiarioService.deletarBeneficiario(beneficiarioEntity.getId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Beneficiário não encontrado!"));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Atualizar Beneficiário")
    public void atualizarBeneficiario(@PathVariable("id")   @Valid Long id, @RequestBody BeneficiarioEntity beneficiarioEntity){
        Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());
        beneficiarioEntity.setDtAtualizacao(dataDeHoje.toLocalDateTime());
        persistBeneficiarioService.pesquisarPorId(id)
                .map( beneficiarioBase -> {
                    modelMapper.map(beneficiarioEntity, beneficiarioBase);
                    persistBeneficiarioService.salvar(beneficiarioBase);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Beneficiário não encontrado!"));
    }
}
