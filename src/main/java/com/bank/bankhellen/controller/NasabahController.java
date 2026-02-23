package com.bank.bankhellen.controller;

import com.bank.bankhellen.entity.Nasabah;
import com.bank.bankhellen.model.dto.*;
import com.bank.bankhellen.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/nasabah")
public class NasabahController {

    @Autowired private CreateNasabahService createService;
    @Autowired private DeleteNasabahService deleteService;
    @Autowired private ReadNasabahService readService;
    @Autowired private UpdateNasabahService updateService;

    @PostMapping("/daftar")
    public ResponseEntity<WebResponse<Object>> create(@Valid @RequestBody CreateNasabahRequest request) throws Exception {
        Nasabah result = createService.execute(request);
        return ResponseEntity.status(201).body(WebResponse.builder()
                .responseCode(201).status("OK").message("Sukses").data(result).build());
    }


    @GetMapping("/semua")
    public ResponseEntity<WebResponse<List<Nasabah>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Page<Nasabah> resultPage = readService.findAll(page, size);

        return ResponseEntity.ok(WebResponse.<List<Nasabah>>builder()
                .responseCode(200)
                .status("OK")
                .message("Berhasil mengambil data")
                .data(resultPage.getContent())
                        .paging(PagingResponse.builder()
                                .currentPage(resultPage.getNumber())
                                .totalPage(resultPage.getTotalPages())
                                .size(resultPage.getSize())
                                .totallElement(resultPage.getTotalElements())
                                .numberOfElements(resultPage.getNumberOfElements())
                                .build())
                .build());
    }

    @GetMapping("/cari/{ktp}")
    public ResponseEntity<WebResponse<Object>> getByKtp(@PathVariable String ktp) throws Exception {
        Nasabah result = readService.findByKtp(ktp);
        if (result == null) throw new Exception("404:Nasabah dengan KTP tersebut tidak ditemukan");

        return ResponseEntity.ok(WebResponse.builder()
                .responseCode(200).status("OK").message("Data ditemukan").data(result).build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<WebResponse<Object>> update(@PathVariable Long id, @RequestBody UpdateNasabahRequest request) throws Exception {
        Nasabah result = updateService.execute(id, request);
        if (result == null) throw new Exception("404:ID Nasabah tidak ditemukan");

        return ResponseEntity.ok(WebResponse.builder()
                .responseCode(200).status("OK").message("Update berhasil").data(result).build());
    }

    @DeleteMapping("/hapus/{id}")
    public ResponseEntity<WebResponse<Object>> delete(@PathVariable Long id) throws Exception {
        deleteService.execute(id);
        return ResponseEntity.ok(WebResponse.builder().responseCode(200).status("OK").message("Berhasil").build());
    }

    @PutMapping("/aktifkan/{id}")
    public ResponseEntity<WebResponse<Object>> reactivate(@PathVariable Long id) throws Exception {
        Nasabah result = updateService.reactivate(id);
        return ResponseEntity.ok(WebResponse.builder().responseCode(200).status("OK").data(result).build());
    }
}