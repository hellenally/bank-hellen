package com.bank.bankhellen.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateNasabahRequest {
    @NotBlank(message = "Nama lengkap wajib diisi!")
    private String namaLengkap;

    @NotBlank(message = "Alamat wajib diisi!")
    private String alamat;

    @NotBlank(message = "Tempat lahir wajib diisi!")
    private String tempatLahir;

    @NotNull(message = "Tanggal lahir wajib diisi!")
    private LocalDate tanggalLahir;

    @NotBlank(message = "Nomor KTP wajib diisi!")
    private String nomorKtp;

    @NotBlank(message = "Nomor handphone wajib diisi!")
    private String nomorHandphone;
}