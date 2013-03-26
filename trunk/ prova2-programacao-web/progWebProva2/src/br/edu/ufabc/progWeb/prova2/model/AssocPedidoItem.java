package br.edu.ufabc.progWeb.prova2.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="ASSOC_PEDIDO_ITEM")
public class AssocPedidoItem implements Serializable {
	
	private Long id;
	
	private Pedido pedido;
	
	private Item item;
	
	private Long qtd;
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ID_PEDIDO")
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ID_ITEM")
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Column(name="QTD")
	public Long getQtd() {
		return qtd;
	}

	public void setQtd(Long qtd) {
		this.qtd = qtd;
	}
	
	/**
	 * 
	 * @return valor total da associação item x pedido (preço x qtd)
	 */
	@Transient
	public Double getValorTotal() {
		return this.getItem().getPreco() * (new Double(this.getQtd()));
	}

}
