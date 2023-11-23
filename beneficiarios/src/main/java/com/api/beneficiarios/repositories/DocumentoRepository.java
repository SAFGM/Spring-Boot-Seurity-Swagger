package com.api.beneficiarios.repositories;

import com.api.beneficiarios.entity.DocumentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DocumentoRepository extends JpaRepository<DocumentoEntity, Long> {
    @Query(value ="select * from TB_DOCUMENTO  where beneficiario_id = ?1", nativeQuery = true)
    List<DocumentoEntity> findBeneficiario(Long beneficiarioId);


}
