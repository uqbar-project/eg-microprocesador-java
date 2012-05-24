package ar.edu.design.examples.microprocesador.domain.instrucciones;

import ar.edu.design.examples.microprocesador.domain.Microcontroller;

public class SUB extends Instruccion {

	@Override
	public void doExecute(Microcontroller micro) {
		int suma = micro.getAAcumulator() - micro.getBAcumulator();
		if (suma > Byte.MAX_VALUE) {
			micro.setBAcumulator(Byte.MAX_VALUE);
			micro.setAAcumulator((byte) (suma - Byte.MAX_VALUE));
		} else {
			micro.setBAcumulator((byte) suma);
			micro.setAAcumulator((byte) 0);
		}
	}
}
