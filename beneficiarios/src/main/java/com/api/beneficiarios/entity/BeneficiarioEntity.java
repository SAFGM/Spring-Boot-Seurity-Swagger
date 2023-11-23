package com.api.beneficiarios.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;

@Getter @Setter
@Data
@AllArgsConstructor
@Builder
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

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy'T'HH:mm:ss")
    @Column(name="DT_INCLUSAO")
    private LocalDateTime dtInclusao;
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy'T'HH:mm:ss")
    @Column(name="DT_ATUALIZACAO")
    private LocalDateTime dtAtualizacao;

    public BeneficiarioEntity(){

    }
     @Override
     public int hashCode() {
         final int prime = 31;
         int result = 1;
         result = prime * result + ((id == null) ? 0 : id.hashCode());
         return result;
     }

    @Override
     public boolean equals(Object obj) {
         if (this == obj)
             return true;
         if (obj == null)
             return false;
         if (getClass() != obj.getClass())
              return false;
         BeneficiarioEntity other = (BeneficiarioEntity) obj;
         if (id == null) {
             if (other.id != null)
                 return false;
         } else if (!id.equals(other.id))
             return false;
         return true;
     }
}
