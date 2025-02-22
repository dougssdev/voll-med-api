package voll.med.api.pacientes;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import voll.med.api.endereco.DadosEndereco;
import voll.med.api.endereco.Endereco;

@Entity(name = "Paciente")
@Table(name = "pacientes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

	public Paciente(DadosPaciente dados) {
	this.nome = dados.nome();
	this.telefone = dados.telefone();
	this.email = dados.email();
	this.endereco = new Endereco(dados.endereco());
	this.cpf = dados.cpf();
	this.ativo = true;
			
	}
	
	public Paciente() {
		
	}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nome;

	private String telefone;
	private String email;
	private String cpf;
	private boolean ativo; 
	
	@Embedded
	private Endereco endereco;
	
	public boolean isAtivo() {
		return ativo;
	}
	
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void atualizarInformacoes(@Valid DadosAtualizacaoPaciente dados) {
		if(dados.nome() != null) {
			this.nome = dados.nome();
		}
		
		if(dados.telefone() != null) {
			this.telefone = dados.telefone();
		}
		
		if(dados.email() != null) {
			this.email = dados.email();
		}
		if(dados.endereco() != null) {
			this.endereco.atualizarInformacoesEndereco(dados.endereco());
		}
	}
	
	public void excluir() {
		this.ativo = false;
	}
}
