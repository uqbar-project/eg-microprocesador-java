package ar.edu.design.examples.microprocesador.domain;

import java.util.List;

import ar.edu.design.examples.microprocesador.domain.instrucciones.Instruccion;
import ar.edu.design.examples.microprocesador.exceptions.SystemException;

public class MicrocontrollerImpl implements Microcontroller {

	private byte acumuladorA;
	private byte acumuladorB;
	private byte programCounter;
	private byte[] datos;

	public MicrocontrollerImpl() {
		this.reset();
	}
	
	@Override
	public byte getAAcumulator() {
		return this.acumuladorA;
	}

	@Override
	public byte getBAcumulator() {
		return this.acumuladorB;
	}

	@Override
	public byte getData(int addr) {
		return this.datos[addr];
	}

	@Override
	public byte getPC() {
		return this.programCounter;
	}

	@Override
	public void setAAcumulator(byte value) {
		this.acumuladorA = value;
	}

	@Override
	public void setBAcumulator(byte value) {
		this.acumuladorB = value;
	}

	@Override
	public void setData(int addr, byte value) {
		this.datos[addr] = value;
	}

	@Override
	public void run(List<Instruccion> program) {
		this.reset();
		for (Instruccion instruccion : program) {
			instruccion.execute(this);
		}
	}

	@Override
	public void advancePC() {
		this.programCounter++;
	}

	@Override
	public void reset() {
		this.programCounter = 0;
		this.acumuladorA = 0;
		this.acumuladorB = 0;
		this.datos = new byte[1024];
	}

	@Override
	public void copyFrom(Microcontroller micro) {
		this.acumuladorA = micro.getAAcumulator();
		this.acumuladorB = micro.getBAcumulator();
		this.programCounter = micro.getPC();
		this.programCounter--;
		for (int i = 0; i < 1024; i++) {
			byte data = micro.getData(i);
			this.setData(i, data);
		}
	}

	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			throw new SystemException(e);
		}
	}

}
