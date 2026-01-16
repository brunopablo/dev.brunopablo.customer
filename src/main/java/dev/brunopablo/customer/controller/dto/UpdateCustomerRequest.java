package dev.brunopablo.customer.controller.dto;

public record UpdateCustomerRequest(String name,
                                    String email,
                                    String phone) {}