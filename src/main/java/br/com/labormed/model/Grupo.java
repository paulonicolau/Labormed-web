package br.com.labormed.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the GRUPO database table.
 * 
 */
@Entity
@Table(name="GRUPO")
@NamedQuery(name="Grupo.findAll", query="SELECT g FROM Grupo g")
public class Grupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private long id;

	@Column(name="DESCRICAO")
	private String descricao;

	//bi-directional many-to-one association to SubGrupo
	@OneToMany(mappedBy="grupo")
	private List<SubGrupo> subGrupos;

	public Grupo() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<SubGrupo> getSubGrupos() {
		return this.subGrupos;
	}

	public void setSubGrupos(List<SubGrupo> subGrupos) {
		this.subGrupos = subGrupos;
	}

	public SubGrupo addSubGrupo(SubGrupo subGrupo) {
		getSubGrupos().add(subGrupo);
		subGrupo.setGrupo(this);

		return subGrupo;
	}

	public SubGrupo removeSubGrupo(SubGrupo subGrupo) {
		getSubGrupos().remove(subGrupo);
		subGrupo.setGrupo(null);

		return subGrupo;
	}

	@Override
	public String toString() {
	    return descricao;
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
		Grupo other = (Grupo) obj;
		if (id != other.id)
			return false;
		return true;
	}
}