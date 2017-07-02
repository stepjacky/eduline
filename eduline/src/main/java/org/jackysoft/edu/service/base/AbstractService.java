package org.jackysoft.edu.service.base;

import java.util.List;

import javax.servlet.http.Part;

import org.jackysoft.edu.formbean.ZtreeNode;
import org.jackysoft.edu.mapper.AbstractMapper;
import org.jackysoft.query.Pager;
import org.jackysoft.query.QueryBuilder;

public abstract class AbstractService<S, T> implements ServiceProvider<S, T> {

	@Override
	public List<T> findAll() {

		return null;
	}

	@Override
	public List<T> findAll(int page, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAll(QueryBuilder qc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> search(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAll(QueryBuilder qc, int page, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pager<T> findByPager(int page, int offset, boolean ajax) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pager<T> findPager(QueryBuilder qc, int page, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T findById(S s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PreResult save(T t) {
		return null;

	}

	@Override
	public List<PreResult> saveAll(List<T> list) {
	    return null;

	}

	@Override
	public void update(T t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updatePartial(S id, String props) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateSimple(S id, String key, String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(QueryBuilder qc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(QueryBuilder qc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeById(S s) {
		// TODO Auto-generated method stub

	}

	@Override
	public void upload(T bean, Part part) {


	}

	@Override
	public List<ZtreeNode> ztree(String parent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sort(S s, int sort) {

	}
}
