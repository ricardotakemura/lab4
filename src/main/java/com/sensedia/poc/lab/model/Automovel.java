package com.sensedia.poc.lab.model;

public abstract class Automovel implements Veiculo {

	private Motor motor;

	public Motor getMotor() {
		return motor;
	}

	public void setMotor(Motor motor) {
		this.motor = motor;
	}

}
