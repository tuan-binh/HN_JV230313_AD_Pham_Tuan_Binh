package exam_1.service;

import exam_1.model.Catalog;

import java.util.ArrayList;
import java.util.List;

public class CatalogService implements IService<Catalog, Integer> {
	
	private List<Catalog> listCatalog = new ArrayList<>();
	
	@Override
	public List<Catalog> getAll() {
		return listCatalog;
	}
	
	@Override
	public void save(Catalog catalog) {
		if (findById(catalog.getCatalogId()) == null) {
			listCatalog.add(catalog);
			System.out.println("XÓA THÀNH CÔNG");
		} else {
			listCatalog.set(listCatalog.indexOf(findById(catalog.getCatalogId())), catalog);
			System.out.println("SỬA THÀNH CÔNG");
		}
	}
	
	@Override
	public Catalog findById(Integer integer) {
		for (Catalog c : listCatalog) {
			if (c.getCatalogId() == integer) {
				return c;
			}
		}
		return null;
	}
	
	@Override
	public void delete(Integer integer) {
		if (findById(integer) != null) {
			listCatalog.remove(findById(integer));
			System.out.println("XÓA THÀNH CÔNG");
		} else {
			System.err.println("Không có mục lục này");
		}
	}
	
	public int getNewId() {
		int idMax = 0;
		for (Catalog c : listCatalog) {
			if (c.getCatalogId() > idMax) {
				idMax = c.getCatalogId();
			}
		}
		return idMax + 1;
	}
}
