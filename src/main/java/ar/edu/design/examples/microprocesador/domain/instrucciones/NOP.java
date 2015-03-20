package ar.edu.design.examples.microprocesador.domain.instrucciones;

import ar.edu.design.examples.microprocesador.domain.Microcontroller;

public class NOP extends Instruccion1Byte {

	@Override
	public void execute(Microcontroller micro) {
		System.out.println("Ejecuto NOP");
	}

}
