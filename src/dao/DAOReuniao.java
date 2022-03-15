package dao;

import java.util.List;

import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import modelo.Reuniao;

public class DAOReuniao  extends DAO<Reuniao>{

	public Reuniao read (Object chave) {
		String datahora = (String) chave;	
		Query q = manager.query();
		q.constrain(Reuniao.class);
		q.descend("datahora").constrain(datahora);
		List<Reuniao> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}
	
	//consulta:

    public List<Reuniao> ReuniaoComConvidado() {
        Query q = manager.query();
        q.constrain(Reuniao.class);
        q.constrain(new Filter() );
        List<Reuniao> result = q.execute();
        return result;
    }

    //Filtro

    class Filter implements Evaluation {

        public void evaluate(Candidate candidate) {
            Reuniao r = (Reuniao) candidate.getObject();
            boolean resposta = false;
            if(r.getParticipantes().size() > 0)
                for (Participante p : r.getParticipantes())
                    if (p instanceof Convidado)
                        resposta = true;
            candidate.include(resposta);
        }
	
}