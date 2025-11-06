package com.login.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.login.login.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Produto findByName(String name);
}