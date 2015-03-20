package ar.edu.design.examples.microprocesador.creationals;

import java.util.Iterator;

import ar.edu.design.examples.microprocesador.domain.instrucciones.Instruccion;

public class ProgramIterator implements Iterator<Instruccion> {

	private byte[] programMemory;
	private byte index;
	
	public ProgramIterator(byte[] program) {
		this.programMemory = program;
		this.index = 0;
	}
	
	@Override
	public boolean hasNext() {
		return this.getCodigoInstruccionActual() > 0;
	}

	private byte getCodigoInstruccionActual() {
		return this.programMemory[this.index];
	}
	
	@Override
	public void remove() {
		throw new UnsupportedOperationException("remove - Program Iterator");
	}

	@Override
	public Instruccion next() {
		byte codigoInstruccionActual = this.getCodigoInstruccionActual();
		this.advanceIndex();
		return InstruccionFactory.getInstance().getInstruction(codigoInstruccionActual, this);
	}

	private void advanceIndex() {
		this.index++;
	}
	
	public byte nextValue() {
		byte codigoInstruccionActual = this.getCodigoInstruccionActual();
		this.advanceIndex();
		return codigoInstruccionActual;
	}

}
