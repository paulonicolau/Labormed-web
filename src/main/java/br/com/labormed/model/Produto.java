package br.com.labormed.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the PRODUTO database table.
 * 
 */
@Entity
@Table(name="PRODUTO")
@NamedQuery(name="Produto.findAll", query="SELECT p FROM Produto p")
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private long id;
	
	@Column(name="DESCRICAO")
	private String descricao;
	
	@Column(name="CODIGO")
	private String codigo;
	
	@Column(name="CODIGO_BARRAS")
	private String codigoBarras;
	
	@Column(name="DATA_REG")
	private Date dataReg;
	
	@Column(name="REGISTRO_ANVISA")
	private String registroAnvisa;
	
	@Column(name="ATIVO")
	private boolean ativo;
	
	@ManyToOne
	@JoinColumn(name="ID_FABRICANTE")
	private Fabricante fabricante;
	
	@ManyToOne
	@JoinColumn(name="ID_USUARIO_CADASTRO")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="ID_SUBGRUPO")
	private SubGrupo subGrupo;
	

	public Produto() {
		super();
	}   
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}   
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}   
	public String getCodigoBarras() {
		return this.codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}   
	public Date getDataReg() {
		return this.dataReg;
	}

	public void setDataReg(Date dataReg) {
		this.dataReg = dataReg;
	}   
	public String getRegistroAnvisa() {
		return this.registroAnvisa;
	}

	public void setRegistroAnvisa(String registroAnvisa) {
		this.registroAnvisa = registroAnvisa;
	}   
	public boolean getAtivo() {
		return this.ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}   
	public Fabricante getFabricante() {
		return this.fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}   
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}   
	public SubGrupo getSubGrupo() {
		return this.subGrupo;
	}

	public void setSubGrupo(SubGrupo subGrupo) {
		this.subGrupo = subGrupo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id != other.id)
			return false;
		return true;
	}
   
}