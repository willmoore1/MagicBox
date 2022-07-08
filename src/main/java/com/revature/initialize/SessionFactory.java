package com.revature.initialize;

import java.util.List;

import javax.imageio.spi.ServiceRegistry;

import com.revature.runtime.Session;

public class SessionFactory {
	
	private ConnectionUtil connUtil;
	private List<MetaModel<Class<?>>> metaModelList;

	protected SessionFactory(ConnectionUtil connUtil, List<MetaModel<Class<?>>> metaModelList) {
		this.connUtil = connUtil;
		this.metaModelList = metaModelList;
	}
	
}
