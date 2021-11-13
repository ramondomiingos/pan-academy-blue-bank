package com.panacademy.squad7.bluebank.services;

import com.panacademy.squad7.bluebank.domain.models.Address;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {

    void  salvar (Address address);

    void  editar (Address address);

    void  excluir (Integer id);

    Address buscarPorId (Integer id);

    List<Address> buscarTodos ();
}
