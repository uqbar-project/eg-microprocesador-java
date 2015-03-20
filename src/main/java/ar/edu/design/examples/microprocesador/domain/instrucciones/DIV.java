package ar.edu.design.examples.microprocesador.domain.instrucciones;

import ar.edu.design.examples.microprocesador.domain.Microcontroller;

public class DIV extends Instruccion {

	@Override
	public void doExecute(Microcontroller micro) {
		int suma = micro.getAAcumulator() / micro.getBAcumulator();
		if (suma > Byte.MAX_VALUE) {
			micro.setAAcumulator(Byte.MAX_VALUE);
			micro.setBAcumulator((byte) (suma - Byte.MAX_VALUE));
		} else {
			micro.setAAcumulator((byte) suma);
			micro.setBAcumulator((byte) 0);
		}
	}

}
