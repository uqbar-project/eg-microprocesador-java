package ar.edu.design.examples.microprocesador.domain.instrucciones;

import ar.edu.design.examples.microprocesador.creationals.ProgramIterator;
import ar.edu.design.examples.microprocesador.domain.Microcontroller;
import ar.edu.design.examples.microprocesador.exceptions.SystemException;

public class LODV implements Instruccion {
	
	private byte value;
	
	@Override
	public void execute(Microcontroller micro) {
		micro.setAAcumulator(value);
		System.out.println("Ejecuto LODV de " + this.value);
	}

	@Override
	public void prepare(ProgramIterator programIt) {
		this.value = programIt.nextValue();
	}
	
	public Instruccion clone() {
		try {
			return (Instruccion) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new SystemException("La instruccion " + this + " no es clonable");
		}
	}

}
