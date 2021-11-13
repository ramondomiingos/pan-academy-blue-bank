package com.panacademy.squad7.bluebank.services;

import com.panacademy.squad7.bluebank.dao.AddressDao;
import com.panacademy.squad7.bluebank.domain.models.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class AddressServiceImpl implements AddressService{
    @Autowired
    private AddressDao dao;

    @Override
    public void salvar(Address address) {
        dao.save(address);
    }

    @Override
    public void editar(Address address) {
        dao.update(address);
    }

    @Override
    public void excluir(Integer id) {
        dao.delete(id.longValue());
    }

    @Override
    @Transactional(readOnly = true)
    public Address buscarPorId(Integer id) {
        return dao.findById(id.longValue());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Address> buscarTodos() {
        return dao.findAll();
    }
}
