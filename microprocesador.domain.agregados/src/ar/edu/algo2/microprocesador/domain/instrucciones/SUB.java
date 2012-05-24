package ar.edu.algo2.microprocesador.domain.instrucciones;

import ar.edu.algo2.microprocesador.domain.Microcontroller;

public class SUB extends Instruccion {

	@Override
	public void doExecute(Microcontroller micro) {
		micro.updateAccumulators(micro.getAAcumulator() - micro.getBAcumulator());
	}
}
