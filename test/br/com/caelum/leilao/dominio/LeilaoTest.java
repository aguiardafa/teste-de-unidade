package br.com.caelum.leilao.dominio;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.leilao.databuilder.LeilaoDataBuilder;

public class LeilaoTest {
	private Usuario steveJobs;
	private Usuario billGates;
	private Leilao leilao;
	
	@Before
	public void setUp() {
		// executa este método antes da execução de cada teste
		// instancia tudo que é necessário para o cenario dos testes
		this.steveJobs = new Usuario("Steve Jobs");
		this.billGates = new Usuario("Bill Gates");
		this.leilao = new LeilaoDataBuilder().leilao("Macbook Pro 15").constroi();
	}
	
	@After
	public void finaliza() {
		System.out.println("fim");
		// Utilizamos métodos @After quando nossos testes consomem recursos que precisam
		// ser finalizados.
	}
	
	@BeforeClass
	public static void testandoBeforeClass() {
	  System.out.println("before class");
	}

	@AfterClass
	public static void testandoAfterClass() {
	  System.out.println("after class");
	}
	
	@Test
	public void deveReceberUmLance() {
		assertEquals(0, leilao.getLances().size());

		leilao.propoe(new Lance(steveJobs, 2000));

		assertEquals(1, leilao.getLances().size());
		assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
	}

	@Test
	public void deveReceberVariosLances() {
		leilao.propoe(new Lance(steveJobs, 2000));
		leilao.propoe(new Lance(billGates, 3000));

		assertEquals(2, leilao.getLances().size());
		assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
		assertEquals(3000, leilao.getLances().get(1).getValor(), 0.00001);
	}

	// Agora implementaremos duas novas regras de negócio no processo de lances em
	// um leilão:
	// - Uma pessoa não pode propor dois lances em sequência;
	// - Uma pessoa não pode dar mais do que cinco lances no mesmo leilão.
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		leilao.propoe(new Lance(steveJobs, 2000));
		leilao.propoe(new Lance(steveJobs, 3000));

		assertEquals(1, leilao.getLances().size());
		assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
	}

	@Test
	public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
		leilao.propoe(new Lance(steveJobs, 2000));
		leilao.propoe(new Lance(billGates, 3000));
		leilao.propoe(new Lance(steveJobs, 4000));
		leilao.propoe(new Lance(billGates, 5000));
		leilao.propoe(new Lance(steveJobs, 6000));
		leilao.propoe(new Lance(billGates, 7000));
		leilao.propoe(new Lance(steveJobs, 8000));
		leilao.propoe(new Lance(billGates, 9000));
		leilao.propoe(new Lance(steveJobs, 10000));
		leilao.propoe(new Lance(billGates, 11000));

		// deve ser ignorado
		leilao.propoe(new Lance(steveJobs, 12000));

		assertEquals(10, leilao.getLances().size());
		int ultimo = leilao.getLances().size() - 1;
		Lance ultimoLance = leilao.getLances().get(ultimo);
		assertEquals(11000.0, ultimoLance.getValor(), 0.00001);
	}
	@Test
	public void deveDobrarUltimoLanceDeUmUsuario() {
		leilao.propoe(new Lance(steveJobs, 2000));
		leilao.propoe(new Lance(billGates, 3000));

		// deve dobra ultimo lance do usuário
		leilao.dobraLance(steveJobs);

		assertEquals(3, leilao.getLances().size());
		int ultimo = leilao.getLances().size() - 1;
		Lance ultimoLance = leilao.getLances().get(ultimo);
		assertEquals(4000.0, ultimoLance.getValor(), 0.00001);
		assertEquals(steveJobs, ultimoLance.getUsuario());
	}
	@Test
	public void naoDeveDobrarUltimoLanceDeUmUsuarioSemLance() {
		leilao.propoe(new Lance(billGates, 3000));

		// nao deve dobra ultimo lance do usuário que não fez lance
		leilao.dobraLance(steveJobs);

		assertEquals(1, leilao.getLances().size());
		int ultimo = leilao.getLances().size() - 1;
		Lance ultimoLance = leilao.getLances().get(ultimo);
		assertEquals(3000.0, ultimoLance.getValor(), 0.00001);
		assertEquals(billGates, ultimoLance.getUsuario());
	}
	
	@Test
	public void naoDeveDobrarLanceDeUmUsuarioQueFezUltimoLance() {
		leilao.propoe(new Lance(steveJobs, 2000));
		leilao.propoe(new Lance(billGates, 3000));

		// nao deve dobra lance do usuário que fez ultimo lance
		leilao.dobraLance(billGates);

		assertEquals(2, leilao.getLances().size());
		int ultimo = leilao.getLances().size() - 1;
		Lance ultimoLance = leilao.getLances().get(ultimo);
		assertEquals(3000.0, ultimoLance.getValor(), 0.00001);
		assertEquals(billGates, ultimoLance.getUsuario());
	}
	@Test
	public void naoDeveDobrarLanceDeUmUsuarioQueFezMaisDoQue5Lances() {
		leilao.propoe(new Lance(steveJobs, 2000));
		leilao.propoe(new Lance(billGates, 3000));
		leilao.propoe(new Lance(steveJobs, 4000));
		leilao.propoe(new Lance(billGates, 5000));
		leilao.propoe(new Lance(steveJobs, 6000));
		leilao.propoe(new Lance(billGates, 7000));
		leilao.propoe(new Lance(steveJobs, 8000));
		leilao.propoe(new Lance(billGates, 9000));
		leilao.propoe(new Lance(steveJobs, 10000));
		leilao.propoe(new Lance(billGates, 11000));

		// nao deve dobra lance do usuário que fez mais de 5 lances
		leilao.dobraLance(steveJobs);

		assertEquals(10, leilao.getLances().size());
		int ultimo = leilao.getLances().size() - 1;
		Lance ultimoLance = leilao.getLances().get(ultimo);
		assertEquals(11000.0, ultimoLance.getValor(), 0.00001);
		assertEquals(billGates, ultimoLance.getUsuario());
	}
}
