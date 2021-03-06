package br.com.caelum.leilao.servico;


import static br.com.caelum.matcher.LeilaoMatcher.temUmLance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.databuilder.LeilaoDataBuilder;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class AvaliadorTestHamcrest {

	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario jose;
	private Usuario maria;

	@Before
	public void setUp() {
		// executa este m�todo antes da execu��o de cada teste
		// instancia tudo que � necess�rio para o cenario dos testes
		this.leiloeiro = new Avaliador();
		this.joao = new Usuario("Jo�o");
		this.jose = new Usuario("Jos�");
		this.maria = new Usuario("Maria");
	}

	@After
	public void finaliza() {
		// Utilizamos m�todos @After quando nossos testes consomem recursos que precisam
		// ser finalizados.
	}

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		// Parte 1: cenario - 3 lances em ordem crescente
		Leilao leilao = new LeilaoDataBuilder().leilao("Playstation 3 Novo")
				.lance(maria, 250.0)
				.lance(joao, 300.0)
				.lance(jose, 400.0).constroi();

		// Parte 2: executando a acao
		// Avaliador leiloeiro = new Avaliador(); // invocado no m�todo auxiliar @Before
		leiloeiro.avalia(leilao);

		// Parte 3: comparando a saida com o esperado
//		double maiorEsperado = 400;
//		double menorEsperado = 250;
		// afirmar iqualdade do maior esperado com o que o leiloeiro retorna
//		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
//		assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);

		//com Hamcrest - mais leg�vel
		// afirmar que o retorno de leiloeiro � igual a 400.0
		assertThat(leiloeiro.getMaiorLance(), equalTo(400.0));
		assertThat(leiloeiro.getMenorLance(), equalTo(250.0));

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

//		assertEquals(3, maiores.size());
//		assertEquals(700.0, maiores.get(0).getValor(), 0.00001);
//		assertEquals(400.0, maiores.get(1).getValor(), 0.00001);
//		assertEquals(300.0, maiores.get(2).getValor(), 0.00001);
		
		//com Hamcrest - mais leg�vel
		assertThat(maiores.size(), equalTo(3));
		assertThat(maiores, hasItems(
                new Lance(joao, 700), 
                new Lance(maria, 400),
                new Lance(joao, 300)
        ));
	}
	
	@Test
    public void deveReceberUmLance() {
        Leilao leilao = new LeilaoDataBuilder().leilao("Macbook Pro 15").constroi();
        assertThat(leilao.getLances().size(), equalTo(0));

        Lance lance = new Lance(joao, 2000);
        leilao.propoe(lance);

        assertThat(leilao.getLances().size(), equalTo(1));
        // uso de matcher customizado
        assertThat(leilao, temUmLance(lance));
    }
}