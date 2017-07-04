package org.jackysoft.edu.service.base;

import java.util.List;

import javax.servlet.http.Part;

import org.jackysoft.edu.formbean.ZtreeNode;
import org.jackysoft.edu.view.ActionResult;
import org.jackysoft.query.Pager;
import org.jackysoft.query.QueryBuilder;

public interface ServiceProvider<S,T> {
    List<T>  findAll();
    List<T>  findAll(int page,int offset);
    List<T>  findAll(QueryBuilder qc);
    List<T>  search(String query);
    List<T>  findAll(QueryBuilder qc ,int page,int offset);
    Pager<T> findByPager(int page,int offset,boolean ajax);
    Pager<T>    findPager(QueryBuilder qc ,int page,int offset);
    T  findById(S s);
    ActionResult save(T t);
    List<ActionResult> saveAll(List<T> list);
    void update(T t);
    void sort(S s,int sort);
    /**
     *
     *  更新由props属性特征所代表的属性,并根据条件s 定位
	 * @param id 主键,符合主键格式
	 * @param props 属性字符串 name:value[;name:value]

	 * */
    void updatePartial(S id,String props);
    void updateSimple(S id,String key,String value);
    void update(QueryBuilder qc);
    void remove(QueryBuilder qc);
    void removeById(S s);
    void upload(T bean , Part part);
    List<ZtreeNode> ztree(String parent);
    

    
}
