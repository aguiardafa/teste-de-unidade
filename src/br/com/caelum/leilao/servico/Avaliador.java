package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {
	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	private double menorDeTodos = Double.POSITIVE_INFINITY;
	private double mediaDeTodos = 0;

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
}
