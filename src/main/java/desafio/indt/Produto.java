package desafio.indt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Produto")
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column()
	private String nome;
	@Column(columnDefinition = "TEXT")
	private String descricao;
	@Column
	private float valor;
	@Column
	private String imagem;

	Produto() {
	}

	Produto(String nome, String descricao, float valor, String imagem) {
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
		this.imagem = imagem;
	}

	public Long getId() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}
	
	public String getDescricao() {
		return this.descricao;
	}

	public float getValor() {
		return this.valor;
	}

	public String getImagem() {
		return this.imagem;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
}
