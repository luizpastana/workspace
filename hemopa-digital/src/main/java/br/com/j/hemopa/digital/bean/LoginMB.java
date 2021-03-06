package br.com.j.hemopa.digital.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.j.hemopa.digital.model.Usuario;
import br.com.j.hemopa.digital.service.UsuarioDAO;

@Named
@SessionScoped
public class LoginMB implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String NAVEGACAO = "/index?faces-redirect=true";

	private Usuario usuario = new Usuario();

	public String efetuaLogin() {
		
		boolean existe = new UsuarioDAO().existe(this.usuario);
		if (existe) {
			
		return LoginMB.NAVEGACAO;
		
		}
		
		return null;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
