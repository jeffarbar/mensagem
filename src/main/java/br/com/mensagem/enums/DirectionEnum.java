package br.com.mensagem.enums;

public enum DirectionEnum {

	ENVIO(0), RECEBE(1);
	
	private DirectionEnum(int sentido) {
		this.sentido = sentido;
	}

	private int sentido;

	public int getSentido() {
		return sentido;
	}

	public void setSentido(int sentido) {
		this.sentido = sentido;
	}

}
