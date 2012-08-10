package com.examplerf.server.service;

import com.examplerf.server.domain.Colaborador;
import com.google.appengine.api.datastore.EntityNotFoundException;

public class ColaboradorDao extends ObjectifyDao<Colaborador> {
	
	public void save(Colaborador colaborador){
		this.put(colaborador);
	}
	public void remove(Long id){
		try {
			Colaborador c = this.get(id);
			this.delete(c);			
		} catch (EntityNotFoundException e) {			
			e.printStackTrace();
		}
	}
	public Colaborador fetch(Long id){
		Colaborador c = null;
		try {
			 c = this.get(id);
		} catch (EntityNotFoundException e) {			
			e.printStackTrace();
		}
		return c;
	}
}