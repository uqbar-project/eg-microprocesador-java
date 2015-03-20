package ar.edu.design.examples.microprocesador.domain;

import java.util.List;

import ar.edu.design.examples.microprocesador.domain.instrucciones.Instruccion;

public interface Microcontroller extends Cloneable {

	/**
	 * programacion: carga y ejecuta un conjunto de instrucciones en memoria
	 * 
	 * @param program
	 *            un conjunto de instrucciones a ejecutar en el orden en que se
	 *            ingresaron
	 */
	public void run(List<Instruccion> program);

	/**
	 * E/S Pone un valor en el canal de E/S que sera leido por la proxima
	 * instrucción IN que haga referencia al canal indicado monitoreo y
	 * debugging
	 */
	// public void setInput(byte channel, byte value);

	/**
	 * Retorna el valor del acumulador A
	 */
	public byte getAAcumulator();

	/**
	 * Setea el valor del acumulador A
	 */
	public void setAAcumulator(byte value);

	/**
	 * Retorna el valor del acumulador B
	 */
	public byte getBAcumulator();

	/**
	 * Setea el valor del acumulador B
	 */
	public void setBAcumulator(byte value);

	/**
	 * Retorna el valor del PC
	 */
	public byte getPC();

	/**
	 * Avanza el program counter una instrucción
	 */
	public void advancePC();

	/**
	 * Inicializa el microcontrolador
	 */
	public void reset();

	/**
	 * Retorna el valor de la memoria de datos en la direccion indicada
	 * 
	 * @param addr
	 *            dirección de memoria
	 * @return valor de la memoria de datos en esa dirección
	 */
	public byte getData(int addr);

	/**
	 * Setea el valor de la memoria de datos en la direccion indicada
	 * 
	 * @param addr
	 *            dirección de memoria
	 * @param value
	 *            un valor
	 */
	public void setData(int addr, byte value);

	/**
	 * AGREGADOS PARA UNDO
	 */
	
	/**
	 * copia el estado de un microcontrolador a otro
	 * @param micro desde el cual se va a copiar el estado (origen), el destino es el objeto receptor
	 */
	public void copyFrom(Microcontroller micro);
	public Object clone();

}
