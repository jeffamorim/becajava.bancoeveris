package br.bancoEveris.app.service;

import br.bancoEveris.app.model.BaseResponse;
import br.bancoEveris.app.request.ContaRequest;
import br.bancoEveris.app.response.ContaResponse;
import br.bancoEveris.app.response.ListContaResponse;

public interface ContaService {

	public BaseResponse inserir();

	public ContaResponse  obter(Long id);

	public ListContaResponse listar();

	public BaseResponse editar(Long id, ContaRequest contaRequest);

	public BaseResponse deletar(Long id);

	public ContaResponse saldo(String hash);

}
