package ar.edu.design.examples.microprocesador.creationals;

import java.util.HashMap;
import java.util.Map;

import ar.edu.design.examples.microprocesador.domain.instrucciones.ADD;
import ar.edu.design.examples.microprocesador.domain.instrucciones.Instruccion;
import ar.edu.design.examples.microprocesador.domain.instrucciones.LODV;
import ar.edu.design.examples.microprocesador.domain.instrucciones.NOP;
import ar.edu.design.examples.microprocesador.domain.instrucciones.SWAP;
import ar.edu.design.examples.microprocesador.exceptions.BusinessException;

public class InstruccionFactory {

	private static InstruccionFactory instance;
	private Map<Byte, Instruccion> instructions;
	
	/**
	 * El InstruccionFactory es un singleton, y tiene implementado un
	 * conjunto conocido de instancias que modelan la instrucción
	 * (no se generan n NOP, sino que
	 * se utiliza una sola instrucción NOP, una ADD, una LODV, etc.)
	 * 
	 * @return
	 */
	public static InstruccionFactory getInstance() {
		if (instance == null) {
			instance = new InstruccionFactory();
		}
		return instance;
	}

	private InstruccionFactory() {
		this.initialize();
	}

	private void initialize() {
		instructions = new HashMap<Byte, Instruccion>();
		instructions.put((byte) 1, new NOP());
		instructions.put((byte) 2, new ADD());
		instructions.put((byte) 5, new SWAP());
		instructions.put((byte) 9, new LODV());
	}

	public Instruccion getInstruction(byte codigoInstruccion, ProgramIterator programIt) {
		Instruccion instruccionAEjecutar = this.instructions.get(codigoInstruccion).clone();
		if (instruccionAEjecutar == null) {
			throw new BusinessException("La instrucción de código " + codigoInstruccion + " no es reconocida");
		}
		instruccionAEjecutar.prepare(programIt);
		return instruccionAEjecutar;
	}
	
}
