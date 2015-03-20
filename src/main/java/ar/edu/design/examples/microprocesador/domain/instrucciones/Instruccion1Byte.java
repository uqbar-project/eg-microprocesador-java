package ar.edu.design.examples.microprocesador.domain.instrucciones;

import ar.edu.design.examples.microprocesador.creationals.ProgramIterator;
import ar.edu.design.examples.microprocesador.domain.Microcontroller;
import ar.edu.design.examples.microprocesador.exceptions.SystemException;

public abstract class Instruccion1Byte implements Instruccion {

	@Override
	public abstract void execute(Microcontroller micro);

	@Override
	public void prepare(ProgramIterator programIt) {
		// Por default, no hago nada
	}

	public Instruccion clone() {
		try {
			return (Instruccion) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new SystemException("La instruccion " + this + " no es clonable");
		}
	}
}
