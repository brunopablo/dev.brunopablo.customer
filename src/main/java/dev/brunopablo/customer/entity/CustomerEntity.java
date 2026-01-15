package dev.brunopablo.customer.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_customers")
public class CustomerEntity {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    @Column(name="name")
    private String name;

    @Column(name="cpf", unique=true)
    private String cpf;

    @Column(name="email", unique=true)
    private String email;

    @Column(name="phone")
    private String phone;

    @CreationTimestamp
    @Column(name="created_at")
    private LocalDateTime created_at;

    @CreationTimestamp
    @Column(name="updated_at")
    private LocalDateTime updated_at;

    public CustomerEntity() {
    }

    public CustomerEntity(UUID id, String name, String cpf, String email, String phone, LocalDateTime created_at,
            LocalDateTime updated_at) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.phone = phone;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}