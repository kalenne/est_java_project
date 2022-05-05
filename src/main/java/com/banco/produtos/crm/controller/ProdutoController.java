package com.banco.produtos.crm.controller;

import java.math.BigDecimal;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banco.produtos.crm.exception.ProdutoNotFoundException;
import com.banco.produtos.crm.model.ProdutoModel;
import com.banco.produtos.crm.service.ProdutoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="api")
@RestController
public class ProdutoController<U> {
	
	@Autowired
	private ProdutoService produtoService;
	
	@ApiOperation(value = "Retorna todos os produtos")
	@GetMapping(path = "/produtos")
	public ResponseEntity<List<ProdutoModel>> getProdutos(){
		return new ResponseEntity<List<ProdutoModel>>(produtoService.getProdutos(), HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "Retorna o produto de acordo com a Id")
	@GetMapping(path="/produtos/{id}")
	public ResponseEntity<ProdutoModel> getIdProdutos(@PathVariable("id") Long id) throws ProdutoNotFoundException{
		ProdutoModel prodModel = produtoService.getIdProdutos(id);
		return new ResponseEntity<>(prodModel, HttpStatus.OK);
				
	}
	
	@ApiOperation(value = "Retorna o produto de acordo com o Nome")
	@GetMapping(value="/produtos/nome")
	public ResponseEntity<ProdutoModel> getNomeProdutos(@RequestParam(value="nome") String nome) throws ProdutoNotFoundException {
		ProdutoModel prodModel = produtoService.getNomeProdutos(nome);
		return new ResponseEntity<>(prodModel, HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "Retorna o produto de acordo com o Preco")
	@GetMapping(path="/produtos/preco")
	public ResponseEntity<ProdutoModel> getPrecoProdutos(@RequestParam(value="preco") BigDecimal preco) throws ProdutoNotFoundException {
		ProdutoModel prodModel = produtoService.getPrecoProdutos(preco);
		return new ResponseEntity<>(prodModel, HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "Cadastrar produto")
	@PostMapping(path = "/produtos")
	public ResponseEntity<ProdutoModel> saveProdutos(@RequestBody ProdutoModel produto){
		ProdutoModel prodModel = produtoService.saveProdutos(produto);
		return new ResponseEntity<>(prodModel, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Edita todos os dados do produto pelo Id")
	@PutMapping(value = "/produtos/{id}")
	public ResponseEntity<ProdutoModel> putProdutos(@PathVariable("id") Long id, @RequestBody ProdutoModel produto) throws ProdutoNotFoundException{
		ProdutoModel prodModel = produtoService.putProdutos(id, produto);
		return new ResponseEntity<>(prodModel, HttpStatus.ACCEPTED);
	}
	
	
	@ApiOperation(value = "Edita parcialmente os dados do produto pelo Id")
	@PatchMapping(value="/produtos/{id}")
	public ResponseEntity<ProdutoModel> patchProdutos(@PathVariable("id") Long id, @RequestBody ProdutoModel produto) throws ProdutoNotFoundException{
		ProdutoModel prodModel = produtoService.patchProdutos(id, produto);
		return new ResponseEntity<>(prodModel, HttpStatus.ACCEPTED);
	}
	
	@ApiOperation(value = "Deleta o produto pelo Id")
	@DeleteMapping(path = "/produtos/{id}")
	public ResponseEntity<Void> deleteProdutosId (@PathVariable("id") Long id) {
		produtoService.deleteProdutosId(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}
