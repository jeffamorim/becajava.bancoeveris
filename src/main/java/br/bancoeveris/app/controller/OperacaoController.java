package br.bancoEveris.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.bancoEveris.app.model.BaseResponse;

import br.bancoEveris.app.request.OperacaoRequest;

import br.bancoEveris.app.request.TransferenciaRequest;
import br.bancoEveris.app.service.OperacaoService;
import br.bancoEveris.app.service.imp.OperacaoServiceImp;

@RestController
@RequestMapping("/operacoes")
public class OperacaoController extends BaseController {

	@Autowired
	private OperacaoService _service;

	public OperacaoController(OperacaoServiceImp service) {
		_service = service;
	}

	@PostMapping(path = "/deposito")
	public ResponseEntity<?> deposito(@RequestBody OperacaoRequest request) {
		try {

			BaseResponse base = _service.deposito(request);
			return ResponseEntity.status(base.statusCode).body(base);

		} catch (Exception e) {
			return ResponseEntity.status(errorBase.statusCode).body(errorBase);
		}
	}

	@PostMapping(path = "/saque")
	public ResponseEntity<?> saque(@RequestBody OperacaoRequest request) {
		try {

			BaseResponse base = _service.saque(request);
			return ResponseEntity.status(base.statusCode).body(base);

		} catch (Exception e) {
			return ResponseEntity.status(errorBase.statusCode).body(errorBase);
		}

	}

	@PostMapping(path = "/transferencia")
	public ResponseEntity<?> transferencia(@RequestBody TransferenciaRequest request) {
		try {

			BaseResponse base = _service.transferencia(request);
			return ResponseEntity.status(base.statusCode).body(base);

		} catch (Exception e) {
			return ResponseEntity.status(errorBase.statusCode).body(errorBase);
		}
	}
}
