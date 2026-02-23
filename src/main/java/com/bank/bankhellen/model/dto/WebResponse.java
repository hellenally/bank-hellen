package com.bank.bankhellen.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebResponse<T> {
    private int responseCode;
    private String status;
    private String message;
    private T data;
    private PagingResponse paging;
}