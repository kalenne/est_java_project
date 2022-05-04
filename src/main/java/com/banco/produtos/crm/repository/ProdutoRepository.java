package com.banco.produtos.crm.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banco.produtos.crm.model.ProdutoModel;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long>{
	
	List<ProdutoModel> findByNome(String nome);
	
	List<ProdutoModel> findByPreco(BigDecimal preco);
}
