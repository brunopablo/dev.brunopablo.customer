package dev.brunopablo.customer.controller.dto;

public record CreateCustomerRequest(String name,
                                    String cpf,
                                    String email,
                                    String phone) {}