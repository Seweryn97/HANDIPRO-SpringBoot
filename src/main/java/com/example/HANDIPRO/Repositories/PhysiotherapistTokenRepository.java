package com.example.HANDIPRO.Repositories;

import com.example.HANDIPRO.models.PhysiotherapistToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhysiotherapistTokenRepository extends JpaRepository<PhysiotherapistToken,Integer> {
    PhysiotherapistToken findByConfirmationtoken(String PhysiotherapistToken);

    void deleteById(Integer id);
}
