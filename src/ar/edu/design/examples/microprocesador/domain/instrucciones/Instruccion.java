package ar.edu.design.examples.microprocesador.domain.instrucciones;

import ar.edu.design.examples.microprocesador.creationals.ProgramIterator;
import ar.edu.design.examples.microprocesador.domain.Microcontroller;

public interface Instruccion extends Cloneable {

	public void execute(Microcontroller micro);

	public void prepare(ProgramIterator programIt);

	public Instruccion clone();

}
