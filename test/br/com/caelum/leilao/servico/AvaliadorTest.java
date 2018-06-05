package br.com.caelum.leilao.servico;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class AvaliadorTest {
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		// cenario: 3 lances em ordem crescente
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 3 Novo");

		leilao.propoe(new Lance(maria, 250.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(jose, 400.0));

		// executando a acao
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		// comparando a saida com o esperado
		double maiorEsperado = 400;
		double menorEsperado = 250;

		Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
		Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
	}

	@Test
	public void deveEntenderLancesEmOrdemDecrescente() {
		// cenario: 3 lances em ordem decrescente
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 3 Novo");

		leilao.propoe(new Lance(jose, 400.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(maria, 250.0));

		// executando a acao
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		// comparando a saida com o esperado
		double maiorEsperado = 400;
		double menorEsperado = 250;

		Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
		Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
	}

	@Test
	public void deveCalcularMediaDosLancesArredondada() {
		// cenario: 3 lances
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 3 Novo");

		leilao.propoe(new Lance(jose, 400.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(maria, 250.0));

		// executando a acao
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		// comparando a saida com o esperado
		double mediaEsperada = (400.0 + 300.0 + 250.0) / 3;

		Assert.assertEquals(mediaEsperada, leiloeiro.getMediaDosLances(), 0.0001);
	}

	@Test
	public void deveCalcularMediaDosLancesExata() {
		// cenario: 3 lances
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 3 Novo");

		leilao.propoe(new Lance(jose, 300.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(maria, 300.0));

		// executando a acao
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		// comparando a saida com o esperado
		double mediaEsperada = 300;

		Assert.assertEquals(mediaEsperada, leiloeiro.getMediaDosLances(), 0.0001);
	}

	@Test
	public void testaMediaDeZeroLance() {

		// cenario
		Usuario ewertom = new Usuario("Ewertom");

		// acao
		Leilao leilao = new Leilao("Iphone 7");

		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);

		// validacao
		Assert.assertEquals(0, avaliador.getMediaDosLances(), 0.0001);

	}

	@Test
	public void testaMediaDeLanceUnico() {

		// cenario
		Usuario ewertom = new Usuario("Ewertom");
		Leilao leilao = new Leilao("Iphone 7");
		leilao.propoe(new Lance(ewertom, 300.0));

		// acao
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);

		// validacao
		Assert.assertEquals(300.0, avaliador.getMediaDosLances(), 0.0001);

	}
}