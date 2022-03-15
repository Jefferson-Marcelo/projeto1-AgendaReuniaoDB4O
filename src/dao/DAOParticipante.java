package dao;

import java.util.List;

import com.db4o.query.Query;

import modelo.Participante;

public class DAOParticipante extends DAO<Participante>{
	public Participante read (Object chave) {
		String datahora = (String) chave;	
		Query q = manager.query();
		q.constrain(Participante.class);
		q.descend("datahora").constrain(datahora);
		List<Participante> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}
	
	//consulta
	
	public List<Participante> participanteComNomeEMes(String N, int M) {
		Query q = manager.query();
		q.constrain(Participante.class);
		q.descend("reunioes").descend("participantes").descend("nome").constrain(N).like();
		q.descend("reunioes").descend("datahora").constrain(M).like();
		List<Participante> result = q.execute(); 
		return result;
	}
	

}






	


