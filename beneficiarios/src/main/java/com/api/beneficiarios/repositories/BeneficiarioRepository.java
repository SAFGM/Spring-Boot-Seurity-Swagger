package com.api.beneficiarios.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.api.beneficiarios.entity.BeneficiarioEntity;


public interface BeneficiarioRepository extends JpaRepository<BeneficiarioEntity, Long> {

    
}
