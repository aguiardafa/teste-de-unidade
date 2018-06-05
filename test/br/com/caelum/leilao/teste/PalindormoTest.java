package br.com.caelum.leilao.teste;

import org.junit.Assert;
import org.junit.Test;

public class PalindormoTest {
	@Test
	public void testePalavraPalindormo() {
		// cenario:
		String palavra = "arara";

		// executando a acao
		Palindromo palindromo = new Palindromo();
		boolean resultado = palindromo.ehPalindromo(palavra);

		// comparando a saida com o esperado
		boolean esperado = true;
		Assert.assertEquals(esperado, resultado);
		Assert.assertTrue(resultado);
	}
	
	@Test
	public void testePalavraPalindormo3() {
		// cenario:
		String palavra = "esse";

		// executando a acao
		Palindromo palindromo = new Palindromo();
		boolean resultado = palindromo.ehPalindromo(palavra);

		// comparando a saida com o esperado
		boolean esperado = true;
		Assert.assertEquals(esperado, resultado);
		Assert.assertTrue(resultado);
	}
	@Test
	public void testePalavraNaoPalindormo() {
		// cenario:
		String palavra = "abacate";

		// executando a acao
		Palindromo palindromo = new Palindromo();
		boolean resultado = palindromo.ehPalindromo(palavra);

		// comparando a saida com o esperado
		boolean esperado = false;
		Assert.assertEquals(esperado, resultado);
	}
	@Test
	public void testeFrasePalindormo() {
		// cenario:
		String frase = "Anotaram a data da maratona";

		// executando a acao
		Palindromo palindromo = new Palindromo();
		boolean resultado = palindromo.ehPalindromo(frase);

		// comparando a saida com o esperado
		boolean esperado = true;
		Assert.assertEquals(esperado, resultado);
	}
	@Test
	public void testeFrasePalindormo2() {
		// cenario:
		String frase = "Socorram-me, subi no ônibus em Marrocos";

		// executando a acao
		Palindromo palindromo = new Palindromo();
		boolean resultado = palindromo.ehPalindromo(frase);

		// comparando a saida com o esperado
		boolean esperado = true;
		Assert.assertEquals(esperado, resultado);
	}
	@Test
	public void testeFrasePalindormo3() {
		// cenario:
		String frase = "Laço bacana para panaca boçal";

		// executando a acao
		Palindromo palindromo = new Palindromo();
		boolean resultado = palindromo.ehPalindromo(frase);

		// comparando a saida com o esperado
		boolean esperado = true;
		Assert.assertEquals(esperado, resultado);
	}
	@Test
	public void testeFraseNaoPalindormo() {
		// cenario:
		String frase = "Anotaram a data do casamento";

		// executando a acao
		Palindromo palindromo = new Palindromo();
		boolean resultado = palindromo.ehPalindromo(frase);

		// comparando a saida com o esperado
		boolean esperado = false;
		Assert.assertEquals(esperado, resultado);
	}
}
