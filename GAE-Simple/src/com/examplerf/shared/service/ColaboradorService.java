package com.examplerf.shared.service;

import java.util.List;

import com.examplerf.server.locator.DaoServiceLocator;
import com.examplerf.server.service.ColaboradorDao;
import com.examplerf.shared.proxy.ColaboradorProxy;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(value = ColaboradorDao.class, locator = DaoServiceLocator.class)
public interface ColaboradorService extends RequestContext {
	Request<List<ColaboradorProxy>> listAll();
	Request<ColaboradorProxy> fetch(Long id);
	Request<Void> save(ColaboradorProxy colaborador);
	Request<Void> remove(Long id);	
}
