package dao;

import java.util.List;

import com.db4o.query.Query;

import modelo.Convidado;
import modelo.Participante;

public class DAOConvidado extends DAO<Convidado> {
	@Override
	public Convidado read (Object chave) {
		String nome = (String) chave;
		Query q = manager.query();
		q.constrain(Participante.class);
		q.descend("nome").constrain(nome);
		List<Convidado> resultado = q.execute();
		if (resultado.size()>0)
			return resultado.get(0);
		else
			return null;
	}

}
