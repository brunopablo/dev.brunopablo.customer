package dev.brunopablo.customer.controller.dto;


public record PaginationRequest(Integer pageNumber,
                                Integer pageSize,
                                Long totalElements,
                                Integer numberOfPages) {}
