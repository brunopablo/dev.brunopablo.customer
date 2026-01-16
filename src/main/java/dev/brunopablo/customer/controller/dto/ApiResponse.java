package dev.brunopablo.customer.controller.dto;

import java.util.List;

public record ApiResponse<T>(List<T> pageContent,
                            PaginationRequest paginationRequest) {}
