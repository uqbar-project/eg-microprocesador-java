package ar.edu.design.examples.microprocesador.domain.instrucciones;

import ar.edu.design.examples.microprocesador.domain.Microcontroller;

public class ADD extends Instruccion1Byte {

	@Override
	public void execute(Microcontroller micro) {
		int suma = micro.getAAcumulator() + micro.getBAcumulator();
		if (suma > Byte.MAX_VALUE) {
			micro.setAAcumulator((byte) (suma - Byte.MAX_VALUE));
			micro.setBAcumulator(Byte.MAX_VALUE);
		} else {
			micro.setAAcumulator((byte) 0);
			micro.setBAcumulator((byte) suma);
		}
		System.out.println("Ejecuto ADD");
	}

}
