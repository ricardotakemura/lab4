package com.sensedia.poc.lab.model;

import java.util.List;

public class Carro extends Automovel {

	private ArCondicionado arCondicionado;
	private List<Passageiro> passageiros;

	public ArCondicionado getArCondicionado() {
		return arCondicionado;
	}

	public void setArCondicionado(ArCondicionado arCondicionado) {
		this.arCondicionado = arCondicionado;
	}

	public List<Passageiro> getPassageiros() {
		return passageiros;
	}

	public void setPassageiros(List<Passageiro> passageiros) {
		this.passageiros = passageiros;
	}

	@Override
	public void andar() {
		/* ... */
	}

}
