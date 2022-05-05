package com.banco.produtos.crm.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.produtos.crm.exception.ProdutoNotFoundException;
import com.banco.produtos.crm.model.ProdutoModel;
import com.banco.produtos.crm.repository.ProdutoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository prodRepository;
	
	public List<ProdutoModel> getProdutos() {
		return prodRepository.findAll();		
	}

	public ProdutoModel getIdProdutos(Long id) throws ProdutoNotFoundException {
		return prodRepository.findById(id)
				.orElseThrow(() -> new ProdutoNotFoundException("Nao possui no Banco"));		
	}

	public ProdutoModel getNomeProdutos(String nome) throws ProdutoNotFoundException{
		return prodRepository.findByNome(nome)
				.orElseThrow(() -> new ProdutoNotFoundException("Nao possui no Banco"));
	}
	
	public ProdutoModel getPrecoProdutos(BigDecimal preco) throws ProdutoNotFoundException{
		return prodRepository.findByPreco(preco)
				.orElseThrow(() -> new ProdutoNotFoundException("Nao possui no Banco"));
	}
	
	public ProdutoModel saveProdutos(ProdutoModel produto) {
		return prodRepository.save(produto);		
	}
	
	public ProdutoModel putProdutos(Long id, ProdutoModel produtos) throws ProdutoNotFoundException {			
		return prodRepository.findById(id)
				.map(record -> {
					record.setNome(produtos.getNome());
					record.setPreco(produtos.getPreco());
					ProdutoModel updated = prodRepository.save(record);
					return updated;					
				}).orElseThrow(() -> new ProdutoNotFoundException("Nao possui no Banco"));
	}
	
	public ProdutoModel patchProdutos(Long id, ProdutoModel produtos) throws ProdutoNotFoundException{
		return prodRepository.findById(id)
				.map(dados -> {
					if(produtos.getNome() != null) {
						dados.setNome(produtos.getNome());
					}
					if (produtos.getPreco() != null) {
						dados.setPreco(produtos.getPreco());
					}
					ProdutoModel updated = prodRepository.save(dados);
					return updated;
				}).orElseThrow(() -> new ProdutoNotFoundException("Nao possui no Banco de Dados"));
	}
	
	public void deleteProdutosId(Long id) {
		prodRepository.deleteById(id);
	}
	
}
