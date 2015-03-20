package ar.edu.design.examples.microprocesador.domain.instrucciones;

import ar.edu.design.examples.microprocesador.domain.Microcontroller;

public class NOP extends Instruccion {

	@Override
	public void doExecute(Microcontroller micro) {
		// No operation no hace nada por default
	}

}
