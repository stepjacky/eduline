package org.jackysoft.edu.controller;

import java.util.List;

import org.jackysoft.edu.entity.Chapter;
import org.jackysoft.edu.formbean.ZtreeNode;
import org.jackysoft.edu.service.base.AbstractService;
import org.jackysoft.edu.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/chapter")
public class ChapterController extends AbstractController<String, Chapter> {

	@Autowired
	protected ChapterService service;

	private static final int IMAGE_WIDTH=800;

	@ResponseBody
	@RequestMapping("/ztree")
	public List<ZtreeNode> ztree(@RequestParam("parent") String parent) {
		List<ZtreeNode> beans = service.ztree(parent);
		return beans;
	}

	@Override
	public AbstractService<String, Chapter> getService() {
		return service;
	}

}