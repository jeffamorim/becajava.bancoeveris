package br.bancoEveris.app.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.bancoEveris.app.model.BaseResponse;
import br.bancoEveris.app.model.Conta;
import br.bancoEveris.app.model.Operacao;
import br.bancoEveris.app.repository.ContaRepository;
import br.bancoEveris.app.repository.OperacaoRepository;
import br.bancoEveris.app.request.OperacaoRequest;
import br.bancoEveris.app.request.TransferenciaRequest;
import br.bancoEveris.app.service.OperacaoService;

@Service
public class OperacaoServiceImp implements OperacaoService {

	@Autowired
	private OperacaoRepository _operacaoRepository;
	@Autowired
	private ContaRepository _contaRepository;

	public double saldo(Long id) {
		double saldo = 0;

		Conta contaOrigem = new Conta();
		contaOrigem.setId(id);

		Conta contaDestino = new Conta();
		contaDestino.setId(id);

		List<Operacao> listaOrigem = _operacaoRepository.findByContaOrigem(contaOrigem);
		List<Operacao> listaDestino = _operacaoRepository.findByContaDestino(contaDestino);

		for (Operacao o : listaOrigem) {
			switch (o.getTipo()) {
			case "D":
				saldo += o.getValor();
				break;
			case "S":
				saldo -= o.getValor();
				break;
			case "T":
				saldo -= o.getValor();
				break;
			default:
				break;
			}
		}

		for (Operacao o : listaDestino) {
			switch (o.getTipo()) {
			case "D":
				saldo += o.getValor();
				break;
			case "S":
				saldo -= o.getValor();
				break;
			case "T":
				saldo += o.getValor();
				break;
			default:
				break;
			}
		}
		return saldo;
	}

	public BaseResponse saque(OperacaoRequest request) {

		BaseResponse base = new BaseResponse();
		Operacao operacao = new Operacao();

		Conta conta = _contaRepository.findByHash(request.getHash());

		if (conta == null) {
			base.statusCode = 404;
			base.message = "Hash não encontrado!";
			return base;
		}

		if (request.getValor() <= 0) {
			base.statusCode = 404;
			base.message = "Valor não informado";
			return base;
		}

		operacao.setTipo("S");
		operacao.setValor(request.getValor());
		operacao.setContaOrigem(conta);

		_operacaoRepository.save(operacao);

		base.statusCode = 200;
		base.message = "Saque realizado com sucesso";
		return base;

	}

	public BaseResponse deposito(OperacaoRequest request) {

		BaseResponse base = new BaseResponse();
		Operacao operacao = new Operacao();

		Conta conta = _contaRepository.findByHash(request.getHash());

		if (conta == null) {
			base.statusCode = 404;
			base.message = "Hash não encontrado!";
			return base;
		}

		if (request.getValor() <= 0) {
			base.statusCode = 404;
			base.message = "Valor não informado";
			return base;
		}

		operacao.setTipo("D");
		operacao.setValor(request.getValor());
		operacao.setContaDestino(conta);

		_operacaoRepository.save(operacao);

		base.statusCode = 200;
		base.message = "Deposito realizado com sucesso";
		return base;

	}
	
	
		public BaseResponse transferencia(TransferenciaRequest request) {
		
		BaseResponse base = new BaseResponse();
		Operacao operacao = new Operacao();
		
		
		Conta destino = _contaRepository.findByHash(request.getHashDestino());
		Conta origem = _contaRepository.findByHash(request.getHashOrigem());
		
		if(destino == null) {
			base.statusCode = 404;
			base.message = "Conta destino não encontrada!";
			return base;
		}
		
		if(origem == null) {
			base.statusCode = 404;
			base.message = "Conta origem não encontrada!";
			return base;
		}
		
		if(request.getValor() <= 0) {
			base.statusCode = 404;
			base.message = "Valor não informado";
			return base;
		}
		
		operacao.setTipo("T");
		operacao.setValor(request.getValor());
		operacao.setContaOrigem(origem);
		operacao.setContaDestino(destino);
		
		_operacaoRepository.save(operacao);
		
		base.statusCode = 200;
		base.message = "Transferencia realizada com sucesso";
		return base;
		
		}

}
