package com.bank.bankhellen.model.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagingResponse {
    private Integer currentPage;
    private Integer totalPage;
    private Integer size;
    private Long totallElement;
    private Integer numberOfElements;
}