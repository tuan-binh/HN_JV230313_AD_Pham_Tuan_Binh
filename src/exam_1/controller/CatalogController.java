package exam_1.controller;

import exam_1.model.Catalog;
import exam_1.service.CatalogService;

import java.util.List;

public class CatalogController implements IController<Catalog, Integer> {
	private CatalogService catalogService = new CatalogService();
	
	@Override
	public List<Catalog> getAll() {
		return catalogService.getAll();
	}
	
	@Override
	public void save(Catalog catalog) {
		catalogService.save(catalog);
	}
	
	@Override
	public Catalog findById(Integer integer) {
		return catalogService.findById(integer);
	}
	
	@Override
	public void delete(Integer integer) {
		catalogService.delete(integer);
	}
	
	public int getNewId() {
		return catalogService.getNewId();
	}
}
