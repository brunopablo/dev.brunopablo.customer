package dev.brunopablo.customer.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.brunopablo.customer.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID>{

    Page<CustomerEntity> findByEmailAndCpf(String email, String cpf, PageRequest pageRequest);
    Page<CustomerEntity> findByCpf(String cpf, PageRequest pageRequest);
    Page<CustomerEntity> findByEmail(String email, PageRequest pageRequest);
}