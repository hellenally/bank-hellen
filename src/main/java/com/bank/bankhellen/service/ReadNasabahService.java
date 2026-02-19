package com.bank.bankhellen.service;

import com.bank.bankhellen.entity.Nasabah;
import com.bank.bankhellen.repository.NasabahRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReadNasabahService {
    @Autowired
    private NasabahRepository repository;

    public List<Nasabah> findAll() {
        return repository.findAll();
    }

    public Nasabah findByKtp(String ktp) {
        return repository.findByNomorKtp(ktp).orElse(null);
    }
}