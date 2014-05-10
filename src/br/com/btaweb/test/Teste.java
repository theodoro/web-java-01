package br.com.btaweb.test;

import java.util.Calendar;
import java.util.List;

import br.com.btaweb.dao.ContatoDAO;
import br.com.btaweb.entidade.Contato;


public class Teste {
	
	public static void main(String[] args){
		
		Contato contato = new Contato();
		
		contato.setNome("Bruno Theodoro de Aquino");
		contato.setEmail("theodorodeaquino@gmail.com");
		contato.setEndereco("Hermenegildo Gomes de Castro, 47");
		contato.setDataNascimento(Calendar.getInstance());
		
		ContatoDAO dao = new ContatoDAO();
		
		dao.adicionar(contato);
		
		System.out.println("Gravado!");
		
		List<Contato> contatos = dao.getLista();
		
		for(Contato c : contatos){
			System.out.println("Nome :" + contato.getNome());
			System.out.println("Email :" + contato.getEmail());
			System.out.println("Endereço :" + contato.getEndereco());
			System.out.println("Data :" + contato.getDataNascimento().getTime() + "\n");
		}
		
	}

}
