package br.com.caelum.leilao.databuilder;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class LeilaoDataBuilder {
	private Leilao leilao;

    public LeilaoDataBuilder() { }

    public LeilaoDataBuilder leilao(String descricao) {
        this.leilao = new Leilao(descricao);
        return this;
    }

    public LeilaoDataBuilder lance(Usuario usuario, double valor) {
        leilao.propoe(new Lance(usuario, valor));
        return this;
    }

    public Leilao constroi() { 
        return leilao;
    }
}
