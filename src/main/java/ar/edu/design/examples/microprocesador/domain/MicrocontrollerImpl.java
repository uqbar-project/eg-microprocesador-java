package ar.edu.design.examples.microprocesador.domain;

import ar.edu.design.examples.microprocesador.creationals.ProgramIterator;
import ar.edu.design.examples.microprocesador.exceptions.BusinessException;


public class MicrocontrollerImpl implements Microcontroller {

	private boolean programStarted;
	private byte[] dataMemory;
	private byte aAcumulator;
	private byte bAcumulator;
	private byte programCounter;
	// TODO: Canales de E/S
	private ProgramIterator programIterator;
	
	public MicrocontrollerImpl() {
		this.reset();
	}
	
	@Override
	public void loadProgram(byte[] program) {
		if (this.programStarted) {
			throw new BusinessException("Ya hay un programa en ejecución");
		}
		this.reset();
		this.programIterator = new ProgramIterator(program);
		//this.programMemory = program;
	}

	@Override
	public void reset() {
		this.programStarted = false;
		this.aAcumulator = (byte) 0;
		this.bAcumulator = (byte) 0;
		this.dataMemory = new byte[1024];
		this.programCounter = 0;
	}

	@Override
	public void start() {
		this.programStarted = true;
	}

	@Override
	public void stop() {
		this.programStarted = false;
	}

	@Override
	public void step() {
		if (!this.programStarted) {
			throw new BusinessException("No hay un programa en ejecución");
		}
		if (this.programIterator == null) {
			throw new BusinessException("No hay un programa cargado en memoria");
		}
		if (!programIterator.hasNext()) {
			throw new BusinessException("No hay más instrucciones para ejecutar");
		}
		programIterator.next().execute(this);
		this.advancePC();
	}

	@Override
	public void setInput(byte channel, byte value) {
		// TODO Auto-generated method stub

	}

	@Override
	public byte getAAcumulator() {
		return this.aAcumulator;
	}

	@Override
	public void setAAcumulator(byte value) {
		this.aAcumulator = value;
	}

	@Override
	public byte getBAcumulator() {
		return this.bAcumulator;
	}

	@Override
	public void setBAcumulator(byte value) {
		this.bAcumulator = value;
	}

	@Override
	public byte getPC() {
		return this.programCounter;
	}

	@Override
	public byte setPC(int value) {
		throw new BusinessException("Use advancePC() instead");
	}

	@Override
	public byte getData(int addr) {
		return this.dataMemory[addr];
	}

	@Override
	public void setData(int addr, byte value) {
		this.dataMemory[addr] = value;
	}

	//@Override
	//public byte getProgram(int addr) {
	//	return this.programMemory[addr];
	//}

	//@Override
	//public void setProgram(int addr, byte value) {
	//	this.programMemory[addr] = value;
	//}

	@Override
	public void advancePC() {
		this.programCounter++;
	}

}
