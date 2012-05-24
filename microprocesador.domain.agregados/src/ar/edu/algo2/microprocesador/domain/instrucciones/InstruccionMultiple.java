package ar.edu.algo2.microprocesador.domain.instrucciones;

import java.util.List;

import ar.edu.algo2.microprocesador.domain.Microcontroller;

public abstract class InstruccionMultiple extends Instruccion {

	private List<Instruccion> instrucciones;
	
	@Override
	public void doExecute(Microcontroller micro) {
		for (Instruccion instruccion : this.instrucciones) {
			instruccion.execute(micro);
		}
	}

	public InstruccionMultiple(List<Instruccion> instrucciones) {
		this.instrucciones = instrucciones;
	}
	
}
