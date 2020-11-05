package tw.com.BeMet.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface BaseDAO<T> {
	
	public T getById(Serializable id);
	
	public List<T> searchAll();
	
	public T saveAndReturn(T entity);
	
	public void save(T entity);
	
	public void update(T entity);
	
	public void delete(T entity);

	public int count(DetachedCriteria detachedCriteria);
	
}
