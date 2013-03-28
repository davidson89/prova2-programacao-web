package br.edu.ufabc.progWeb.prova2.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="ITEM")
public class Item implements Serializable {

	private Long id;

	@NotEmpty(message="{item.descricao}")
	@Size(min=2)
	private String descricao;

	private Double preco;

	@OneToMany(mappedBy = "item", targetEntity = Item.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private AssocPedidoItem assocPedidoItem;

	@Id
	@GeneratedValue
	@Column(name="ID")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="DESCRICAO", length=50)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name="PRECO", precision=10, scale=2)
	public Double getPreco() {
		return this.preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public AssocPedidoItem getAssocPedidoItem() {
		return assocPedidoItem;
	}

	public void setAssocPedidoItem(AssocPedidoItem assocPedidoItem) {
		this.assocPedidoItem = assocPedidoItem;
	}

}
