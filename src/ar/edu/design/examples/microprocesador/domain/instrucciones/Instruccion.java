package ar.edu.design.examples.microprocesador.domain.instrucciones;

import ar.edu.design.examples.microprocesador.domain.Microcontroller;

public abstract class Instruccion {

	private Microcontroller microBefore;

	public void execute(Microcontroller micro) {
		microBefore = (Microcontroller) micro.clone();
		micro.advancePC();
		this.doExecute(micro);
	}

	public abstract void doExecute(Microcontroller micro);
	
	public void undo(Microcontroller micro) {
		micro.copyFrom(microBefore);
	}

}
