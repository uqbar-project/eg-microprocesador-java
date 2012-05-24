package ar.edu.algo2.microprocesador.domain;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ar.edu.algo2.microprocesador.domain.Microcontroller;
import ar.edu.algo2.microprocesador.domain.MicrocontrollerImpl;
import ar.edu.algo2.microprocesador.domain.instrucciones.ADD;
import ar.edu.algo2.microprocesador.domain.instrucciones.DIV;
import ar.edu.algo2.microprocesador.domain.instrucciones.Instruccion;
import ar.edu.algo2.microprocesador.domain.instrucciones.LODV;
import ar.edu.algo2.microprocesador.domain.instrucciones.NOP;
import ar.edu.algo2.microprocesador.domain.instrucciones.SUB;
import ar.edu.algo2.microprocesador.domain.instrucciones.SWAP;
import ar.edu.algo2.microprocesador.domain.instrucciones.WHNZ;

public class TestMicrocontroller {

	private Microcontroller micro;
	
	@Before
	public void setUp() throws Exception {
		micro = new MicrocontrollerImpl();
	}

	@Test
	public void programCounter() {
		List<Instruccion> instrucciones = new ArrayList<Instruccion>();
		instrucciones.add(new NOP());
		instrucciones.add(new NOP());
		instrucciones.add(new NOP());
		micro.run(instrucciones);
		Assert.assertEquals(3, micro.getPC());
	}

	@Test
	public void sumaSimple() {
		List<Instruccion> instrucciones = new ArrayList<Instruccion>();
		instrucciones.add(new LODV(10));
		instrucciones.add(new SWAP());
		instrucciones.add(new LODV(22));
		instrucciones.add(new ADD());
		micro.run(instrucciones);
		Assert.assertEquals(0, micro.getAAcumulator());
		Assert.assertEquals(32, micro.getBAcumulator());
	}

	@Test
	public void sumaNumerosGrandes() {
		List<Instruccion> instrucciones = new ArrayList<Instruccion>();
		instrucciones.add(new LODV(100));
		instrucciones.add(new SWAP());
		instrucciones.add(new LODV(50));
		instrucciones.add(new ADD());
		micro.run(instrucciones);
		Assert.assertEquals(23, micro.getAAcumulator());
		Assert.assertEquals(127, micro.getBAcumulator());
	}

	@Test(expected=ArithmeticException.class)
	public void divisionPorCero() {
		List<Instruccion> instrucciones = new ArrayList<Instruccion>();
		instrucciones.add(new LODV(0));
		instrucciones.add(new SWAP());
		instrucciones.add(new LODV(2));
		instrucciones.add(new DIV());
		micro.run(instrucciones);
	}

	/**
	 * BONUS 3 : requiere mayor manejo del micro
	 * Se desea poder deshacer la última instrucción ejecutada 
	 * (o sea, que el microprocesador vuelva al estado anterior). 
	 * Ejemplo: si se hizo un SWAP, el acumulador A debe volver a tener lo que
	 *  el acumulador B tenía y viceversa. En el caso del ADD se debe deshacer 
	 *  la suma y los valores de los acumuladores deben quedar como estaban
	 *  previamente. 
	 **/
	@Test
	public void undo() {
		Instruccion carga100 = new LODV(100);
		Instruccion swap = new SWAP();
		carga100.execute(micro);
		swap.execute(micro);
		Assert.assertEquals(100, micro.getBAcumulator());
		Assert.assertEquals(0, micro.getAAcumulator());
		swap.undo(micro);
		Assert.assertEquals(0, micro.getBAcumulator());
		Assert.assertEquals(100, micro.getAAcumulator());
		swap.execute(micro);
		new LODV(50).execute(micro);
		Instruccion suma = new ADD();
		suma.execute(micro);
		Assert.assertEquals(23, micro.getAAcumulator());
		Assert.assertEquals(127, micro.getBAcumulator());
		suma.undo(micro);
		Assert.assertEquals(50, micro.getAAcumulator());
		Assert.assertEquals(100, micro.getBAcumulator());
	}

	// Test que prueba el while de 1 a 10 
	@Test
	public void for1a10() {
		List<Instruccion> instrucciones = new ArrayList<Instruccion>();
		// Cargo diez 
		instrucciones.add(new LODV(1));
		instrucciones.add(new SWAP());
		instrucciones.add(new LODV(10));
		
		List<Instruccion> subInstrucciones = new ArrayList<Instruccion>();
		subInstrucciones.add(new SUB());
		subInstrucciones.add(new LODV(1));
		subInstrucciones.add(new SWAP());
		WHNZ bloqueWhile = new WHNZ(subInstrucciones);
		instrucciones.add(bloqueWhile);
		micro.run(instrucciones);
		
		Assert.assertEquals(10, bloqueWhile.getVecesQueFueEjecutado());
	}
	
}
