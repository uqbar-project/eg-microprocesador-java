package ar.edu.design.examples.microprocesador.domain.instrucciones;

import ar.edu.design.examples.microprocesador.domain.Microcontroller;

public class SWAP extends Instruccion {

	@Override
	public void doExecute(Microcontroller micro) {
		byte swapValue = micro.getBAcumulator();
		micro.setBAcumulator(micro.getAAcumulator());
		micro.setAAcumulator(swapValue);
	}

}
