package br.com.mensagem.enums;

public enum ResponseEnum {

	OK(0 , "Mensagem enviada com sucesso"),
	ERRO(1, "Erro ao salvar mensagem");
	
	private ResponseEnum(int codigo, String msg) {
		this.codigo = codigo;
		this.msg = msg;
	}
	private int codigo;
	private String msg;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
