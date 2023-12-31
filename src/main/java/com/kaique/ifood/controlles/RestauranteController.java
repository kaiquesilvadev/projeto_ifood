package com.kaique.ifood.controlles;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kaique.ifood.dto.conversor.RestauranteDtoConversor;
import com.kaique.ifood.dto.request.RestaurantesDtoRequest;
import com.kaique.ifood.dto.responce.RestauranteResumoDtoResponce;
import com.kaique.ifood.entities.Restaurante;
import com.kaique.ifood.services.RestauranteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteService service;

	@Autowired
	private RestauranteDtoConversor conversor;

	@GetMapping
	public Page<RestauranteResumoDtoResponce> listar(Pageable pageable) {
		Page<Restaurante> pageRestaurante = service.listar(pageable);
	  	List<RestauranteResumoDtoResponce> restaurante = conversor.listaDto(pageRestaurante.getContent());
	  	return new PageImpl<>(restaurante, pageable , pageRestaurante.getTotalElements());
	}

	@GetMapping("/{id}")
	public Restaurante buscaPorId(@PathVariable Long id) {
		return service.buscaPorId(id);
	}

	@GetMapping("/filtroTaxa/por-taxa-frete")
	public List<Restaurante> filtraPorTaxas(BigDecimal taxaInicial, @RequestParam BigDecimal taxaFinal) {
		return service.filtraPorTaxas(taxaInicial, taxaFinal);
	}

	/*
	 * @GetMapping("/filtra/nome-e-id") public ResponseEntity<List<Restaurante>>
	 * buscaPorNomeEIdDeCozinha(String nome, BigDecimal id) { return
	 * ResponseEntity.ok().body(service.buscaPorNomeEIdDeCozinha(nome, id)); }
	 */

	@GetMapping("/filtra/por-nome-e-frete")
	public List<Restaurante> buscaRTTPorNomeFrete(String nome, @RequestParam BigDecimal taxaFreteInicia,
			BigDecimal taxaFreteFinal) {
		return service.buscaRTTPorNomeFrete(nome, taxaFreteInicia, taxaFreteFinal);
	}

	@GetMapping("/filtra/com-frete-gratis")
	public List<Restaurante> restaurantesComFreteGratis(String nome) {
		return service.restaurantesComFreteGratis(nome);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Restaurante adiciona(@Valid @RequestBody RestaurantesDtoRequest restauranteDto) {
		return service.adiciona(restauranteDto);
	}

	@PutMapping("/{restauranteId}")
	public Restaurante atualiza(@PathVariable Long restauranteId,
			@Valid @RequestBody RestaurantesDtoRequest restaurante) {
		return service.atualiza(restauranteId, restaurante);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{restauranteId}/ativa")
	public void ativa(@PathVariable Long restauranteId) {
		service.ativa(restauranteId);

	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{restauranteId}/desativa")
	public void desativa(@PathVariable Long restauranteId) {
		service.desativa(restauranteId);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		service.deletar(id);
	}
}
