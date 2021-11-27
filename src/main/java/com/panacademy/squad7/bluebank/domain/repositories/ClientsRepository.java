package com.panacademy.squad7.bluebank.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.panacademy.squad7.bluebank.domain.models.Client;

@Repository
public interface ClientsRepository extends JpaRepository<Client, Long> {

}
