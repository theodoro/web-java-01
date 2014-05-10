package br.com.btaweb.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.btaweb.entidade.Contato;
import br.com.btaweb.jdbc.Conexao;

public class ContatoDAO {

	private Connection connection;

	public ContatoDAO() {
		this.connection = new Conexao().getConnection();
	}

	public void adicionar(Contato contato) {

		String sql = "insert into contatos (nome, email, endereco, dataNascimento) values(?, ?, ?, ?)";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, new Date(contato.getDataNascimento()
					.getTimeInMillis()));
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Falha na conexão!");
			throw new RuntimeException(e);
		}
	}

	public List<Contato> getLista() {

		try {

			List<Contato> contatos = new ArrayList<>();
			String sql = "select * from contatos";
			PreparedStatement stmt = this.connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Contato contato = new Contato();

				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));

				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataNascimento"));
				contato.setDataNascimento(data);

				contatos.add(contato);

			}
			rs.close();
			stmt.close();
			return contatos;
		} catch (SQLException e) {

			System.out.println("Falha ao carregar a lista");
			throw new RuntimeException(e);

		}

	}
	
	public void alterar(Contato contato){
		
		String sql =  "update contatos set nome=?, email=?, endereco=?, dataNascimento=? where id=? ";
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, new Date(contato.getDataNascimento()
					.getTimeInMillis()));
			
			stmt.setLong(5, contato.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void remover(Contato contato){
		String sql = "delete from contatos where id=?";
		
		try{
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, contato.getId());
			
			stmt.execute();
			stmt.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

}
