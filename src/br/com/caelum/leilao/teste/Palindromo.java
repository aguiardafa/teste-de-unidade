package br.com.caelum.leilao.teste;

import java.text.Normalizer;

public class Palindromo {
	public boolean ehPalindromo(String frase) {

		// para remover pontuações
		String fraseFiltrada = frase.toUpperCase().replace(" ", "").replace("-", "").replace(",", "").replace(".", "");
		// para remover acentos dos caracteres
		fraseFiltrada = Normalizer.normalize(fraseFiltrada, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

		for (int i = 0; i < fraseFiltrada.length(); i++) {
			if (fraseFiltrada.charAt(i) != fraseFiltrada.charAt(fraseFiltrada.length() - (i + 1))) {
				return false;
			}
			if (i >= fraseFiltrada.length() / 2)
				return true; // se já verificou até a metade e não acusou diferença a frase é palindromo
		}

		return true;
	}
}
