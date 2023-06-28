package exam_1.service;

import java.util.List;

public interface IService<T,E> {
	List<T> getAll();
	void save(T t);
	T findById(E e);
	void delete(E e);
}
