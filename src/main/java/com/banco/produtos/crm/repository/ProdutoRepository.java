package com.banco.produtos.crm.repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banco.produtos.crm.model.ProdutoModel;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long>{
	
	Optional<ProdutoModel> findByNome(String nome);
	Optional<ProdutoModel> findByPreco(BigDecimal preco);
}
