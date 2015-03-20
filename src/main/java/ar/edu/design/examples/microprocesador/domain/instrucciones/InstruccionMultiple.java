package ar.edu.design.examples.microprocesador.domain.instrucciones;

import java.util.List;

import ar.edu.design.examples.microprocesador.domain.Microcontroller;

public abstract class InstruccionMultiple extends Instruccion {

	private List<Instruccion> instrucciones;
	
	@Override
	public void doExecute(Microcontroller micro) {
		for (Instruccion instruccion : this.instrucciones) {
			instruccion.doExecute(micro);
		}
	}

	public InstruccionMultiple(List<Instruccion> instrucciones) {
		this.instrucciones = instrucciones;
	}
	
}
