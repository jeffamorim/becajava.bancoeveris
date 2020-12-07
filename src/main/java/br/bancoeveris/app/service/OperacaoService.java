package br.bancoEveris.app.service;

import br.bancoEveris.app.model.BaseResponse;
import br.bancoEveris.app.model.Operacao;
import br.bancoEveris.app.request.OperacaoRequest;
import br.bancoEveris.app.request.TransferenciaRequest;

public interface OperacaoService {
	
	double saldo(Long id);
	BaseResponse saque(OperacaoRequest request);
	BaseResponse deposito(OperacaoRequest request);
	BaseResponse transferencia(TransferenciaRequest request);
		
	
	
}