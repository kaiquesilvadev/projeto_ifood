package com.kaique.ifood.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaique.ifood.entities.Produto;
import com.kaique.ifood.exception.ProdutoNaoEncontradoException;
import com.kaique.ifood.repositories.ProdutoRepositorie;

import jakarta.transaction.Transactional;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepositorie repositorie;

	public Produto buscaPorId(Long id) {
		return repositorie.findById(id).orElseThrow(() -> new ProdutoNaoEncontradoException(id));
	}

	public Produto buscaIdEmRestaurante(Long restauranteId, Long produtoId) {
		return repositorie.buscaProdutoPorIdEmRestaurante(produtoId , restauranteId)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId));
	}
	
	@Transactional 
	public Produto salva(Produto produto) {
		return repositorie.save(produto);
	}
	
	public void deleta(Long id) {
		repositorie.deleteById(id);
	}
}
