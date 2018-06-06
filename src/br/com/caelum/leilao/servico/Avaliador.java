package br.com.caelum.leilao.servico;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {
	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	private double menorDeTodos = Double.POSITIVE_INFINITY;
	private double mediaDeTodos = 0;
	private List<Lance> tresMaiores;

	public void avalia(Leilao leilao) {

		for (Lance lance : leilao.getLances()) {
			if (lance.getValor() > maiorDeTodos) {
				this.maiorDeTodos = lance.getValor();
			}
			if (lance.getValor() < menorDeTodos) {
				this.menorDeTodos = lance.getValor();
			}
		}
		if (!leilao.getLances().isEmpty()) {
			this.mediaDeTodos = leilao.getLances().stream().mapToDouble(Lance::getValor).average().getAsDouble();
		}
		pegaOsMaioresNo(leilao);
	}

	private void pegaOsMaioresNo(Leilao leilao) {
		List<Lance> maiores = new ArrayList<Lance>(leilao.getLances());
		maiores = maiores.stream().sorted(Comparator.comparing(Lance::getValor).reversed()).collect(Collectors.toList());
		this.tresMaiores = maiores.subList(0, maiores.size() > 3 ? 3 : maiores.size());
	}

	public double getMaiorLance() {
		return maiorDeTodos;
	}

	public double getMenorLance() {
		return menorDeTodos;
	}

	public double getMediaDosLances() {
		return mediaDeTodos;
	}

	public List<Lance> getTresMaiores() {
		return this.tresMaiores;
	}
}
