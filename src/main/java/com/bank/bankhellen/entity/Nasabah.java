package com.bank.bankhellen.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "nasabah")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Nasabah {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String namaLengkap;
    private String alamat;
    private String tempatLahir;
    private LocalDate tanggalLahir;

    @Column(unique = true)
    private String nomorKtp;
    private String nomorHandphone;

    @Builder.Default
    private Integer status = 1; // 1 = ACTIVE, 0 = INACTIVE
}