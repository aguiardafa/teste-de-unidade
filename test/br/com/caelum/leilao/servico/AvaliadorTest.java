package br.com.caelum.leilao.servico;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.databuilder.LeilaoDataBuilder;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class AvaliadorTest {

	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario jose;
	private Usuario maria;

	@Before
	public void setUp() {
		// executa este método antes da execução de cada teste
		// instancia tudo que é necessário para o cenario dos testes
		this.leiloeiro = new Avaliador();
		this.joao = new Usuario("João");
		this.jose = new Usuario("José");
		this.maria = new Usuario("Maria");
	}

	@After
	public void finaliza() {
		// Utilizamos métodos @After quando nossos testes consomem recursos que precisam
		// ser finalizados.
	}

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		// Parte 1: cenario - 3 lances em ordem crescente
		Leilao leilao = new LeilaoDataBuilder().leilao("Playstation 3 Novo").lance(maria, 250.0).lance(joao, 300.0)
				.lance(jose, 400.0).constroi();

		// Parte 2: executando a acao
		// Avaliador leiloeiro = new Avaliador(); // invocado no método auxiliar @Before
		leiloeiro.avalia(leilao);

		// Parte 3: comparando a saida com o esperado
		double maiorEsperado = 400;
		double menorEsperado = 250;

		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
		assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
	}

	@Test
	public void deveEntenderLancesEmOrdemDecrescente() {
		Leilao leilao = new LeilaoDataBuilder().leilao("Playstation 3 Novo")
				.lance(jose, 400.0)
				.lance(joao, 300.0)
				.lance(maria, 250.0).constroi();

		// executando a acao
		leiloeiro.avalia(leilao);

		// comparando a saida com o esperado
		double maiorEsperado = 400;
		double menorEsperado = 250;

		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
		assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
	}

	@Test
	public void deveEntenderLancesSemOrdemDefinida() {
		// cenario
		Leilao leilao = new LeilaoDataBuilder().leilao("Playstation 3 Novo")
				.lance(jose, 290.0)
				.lance(maria, 250.0)
				.lance(joao, 270.0)
				.lance(maria, 285.0)
				.lance(joao, 300.0)
				.lance(jose, 400.0).constroi();

		// executando a acao
		leiloeiro.avalia(leilao);

		// comparando a saida com o esperado
		double maiorEsperado = 400;
		double menorEsperado = 250;

		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
		assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
	}

	@Test
	public void deveCalcularMediaDosLancesArredondada() {
		// cenario
		Leilao leilao = new LeilaoDataBuilder().leilao("Playstation 3 Novo")
				.lance(jose, 400.0)
				.lance(joao, 300.0)
				.lance(maria, 250.0).constroi();

		// executando a acao
		leiloeiro.avalia(leilao);

		// comparando a saida com o esperado
		double mediaEsperada = (400.0 + 300.0 + 250.0) / 3;

		assertEquals(mediaEsperada, leiloeiro.getMediaDosLances(), 0.0001);
	}

	@Test
	public void deveCalcularMediaDosLancesExata() {
		// cenario
		Leilao leilao = new LeilaoDataBuilder().leilao("Playstation 3 Novo")
				.lance(jose, 300.0)
				.lance(joao, 300.0)
				.lance(maria, 300.0).constroi();

		// executando a acao
		leiloeiro.avalia(leilao);

		// comparando a saida com o esperado
		double mediaEsperada = 300;

		assertEquals(mediaEsperada, leiloeiro.getMediaDosLances(), 0.0001);
	}

	@Test
	public void deveCalcularMediaDeLanceUnico() {
		// cenario
		Leilao leilao = new LeilaoDataBuilder().leilao("Playstation 3 Novo")
				.lance(joao, 300.0).constroi();

		// acao
		leiloeiro.avalia(leilao);

		// validacao
		assertEquals(300.0, leiloeiro.getMediaDosLances(), 0.0001);
	}

	@Test
	public void deveEntenderLanceUnico() {
		// cenario
		Leilao leilao = new LeilaoDataBuilder().leilao("Playstation 3 Novo")
				.lance(joao, 300.0).constroi();

		// acao
		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();

		// validacao
		assertEquals(300.0, leiloeiro.getMediaDosLances(), 0.0001);
		assertEquals(300.0, leiloeiro.getMaiorLance(), 0.0001);
		assertEquals(300.0, leiloeiro.getMenorLance(), 0.0001);

		assertEquals(1, maiores.size());
		assertEquals(300.0, maiores.get(0).getValor(), 0.00001);
	}

	@Test
	public void deveEncontrarOsTresMaioresLances() {
		Leilao leilao = new LeilaoDataBuilder().leilao("Playstation 3 Novo")
				.lance(joao, 200.0)
				.lance(maria, 100.0)
				.lance(joao, 300.0)
				.lance(maria, 400.0)
				.lance(joao, 700.0).constroi();

		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();

		assertEquals(3, maiores.size());
		assertEquals(700.0, maiores.get(0).getValor(), 0.00001);
		assertEquals(400.0, maiores.get(1).getValor(), 0.00001);
		assertEquals(300.0, maiores.get(2).getValor(), 0.00001);
	}

	@Test
	public void deveEncontrarOsTresMaioresEmDoisLances() {
		Leilao leilao = new LeilaoDataBuilder().leilao("Playstation 3 Novo")
				.lance(joao, 200.0)
				.lance(maria, 400.0).constroi();

		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();

		assertEquals(2, maiores.size());
		assertEquals(400.0, maiores.get(0).getValor(), 0.00001);
		assertEquals(200.0, maiores.get(1).getValor(), 0.00001);
	}

	@Test
	public void deveEncontrarOsTresMaioresEmLanceUnico() {
		Leilao leilao = new LeilaoDataBuilder().leilao("Playstation 3 Novo")
				.lance(joao, 200.0).constroi();

		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();

		assertEquals(1, maiores.size());
		assertEquals(200.0, maiores.get(0).getValor(), 0.00001);
	}

	@Test
	public void naoDeveAvaliarLeiloesSemNenhumLanceDado() {
		// modelo usando fail
		try {
			Leilao leilao = new LeilaoDataBuilder().leilao("Playstation 3 Novo").constroi();

			leiloeiro.avalia(leilao);
			Assert.fail();
		} catch (RuntimeException e) {
			// deu certo!
		}
	}

	@Test(expected = RuntimeException.class)
	public void naoDeveAvaliarLeiloesSemNenhumLanceDadoJUnit4() {
		// a partir do JUnit4
		// Caso a exceção não seja lançada ou não bata com a informada, o teste falhará.
		Leilao leilao = new LeilaoDataBuilder().leilao("Playstation 3 Novo").constroi();

		leiloeiro.avalia(leilao);
	}
}