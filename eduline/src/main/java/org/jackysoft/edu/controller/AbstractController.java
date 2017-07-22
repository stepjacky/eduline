package org.jackysoft.edu.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jackysoft.edu.formbean.MediaFile;
import org.jackysoft.edu.service.base.AbstractService;
import org.jackysoft.edu.view.ActionResult;
import org.jackysoft.query.Pager;
import org.jackysoft.query.QueryBuilder;
import org.jackysoft.utils.FormOption;
import org.jackysoft.utils.JsonValidator;
import org.jackysoft.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;

public abstract class AbstractController<S, T> {

	static final Log logger = LogFactory.getLog(AbstractController.class);

	@Value("${uploaded.location}")
	protected String filelocation;
	
	@Autowired
	private JsonValidator jsonValidator;
	
	boolean isJson(String json) {
		return !(Strings.isNullOrEmpty(json) || json.length()<4 || !jsonValidator.validate(json));
	}


	@PostMapping("/save")
	@ResponseBody
	public ActionResult save(@RequestBody T bean){
		if(bean!=null){
			return getService().save(bean);

		}
		return ActionResult.FAILURE;
	}

	@ResponseBody
	@PostMapping("/saves")
	public ActionResult saves(@RequestBody List<T> beans){
		ActionResult result = new ActionResult();
		if(beans!=null && !beans.isEmpty()) {
			List<ActionResult> rsts = this.getService().saveAll(beans);
			rsts.forEach(r->{
				result.setFlag(result.isFlag()&&r.isFlag());
				result.setMessage(result.getMessage()+','+r.getMessage());
			});
			return result;
		}
		result.setFlag(false);
		result.setMessage("Empty collection of object to save");
		return result;
	}

	@ResponseBody
	@PostMapping("/update")
	public ActionResult update(@RequestBody T bean){
		return this.getService().update(bean);

	}

	@RequestMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") S id) {
		ModelAndView mav = new ModelAndView("edit");
		T item = this.getService().findById(id);
		mav.addObject("bean", item);
		this.getService().beforeInput(mav);
		return mav;
	}

	@ResponseBody
	@RequestMapping("/remove/{id}")
	public ActionResult remove(@PathVariable("id") S id) {

		return this.getService().removeById(id);


	}

	@ResponseBody
	@RequestMapping("/get/{id}")
	public T getOne(@PathVariable("id")S id){
		return getService().findById(id);
	}


	
	@RequestMapping(value = "/persiste", method = RequestMethod.POST)
	public ModelAndView persiste(@RequestParam("data") String data) {
		ModelAndView mav = new ModelAndView("persiste");		
		if (!isJson(data))return mav;		
		Gson gson = new Gson();
		Type st = this.getClass().getGenericSuperclass();
		Type[] type = ((ParameterizedType) st).getActualTypeArguments();
		T bean = null;
		try {
		 bean = gson.fromJson(data, type[1]);		
		}catch( JsonParseException  e) {
			logger.error(e);
			return mav;
		}
		if(bean!=null) this.getService().save(bean);
		return mav;
	}



	@RequestMapping(value = "/persisties", method = RequestMethod.POST)
	public ModelAndView persisties(@RequestParam("data") String data) {
		ModelAndView mav = new ModelAndView("persiste");
		if ( !isJson(data) )
			return mav;
		Gson gson = new Gson();
		Type st = this.getClass().getGenericSuperclass();
		Type[] type = ((ParameterizedType) st).getActualTypeArguments();
		data = data.substring(2, data.length() - 2);
		
		List list = null;
		
		try {
			list = Splitter.on("},{").splitToList(data).stream()
			.filter(t -> !Strings.isNullOrEmpty(t))
			.map(t -> gson.fromJson("{" + t + "}", type[1]))
			.collect(Collectors.toList());
			}catch( JsonParseException  e) {
				logger.error(e);
				return mav;
			}
		
		if(list!=null && !list.isEmpty())this.getService().saveAll(list);
		return mav;
	}



	@RequestMapping(value = "/remove/qc")
	public ModelAndView removeByqc(@RequestParam("query") String query) {
		ModelAndView mav = new ModelAndView("remove");
		if(Strings.isNullOrEmpty(query))return mav;
		QueryBuilder qc = new QueryBuilder();
		qc.setQueries(query);
		this.getService().remove(qc);

		return mav;
	}

	@RequestMapping(value = "/findall")
	public ModelAndView findAll() {
		ModelAndView mav = new ModelAndView("findall");
		List<T> list = this.getService().findAll();
		mav.addObject("beans", list);
		return mav;
	}

	@ResponseBody
	@RequestMapping(value = "/options")
	public List<FormOption> options(
			@RequestParam(value = "query", defaultValue = "") String query,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "offset", defaultValue = "10") int offset) {

		List<T> list = null;
		if (Strings.isNullOrEmpty(query))
			list = getService().findAll();
		else {
			QueryBuilder qc = new QueryBuilder();
			qc.setQueries(query);
			list = getService().findAll(qc, page, offset);
		}

		return list.stream().filter(t -> t != null)
				.map(StringUtils::convertFormOption)
				.collect(Collectors.toList());

	}

	
	@ResponseBody
	@RequestMapping(value = "/search")
	public List<FormOption> search(
			@RequestParam(value = "query", defaultValue = "") String query) {

		List<T> list = null;
		if (Strings.isNullOrEmpty(query))
			list = getService().findAll();
		else {
			list = getService().search(query);
		}

		return list.stream().filter(t -> t != null)
				.map(StringUtils::convertFormOption)
				.collect(Collectors.toList());

	}
	


	@RequestMapping(value = "/pager/{page}")
	public ModelAndView pager(
			@PathVariable("page") int page,
			@RequestParam(value = "offset", required = false, defaultValue = "10") int offset,
			@RequestParam(value = "ajax", required = false, defaultValue = "true") boolean ajax) {
		ModelAndView mav = new ModelAndView("pager");
		Pager<T> pager = getService().findByPager(page, offset, ajax);
		mav.addObject("pager", pager);
		mav.addObject("ajax", ajax);
		return mav;
	}

	@RequestMapping(value = "/query/{page}")
	public ModelAndView query(
			@RequestParam(value = "query", required = false, defaultValue = "") String query,
			@RequestParam(value = "group", required = false, defaultValue = "") String group,
			@RequestParam(value = "order", required = false, defaultValue = "") String order,
			@PathVariable("page") int page,
			@RequestParam(value = "offset", required = false, defaultValue = "10") int offset,
			@RequestParam(value = "ajax", required = false, defaultValue = "true") boolean ajax) {
		ModelAndView mav = new ModelAndView("query");
		Pager<T> pager = queryPager(query,group,order, page, offset, ajax);
		mav.addObject("pager", pager);
		mav.addObject("qstring", String.format("query=%s&group=%s&order=%s", query,group,order));
		mav.addObject("ajax", ajax);
		return mav;
	}

	@RequestMapping(value = "/datatable/{page}")
	public ModelAndView datatable(
			@RequestParam(value = "query", required = false, defaultValue = "") String query,
			@PathVariable("page") int page,
			@RequestParam(value = "offset", required = false, defaultValue = "10") int offset,
			@RequestParam(value = "ajax", required = false, defaultValue = "true") boolean ajax) {
		ModelAndView mav = new ModelAndView("datatable");
		Pager<T> pager = queryTable(query, null, null, page, offset, ajax);
		mav.addObject("pager", pager);
		mav.addObject("qstring", String.format("query=%s", query));
		mav.addObject("ajax", ajax);
		return mav;
	}

	/**
	 * @param query
	 * @param group
	 * @param order
	 * @param page
	 * @param offset
	 * @param ajax
	 * 
	 * **/

	protected Pager<T> queryPager(String query, String group, String order, int page,
			int offset, boolean ajax) {
		Pager<T> pager = null;
		QueryBuilder qc = new QueryBuilder();
        qc.setGroupBy(group);
        qc.setOrders(order);
		qc.setQueries(query);
		pager = getService().findPager(qc, page, offset);
		pager.setAjaxable(ajax);
		return pager;
	}

	protected Pager<T> queryTable(String query, String group, String order, int page,
			int offset, boolean ajax){
		return  this.queryPager(query, group, order, page, offset, ajax);
	}
	
	
	@RequestMapping(value = "/upload/formbean", method = RequestMethod.POST)
	public ModelAndView upload(T bean, @RequestParam("file") Part part) {
		ModelAndView mav = new ModelAndView("upload");
		try {
			proceedUpload(bean,part);
		} catch (Exception e) {
			logger.error(e);
		}
		return mav;
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ModelAndView uploadonly(@RequestParam("file") Part part) {
		ModelAndView mav = new ModelAndView("upload");
		try {
			proceedUpload(null,part);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return mav;
	}

	protected  void proceedUpload(T bean , Part part) throws Exception{};
	
	@RequestMapping(value = "/update/partial", method = RequestMethod.POST)
	public ModelAndView updatepartial(@RequestParam("id") S id,
			@RequestParam("props") String props) {
		ModelAndView mav = new ModelAndView("update");
		getService().updatePartial(id, props);
		return mav;
	}
	
	@RequestMapping(value = "/update/simple/part/{id}/{key}/{value}", method = RequestMethod.GET)
	public ModelAndView updatesimple(
			@PathVariable("id") S id,
			@PathVariable("key") String key,
			@PathVariable("value")String value) {
		ModelAndView mav = new ModelAndView("update");
		getService().updatePartial(id, key+":"+value);
		return mav;
	}

	@RequestMapping(value = "/update/qc", method = RequestMethod.POST)
	public ModelAndView updateqc(@RequestParam("query") String query) {
		ModelAndView mav = new ModelAndView("update");
		QueryBuilder qc = new QueryBuilder();
		qc.setQueries(query);
		getService().update(qc);
		return mav;
	}


	
	@RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> download(@PathVariable("id") S id)throws IOException {
	  
	    T t = getService().findById(id);
	    if(!(t instanceof MediaFile)) return new ResponseEntity<InputStreamResource>(HttpStatus.NOT_IMPLEMENTED);
	    
	    MediaFile mfile = (MediaFile) t;
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.valueOf(mfile.getContentType()));
	    headers.setContentLength(mfile.getContentLength());
	    headers.setContentDispositionFormData("attachment", StringUtils.toDownloadFileName(mfile.getFilename()));
        InputStreamResource isr = new InputStreamResource(new FileInputStream(new File(String.format("%s%s%s", filelocation,File.separator,mfile.getRealPath()))));
	    return new ResponseEntity<InputStreamResource>(isr, headers, HttpStatus.OK);
	}
	

	
	JRDataSource retriveJRDataSource(String query) {
		
		
		return new JREmptyDataSource()  ;
		
	};
	
	@RequestMapping(value = "/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}

	@RequestMapping(value = "/input")
	public ModelAndView input(@RequestParam(value="param",required = false)String param) {
		ModelAndView mav = new ModelAndView("/input");
		mav.addObject("param",param);
		this.getService().beforeInput(mav);
		return mav;
	}
	@RequestMapping(value = "/anyway/{view}")
	public ModelAndView anyway(
			@PathVariable("view")String view,HttpServletRequest req) {
		
		ModelAndView mav = new ModelAndView(view);
		req.getParameterMap().entrySet().forEach(p->{
			String name =  p.getKey();
			String[] val = p.getValue();
			if(val!=null && val.length>0){
				mav.addObject(name, val[0]);
			}
		});
		return mav;
	}

	public abstract AbstractService<S, T> getService();
}
