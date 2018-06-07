package br.com.caelum.leilao.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.databuilder.LeilaoDataBuilder;

public class LanceTest {
	private Usuario usuarioTest;

	@Before
	public void setUp() {
		this.usuarioTest = new Usuario("Usuario de Test");
	}
	
	@Test
	public void deveInstanciarLanceComValorPositivo() {
			// Parte 1: cenario
			Leilao leilao = new LeilaoDataBuilder().leilao("Playstation 3 Novo").constroi();

			// Parte 2: executando a acao
			leilao.propoe(new Lance(usuarioTest, 10));

			// Parte 3: comparando a saida com o esperado
			assertEquals(10, leilao.getLances().get(0).getValor());
	}

	@Test
	public void naoDeveInstanciarLanceComValorZerado() {
		// modelo usando fail
		try {
			// Parte 1: cenario
			Leilao leilao = new LeilaoDataBuilder().leilao("Playstation 3 Novo").constroi();

			// Parte 2: executando a acao
			leilao.propoe(new Lance(usuarioTest, 0));

			// Parte 3: comparando a saida com o esperado
			Assert.fail();
		} catch (IllegalArgumentException e) {
			// deu certo!
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void naoDeveInstanciarLanceComValorZeradoJUnit4() {
		// a partir do JUnit4
		// Caso a exceção não seja lançada ou não bata com a informada, o teste falhará.
		Leilao leilao = new LeilaoDataBuilder().leilao("Playstation 3 Novo").lance(usuarioTest, 0).constroi();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void naoDeveInstanciarLanceComValorNegativoJUnit4() {
		// a partir do JUnit4
		// Caso a exceção não seja lançada ou não bata com a informada, o teste falhará.
		Leilao leilao = new LeilaoDataBuilder().leilao("Playstation 3 Novo").lance(usuarioTest, -10).constroi();
	}
}
