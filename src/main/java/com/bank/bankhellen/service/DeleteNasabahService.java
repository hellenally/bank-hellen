package com.bank.bankhellen.service;

import com.bank.bankhellen.entity.Nasabah;
import com.bank.bankhellen.repository.NasabahRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteNasabahService {
    @Autowired
    private NasabahRepository repository;

    public boolean execute(Long id) throws Exception {
        Nasabah n = repository.findById(id).orElseThrow(() -> new Exception("404:Nasabah tidak ditemukan!"));
        if (n.getStatus() == 0) throw new Exception("400:Gagal, nasabah tersebut sudah non-aktif!");

        n.setStatus(0);
        repository.save(n);
        return true;
    }
}