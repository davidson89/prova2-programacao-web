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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.edu.ufabc.progWeb.prova2.dao.AssocPedidoItemDAO;

import com.sun.istack.internal.NotNull;


@Entity
@Table(name="PEDIDO")
public class Pedido implements Serializable {

	private static final long serialVersionUID = -7099434527434920634L;

	private Long id;

	@Size(min=6, max=6)
	@Pattern(regexp="^[0-9]+[0-9]+[0-9]+[0-9]+[0-9]+[0-9]$")
	private String numeroPedido;

	@NotEmpty
	private String solicitante;

	@NotEmpty
	@Pattern(regexp="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	private String emailSolicitante;

	private Boolean status;

	private Date dtPedido;

	private Date dtFechamento;

	@Id
	@Column(name="ID")
	@GeneratedValue
	@NotNull
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	@GeneratedValue
	@Column(name="NUM_PEDIDO", length=10, unique=true,nullable=false)
	public String getNumeroPedido() {
		return this.numeroPedido;
	}

	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	@Column(name="SOLICITANTE", length=50, nullable=false)
	public String getSolicitante() {
		return this.solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	@Column(name="EMAIL_SOLIC")
	public String getEmailSolicitante() {
		return this.emailSolicitante;
	}

	public void setEmailSolicitante(String emailSolicitante) {
		this.emailSolicitante = emailSolicitante;
	}

	@Column(name="STATUS")
	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Column(name="DT_PEDIDO")
	public Date getDtPedido() {
		return this.dtPedido;
	}

	public void setDtPedido(Date dtPedido) {
		this.dtPedido = dtPedido;
	}

	/**
	 * @return the dtFechamento
	 */
	@Column(name="DT_FECHAMENTO")
	public Date getDtFechamento() {
		return this.dtFechamento;
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
		return assocDAO.findByPedido(this);
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
