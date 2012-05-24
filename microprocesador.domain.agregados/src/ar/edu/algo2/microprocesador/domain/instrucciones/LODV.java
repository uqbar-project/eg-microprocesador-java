package ar.edu.algo2.microprocesador.domain.instrucciones;

import ar.edu.algo2.microprocesador.domain.Microcontroller;

public class LODV extends Instruccion {

	int value;
	
	public LODV(int value) {
		if (value > 255) {
			throw new IllegalArgumentException("No debe crear una instrucción LODV de un número mayor a 255");
		} else {
			this.value = value;
		}
	}

	@Override
	public void doExecute(Microcontroller micro) {
		micro.setAAcumulator((byte) value);
	}

}
