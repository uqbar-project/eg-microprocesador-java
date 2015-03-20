package ar.edu.design.examples.microprocesador.domain;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ar.edu.design.examples.microprocesador.domain.Microcontroller;
import ar.edu.design.examples.microprocesador.domain.MicrocontrollerImpl;
import ar.edu.design.examples.microprocesador.exceptions.BusinessException;


public class TestMicrocontrollerCreacionales {

	Microcontroller micro;
	private byte[] programNOP;
	private byte[] programSuma8y5;
	
	@Before
	public void setUp() {
		micro = new MicrocontrollerImpl();

		programNOP = new byte[1024];
		programNOP[0] = (byte) 1;  // NOP
		programNOP[1] = (byte) 1;  // NOP
		programNOP[2] = (byte) 1;  // NOP

		programSuma8y5 = new byte[1024];
		programSuma8y5[0] = (byte) 9;  // LODV 
		programSuma8y5[1] = (byte) 8;     // dato: 8  
		programSuma8y5[2] = (byte) 5;  // SWAP
		programSuma8y5[3] = (byte) 9;  // LODV 
		programSuma8y5[4] = (byte) 5;     // dato: 5
		programSuma8y5[5] = (byte) 2;  // ADD
	}
	
	@Test
	public void nop() {
		micro.loadProgram(programNOP);
		micro.start();
		micro.step();
		micro.step();
		micro.step();
		micro.stop();
		Assert.assertEquals(3, micro.getPC());
	}

	@Test
	public void suma() {
		micro.loadProgram(programSuma8y5);
		micro.start();
		micro.step();
		micro.step();
		micro.step();
		micro.step();
		micro.stop();
		Assert.assertEquals(0, micro.getAAcumulator());
		Assert.assertEquals(13, micro.getBAcumulator());
	}

	@Test(expected=BusinessException.class)
	public void cargarProgramaMientrasOtroEjecuta() {
		micro.loadProgram(programNOP);
		micro.start();
		micro.loadProgram(programNOP);
	}
	
	@Test(expected=BusinessException.class)
	public void ejecutarProgramaNoEmpezado() {
		micro.step();
	}

	@Test(expected=BusinessException.class)
	public void ejecutarProgramaNoCargado() {
		micro.start() ;
		micro.step();
	}

	@Test
	public void ejecutarDosProgramas() {
		micro.loadProgram(programSuma8y5);
		micro.start();
		micro.step();
		micro.step();
		micro.step();
		micro.step();
		micro.stop();
		Assert.assertEquals(13, micro.getBAcumulator());
		Assert.assertEquals(0, micro.getAAcumulator());

		micro.loadProgram(programNOP);
		micro.start();
		micro.step();
		micro.step();
		micro.step();
		micro.stop();
		Assert.assertEquals(3, micro.getPC());
		Assert.assertEquals(0, micro.getBAcumulator());
		Assert.assertEquals(0, micro.getAAcumulator());
	}
	
}
