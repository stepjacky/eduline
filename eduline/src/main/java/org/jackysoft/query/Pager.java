package org.jackysoft.query;


import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jackysoft.utils.URLMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;

public final class Pager<T> {

	public static final int DEFAULT_OFFSET = 10;
	
	// 当前页码
	protected int page = 0;

	// 总页数
	protected int pages = 0;

	// 总记录数
	protected long count = 0;

	// 每页条数
	protected int offset = DEFAULT_OFFSET;

	protected boolean ajaxable = true;
	
	protected String contextPath="";
	// 记录实体
	protected Iterable<T> dataList;

	private String uri;
	// //////////////////////////////////////////////////////////	

	protected int from = 0;

	
	
	protected QueryBuilder  query = new QueryBuilder();
	
	public static final <E> Pager<E> EMPTY_PAGER(){
		return new Pager<E>();
	}
	
	public static final <T> Pager<T> build(int page,long count,Iterable<T> dataList){
		Pager<T> p = new Pager<T>();
		p.setPage(page);
		p.setCount(count);
		p.setOffset(DEFAULT_OFFSET);
		p.setDataList(dataList);
		p.setAjaxable(false);
		return p;
	}
	public static final <T> Pager<T> build(int page,int offset,long count,Iterable<T> dataList){
		Pager<T> p = new Pager<T>();
		p.setPage(page);
		p.setCount(count);
		p.setOffset(offset==0?DEFAULT_OFFSET:offset);
		p.setDataList(dataList);
		p.setAjaxable(false);
		return p;
	}
	
	public static final <T> Pager<T> build(int page,int offset,long count,Iterable<T> rows,boolean ajaxable){
		Pager<T> p = build(page,offset,count,rows);
		p.setAjaxable(ajaxable);
		return p;
	}
	
	public long getEnd() {
		if (page == (pages - 1)) {
			// 最后一页
			long lst = count % offset;
			return getFrom() + lst;
		}
		return getFrom() + offset;
	}
	
	public long getFrom() {
		from = page * offset;
		return from < 0 ? 0 : from;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	
	public void setCount(long count) {
		this.count = count;
		
		if (count > 0) {
			double dcount = Double.valueOf(this.count+"");
		    double doffset = Double.valueOf(this.offset+"");
		    pages = new Double(Math.ceil(dcount / doffset)).intValue();
		}else
			pages = 0;
		if (page > pages)
			page = pages;
	}

	public void setDataList(Iterable<T> rows) {
		this.dataList = rows;
	}

	public Iterable<T> getDataList(){
		return this.dataList;
	}
	public enum NavType {
		prev, next, page, first, last, info
	}	
	
	@JsonIgnore
	public Element getElement() {
		Document doc = Jsoup.parse("");
		Element div = doc.createElement("nav");
		Element ul = doc.createElement("ul");
		ul.addClass("pagination");
		div.appendChild(ul);
		int cpage = page;
		int ppages = pages;
		ul.appendChild(pager(0, false, doc, NavType.first));

		if (cpage >= 1) {
			// 可以显示上页按钮
			ul.appendChild(pager(cpage - 1, false, doc, NavType.prev));
		}
		// 默认显示10个页码
		Integer end = ((cpage + 10) >= ppages) ? ppages : (cpage + 10);

		// 如果当前页到最后一页数大于所要显示的页码个数10
		// 则按照顺序从当前页往后显示10个页码数
		for (int page = cpage; page < end; page++) {
			ul.appendChild(pager(page, (page == cpage), doc, NavType.page));
		}

		if (cpage < (ppages - 1)) {
			ul.appendChild(pager(cpage + 1, false, doc, NavType.next));
		}

		ul.appendChild(pager(pages - 1, false, doc, NavType.last));
		ul.appendChild(pager(cpage, false, doc, NavType.info));

		return div;
	}

	
	public String getPagination() {

		return getElement().outerHtml();

	}

	/**
	 * @param page
	 *            页码
	 * @param actived
	 * 
	 * */
	Element pager(int page, boolean actived, Document doc, NavType navType) {

		page = page < 0 ? 0 : page;
		Element li = doc.createElement("li");
		Element a = doc.createElement("a");
		a.attr("page", page + "");
		if(ajaxable){
			a.attr("href", "javascript:void(0);");			
			a.attr("link", getAction(page));
		}else{
			a.attr("href", getAction(page));
			a.attr("target","_self");
		}
		if (actived) {
			li.addClass("active");
			a.addClass("noclick");
		}
		switch (navType) {
		case prev:
			a.text("上页");
			li.appendChild(a);
			break;
		case next:
			a.text("下页");
			li.appendChild(a);
			break;
		case page:
			a.text(page + 1 + "");

			li.appendChild(a);
			break;
		case first:
			a.text("首页");

			li.appendChild(a);
			break;
		case last:
			a.text("末页");
			li.appendChild(a);
			break;
		case info:
			String str = String.format(
					"第  %s/%s 页 , 共  %s 条记录,当前显示从 %s 到 %s 条", getPage() + 1,
					pages, count, getFrom(), getEnd());
			Element span = li.appendElement("span");
			span.text(str);

			break;
		}

		return li;
	}
		
	public String getAction(int page) {
		
		String uri = getUri();
		String[] parts = uri.split("\\?");
		String baseURI = altPage(parts[0],page);
		
		if(parts.length==1) return baseURI;
		String queryString = parts[1];
		Set<String> pset = new HashSet<>();
		if (!Strings.isNullOrEmpty(queryString)) {
			Map<String, String> map = URLMapper.with(queryString);			
			map.putAll( URLMapper.with(query.semanticHttpQuery()));
			map.forEach((name,value)->{
				if("_csrf".equals(name))return;
				if("page".equals(name)){
					pset.add("page="+page);
				}else {
					pset.add(name + "=" + (value));
				}
			});
			if(map.keySet().contains("page")){
				baseURI = parts[0];
			}
		}	
		String query = Joiner.on('&').join(pset);
		return contextPath+baseURI+ (Strings.isNullOrEmpty(query)?"":"?")+query;		
				
	}

	
	String altPage(String uri,int page) {
		
		return uri.substring(0, uri.lastIndexOf('/')+1)+page;
	}
	
    public boolean isAjaxable() {
		return ajaxable;
	}


	public void setAjaxable(boolean ajaxable) {
		this.ajaxable = ajaxable;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getPages() {
		return pages;
	}	
	
}
