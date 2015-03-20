package ar.edu.design.examples.microprocesador.domain.instrucciones;

import ar.edu.design.examples.microprocesador.domain.Microcontroller;

public class SWAP extends Instruccion1Byte {

	@Override
	public void execute(Microcontroller micro) {
		byte swapValue = micro.getBAcumulator();
		micro.setBAcumulator(micro.getAAcumulator());
		micro.setAAcumulator(swapValue);
		System.out.println("Ejecuto SWAP. Acumulador A: " + micro.getAAcumulator() + " | Acumulador B: " + micro.getBAcumulator());
	}

}
