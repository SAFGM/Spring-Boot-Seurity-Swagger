package com.api.beneficiarios.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "TB_DOCUMENTO")
public class DocumentoEntity implements Serializable {
    @Id
    @Column(name ="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="TP_DOCUMENTO", nullable = false )
    private String tpDocumento;

    @Column(name="DESC_DOCUMENTO", nullable = false)
    private String descDocumento;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy'T'HH:mm:ss")
    @Column(name="DT_INCLUSAO_DOCUMENTO")
    private LocalDateTime dtInclusao_Documento;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy'T'HH:mm:ss")
    @Column(name="DT_ATUALIZACAO_DOCUMENTO")
    private LocalDateTime    dtAtualizacaoDocumento;

    @ManyToOne
    @JoinColumn(name = "beneficiario_id", nullable = false)
    private BeneficiarioEntity beneficiarioId;

    public DocumentoEntity(){

    }
}
