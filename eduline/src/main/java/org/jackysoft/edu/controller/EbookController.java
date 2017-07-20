package org.jackysoft.edu.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.UUID;

import javax.servlet.http.Part;

import org.jackysoft.edu.entity.Ebook;
import org.jackysoft.edu.service.base.AbstractService;
import org.jackysoft.edu.service.EbookService;
import org.jackysoft.query.Pager;
import org.jackysoft.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ebook")
public class EbookController extends AbstractController<String, Ebook> {

	@Autowired
	protected EbookService service;




	@RequestMapping(value = "/cover/path", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> coverPath(@RequestParam("path") String path) {

		
		InputStream ins = null;
		HttpHeaders headers = new HttpHeaders();
		InputStreamResource isr = null;
		try {
			ins = new FileInputStream(String.format("%s%s%s", filelocation, File.separator, path));

			headers.setContentType(MediaType.valueOf("image/" + path.substring(path.lastIndexOf('.'))));
			headers.setContentLength(ins.available());
			headers.setContentDispositionFormData("attachment", path);
			isr = new InputStreamResource(ins);
		} catch (IOException e) {
			logger.error(e);
		}

		return new ResponseEntity<InputStreamResource>(isr, headers, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> download(@PathVariable("id")String id) throws IOException {
		service.incrementClicked(id);
		return super.download(id);
	}

	@RequestMapping(value = "/with/tags/{page}/{offset}")
	public ModelAndView withtags(@RequestParam("tags") String tags, @PathVariable("page") int page,
			@PathVariable("offset") int offset

	) {
		QueryBuilder qc = new QueryBuilder();
		qc.setQueries("tags`LIKE`" + tags);
		Pager<Ebook> pager = service.findPager(qc, page, offset);
		ModelAndView mav = new ModelAndView("studentindex");
		mav.addObject("pager", pager);
		mav.addObject("tag", tags);
		mav.addObject("tags", service.findTags());
		return mav;
	}

	@RequestMapping(value = "/cover/upload", method = RequestMethod.POST)
	@ResponseBody
	public String coverupload(

			@RequestParam("cover") Part cover) throws Exception {
		String realPath = UUID.randomUUID().toString()
				+ cover.getSubmittedFileName().substring(cover.getSubmittedFileName().lastIndexOf('.'));
		Files.copy(cover.getInputStream(),
				new File(String.format("%s%s%s", filelocation, File.separator, realPath)).toPath());
		return realPath;
	}

	@RequestMapping(value = "/multile/upload", method = RequestMethod.POST)
	public ModelAndView uploads(Ebook bean, @RequestParam("file") Part file) throws Exception {
		ModelAndView mav = new ModelAndView("upload");
		proceedUpload(bean, file);
		return mav;
	}

	@Override
	protected void proceedUpload(Ebook bean, Part part) throws Exception {
		Ebook book = bean == null ? new Ebook() : bean;
		book.setFileType(part.getContentType());
		book.setSize(part.getSize());
		book.setFiretime(System.currentTimeMillis());
		book.setName(part.getSubmittedFileName());
		String realPath = UUID.randomUUID().toString()
				+ part.getSubmittedFileName().substring(part.getSubmittedFileName().lastIndexOf('.'));
		book.setRealPath(realPath);
		Files.copy(part.getInputStream(),
				new File(String.format("%s%s%s", filelocation, File.separator, realPath)).toPath());
		logger.info(bean);
		getService().save(book);

	}

	@RequestMapping(value = "/student/{page}")
	public ModelAndView studentindex(@PathVariable("page") int page) {
		ModelAndView mav = new ModelAndView("studentindex");
		Pager<Ebook> pager = getService().findByPager(page, 12, false);
		mav.addObject("pager", pager);
		mav.addObject("tags", service.findTags());
		return mav;
	}

	@Override
	public AbstractService<String, Ebook> getService() {
		return service;
	}

}