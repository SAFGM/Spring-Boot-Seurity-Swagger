package com.api.beneficiarios.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@Entity
@Table(name = "TB_BENEFICIARIOS ")
public class BeneficiarioEntity implements Serializable {

    @Id
    @Column(name ="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name="NOME", nullable = false, length = 70)
    private String nome;    

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    @Column(name="DT_NASCIMENTO" )
    private LocalDate dtNascimento;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
    @Column(name="DT_INCLUSAO")
    private LocalDateTime dtInclusao;
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
    @Column(name="DT_ATUALIZACAO")
    private LocalDateTime dtAtualizacao;

    public BeneficiarioEntity(){

    }


}
