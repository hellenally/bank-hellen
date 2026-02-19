package com.bank.bankhellen.repository;

import com.bank.bankhellen.entity.Nasabah;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface NasabahRepository extends JpaRepository<Nasabah, Long> {
    Optional<Nasabah> findByNomorKtp(String nomorKtp);
}