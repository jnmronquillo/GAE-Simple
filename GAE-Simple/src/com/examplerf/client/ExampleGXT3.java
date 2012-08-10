package com.examplerf.client;

import java.util.List;

import com.examplerf.shared.proxy.ColaboradorProxy;
import com.examplerf.shared.service.ColaboradorService;
import com.examplerf.shared.service.ExampleRFRequestFactory;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;

public class ExampleGXT3 implements EntryPoint {
	private final ExampleRFRequestFactory myRF = GWT.create(ExampleRFRequestFactory.class);
	public void onModuleLoad() {
		myRF.initialize(new SimpleEventBus());
		
		//guardar();
		//editar();		
		//eliminar();
		listar();
	}
	
	public void guardar(){
		ColaboradorService cs = myRF.colaboradorService();
		
		ColaboradorProxy colaborador = cs.create(ColaboradorProxy.class);
		colaborador.setNombres("marcelo");
		colaborador.setApellidos("bailon");
		colaborador.setEdad(30);		
		
		cs.save(colaborador).fire(new Receiver<Void>() {

			@Override
			public void onSuccess(Void response) {
				GWT.log("Se guardo correctamente");				
			}
		});
	}
	public void editar(){		 
		ColaboradorService cs = myRF.colaboradorService();
		
		Request<ColaboradorProxy> colaborador = cs.fetch(new Long(2));
		colaborador.fire(new Receiver<ColaboradorProxy>() {

			@Override
			public void onSuccess(ColaboradorProxy response) {
				GWT.log(response.getNombres());
				
				ColaboradorService cs2 = myRF.colaboradorService();
				//para poder editar
				ColaboradorProxy colaborador2 = cs2.edit(response);
				colaborador2.setNombres("Marcelo");
				colaborador2.setApellidos("bailon");
				
				cs2.save(colaborador2).fire(new Receiver<Void>() {

					@Override
					public void onSuccess(Void response) {
						GWT.log("Se edito correctamente");						
					}
				});
			}
		});

	}
	public void eliminar(){
		myRF.colaboradorService().remove(new Long(1)).fire(new Receiver<Void>() {

			@Override
			public void onSuccess(Void response) {
				GWT.log("Se elimino correctamente");				
			}
		});
		
	}
	public void listar(){
		myRF.colaboradorService().listAll().fire(new Receiver<List<ColaboradorProxy>>() {

			@Override
			public void onSuccess(List<ColaboradorProxy> response) {
				for(ColaboradorProxy c : response){
					GWT.log(c.getNombres() +" "+c.getApellidos());
				}				
			}
		});
	}
}
