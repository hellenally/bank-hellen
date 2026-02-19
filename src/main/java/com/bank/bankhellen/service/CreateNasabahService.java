package com.bank.bankhellen.service;

import com.bank.bankhellen.entity.Nasabah;
import com.bank.bankhellen.model.dto.CreateNasabahRequest;
import com.bank.bankhellen.repository.NasabahRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateNasabahService {
    @Autowired
    private NasabahRepository repository;

    public Nasabah execute(CreateNasabahRequest r) throws Exception {
        if (repository.findByNomorKtp(r.getNomorKtp()).isPresent()) {
            throw new Exception("400:Gagal, Nomor KTP sudah pernah terdaftar");
        }

        Nasabah n = Nasabah.builder()
                .namaLengkap(r.getNamaLengkap()).alamat(r.getAlamat())
                .tempatLahir(r.getTempatLahir()).tanggalLahir(r.getTanggalLahir())
                .nomorKtp(r.getNomorKtp()).nomorHandphone(r.getNomorHandphone())
                .status(1).build();
        return repository.save(n);
    }
}