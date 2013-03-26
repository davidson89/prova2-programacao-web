package br.edu.ufabc.progWeb.prova2.model;


import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.edu.ufabc.progWeb.prova2.dao.AssocPedidoItemDAO;

import com.sun.istack.internal.NotNull;


@Entity
@Table(name="PEDIDO")
public class Pedido implements Serializable {
	
	private Long id;
	
	private String numeroPedido;
	
	private String solicitante;
	
	private String emailSolicitante;
	
	private Boolean status;
	
	private Date dtPedido;
	
	private Date dtFechamento;
	
	@Id
	@Column(name="ID")
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@NotNull
	@Column(name="NUM_PEDIDO", length=10, unique=true,nullable=false)
	public String getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	
	@Column(name="SOLICITANTE", length=50, nullable=false)
	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	@Column(name="EMAIL_SOLIC")
	public String getEmailSolicitante() {
		return emailSolicitante;
	}

	public void setEmailSolicitante(String emailSolicitante) {
		this.emailSolicitante = emailSolicitante;
	}

	@Column(name="STATUS")
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Column(name="DT_PEDIDO")
	public Date getDtPedido() {
		return dtPedido;
	}

	public void setDtPedido(Date dtPedido) {
		this.dtPedido = dtPedido;
	}
	
	/**
	 * @return the dtFechamento
	 */
	@Column(name="DT_FECHAMENTO")
	public Date getDtFechamento() {
		return dtFechamento;
	}
	
	/**
	 * @param dtFechamento the dtFechamento to set
	 */
	public void setDtFechamento(Date dtFechamento) {
		this.dtFechamento = dtFechamento;
	}
	
	@Transient
	public List<AssocPedidoItem> getAssocPedidoItem() {
		AssocPedidoItemDAO assocDAO = new AssocPedidoItemDAO();
		return assocDAO.fingByPedido(this);
	}

	/**
	 * 
	 * @return valor total da compra
	 */
	@Transient
	public Double getValorTotalCompra() {
		Double valorCompra = 0.0;
		for(AssocPedidoItem assoc : this.getAssocPedidoItem()) {
			valorCompra = valorCompra + assoc.getValorTotal();
		}
		return valorCompra;
	}
	
}
