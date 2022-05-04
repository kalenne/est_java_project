package com.banco.produtos.crm.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.banco.produtos.crm.model.ProdutoModel;
import com.banco.produtos.crm.repository.ProdutoRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="api")
@RestController
public class ProdutoController<U> {
	
	@Autowired
	private ProdutoRepository prodRepository;
	
	@ApiOperation(value = "Retorna todos os produtos")
	@GetMapping(path = "/produtos")
	public List<ProdutoModel> getProdutos() {
		return prodRepository.findAll();
	}
	
	@ApiOperation(value = "Retorna o produto de acordo com a Id")
	@GetMapping(path="/produtos/{id}")
	public ProdutoModel getIdProdutos(@PathVariable("id") Long id) {
		return prodRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
	}
	
	@ApiOperation(value = "Retorna o produto de acordo com o Nome")
	@GetMapping(value="/produtos/nome")
	public ResponseEntity<List<ProdutoModel>> getNomeProdutos(@RequestParam(value="nome") String nome){
		return new ResponseEntity<List<ProdutoModel>>(prodRepository.findByNome(nome), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Retorna o produto de acordo com o Preco")
	@GetMapping(path="/produtos/preco")
	public ResponseEntity<List<ProdutoModel>> getPrecoProdutos(@RequestParam(value="preco") BigDecimal preco){
		return new ResponseEntity<List<ProdutoModel>>(prodRepository.findByPreco(preco), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Cadastrar produto")
	@PostMapping(path = "/produtos")
	public ProdutoModel saveProdutos(@RequestBody ProdutoModel produto) {
		return prodRepository.save(produto);
	}
	
	@ApiOperation(value = "Edita todos os dados do produto pelo Id")
	@PutMapping(value = "/produtos/{id}")
	public ResponseEntity<ProdutoModel> putProdutos(@PathVariable("id") Long id, @RequestBody ProdutoModel produtos) {
		return prodRepository.findById(id)
		.map(record -> {
			record.setNome(produtos.getNome());
			record.setPreco(produtos.getPreco());
			ProdutoModel updated = prodRepository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(null);
	}
	
	@ApiOperation(value = "Edita parcialmente os dados do produto pelo Id")
	@PatchMapping(value="/produtos/{id}")
	public ResponseEntity<ProdutoModel> patchProdutos(@PathVariable("id") Long id, @RequestBody ProdutoModel produtos){
		return prodRepository.findById(id)
				.map(dados -> {
					if(produtos.getNome() != null) {
						dados.setNome(produtos.getNome());
					}
					if (produtos.getPreco() != null) {
						dados.setPreco(produtos.getPreco());
					}
					
					ProdutoModel updated = prodRepository.save(dados);
					return ResponseEntity.ok().body(updated);
				}).orElse(null);
	}
	
	@ApiOperation(value = "Deleta o produto pelo Id")
	@DeleteMapping(path = "/produtos/{id}")
	public void deleteProdutosId(@PathVariable("id") Long id) {
		prodRepository.deleteById(id);
	}
	
	@ApiOperation(value = "Deleta todos os dados")
	@DeleteMapping(path="/produtos/all")
	public void deleteTodosProdutos() {
		prodRepository.deleteAll();
	}
}
