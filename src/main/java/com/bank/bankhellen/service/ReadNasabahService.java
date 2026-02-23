package com.bank.bankhellen.service;

import com.bank.bankhellen.entity.Nasabah;
import com.bank.bankhellen.repository.NasabahRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReadNasabahService {
    @Autowired
    private NasabahRepository repository;

    public Page<Nasabah> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }

    public Nasabah findByKtp(String ktp) {
        return repository.findByNomorKtp(ktp).orElse(null);
    }
}