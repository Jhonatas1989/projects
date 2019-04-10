package br.com.sctdb.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import br.com.sctdb.dao.GenericDao;
import br.com.sctdb.entity.Aluno;
import br.com.sctdb.entity.Nota;
import br.com.sctdb.entity.Usuario;
import br.com.sctdb.util.SessionUtils;

@ManagedBean
@ViewScoped
public class MinhaPaginaAlunoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private GenericDao<Aluno> adao = new GenericDao<Aluno>(Aluno.class);
	private Aluno aluno;

	public MinhaPaginaAlunoBean() {
		aluno = pegarAluno(SessionUtils.getUser());
	}

	public String aprovacao(Nota nota) {
		String aprovado;
		Double nota1 = 0.0;
		Double nota2 = 0.0;
		Double nota3 = 0.0;
		if (nota.getProjeto1() != null) {
			nota1 = nota.getProjeto1() * 0.3;
		}
		if (nota.getProjeto2() != null) {
			nota2 = nota.getProjeto2() * 0.3;
		}
		if (nota.getAtividadePratica() != null) {
			nota3 = nota.getAtividadePratica() * 0.4;
		}
		Double total = nota1 + nota2 + nota3;
		if (total >= 7.0) {
			aprovado = "Aprovado";
		} else {
			aprovado = "Reprovado";
		}
		return aprovado;
	}
	
	public Aluno pegarAluno(Usuario user){
		Aluno al = null;
		for (Aluno aluno : adao.listar()) {
			if(aluno.getEmail().equals(user.getEmail())){
				al = aluno;
			}
		}
		return al;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

}
