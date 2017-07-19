package org.jackysoft.edu.service.base;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.jackysoft.edu.formbean.ZtreeNode;
import org.jackysoft.edu.view.ActionResult;
import org.jackysoft.query.Pager;
import org.jackysoft.query.QueryBuilder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;

public abstract class AbstractService<S, T> implements ServiceProvider<S, T> {

	@Override
	public List<T> findAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<T> findAll(int page, int offset) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<T> findAll(QueryBuilder qc) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<T> search(String query) {

		throw new UnsupportedOperationException();
	}

	@Override
	public List<T> findAll(QueryBuilder qc, int page, int offset) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void beforeInput(ModelAndView mav) {

	}

	@Override
	public Pager<T> findByPager(int page, int offset, boolean ajax) {

		throw new UnsupportedOperationException();
	}

	@Override
	public Pager<T> findPager(QueryBuilder qc, int page, int offset) {

		throw new UnsupportedOperationException();
	}

	@Override
	public T findById(S s) {

		throw new UnsupportedOperationException();
	}

	@Override
	public ActionResult save(T t) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<ActionResult> saveAll(List<T> list) {
		throw new UnsupportedOperationException();

	}

	@Override
	public void update(T t) {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updatePartial(S id, String props) {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateSimple(S id, String key, String value) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void update(QueryBuilder qc) {
		throw new UnsupportedOperationException();

	}

	@Override
	public void remove(QueryBuilder qc) {
		throw new UnsupportedOperationException();

	}

	@Override
	public void removeById(S s) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void upload(T bean, Part part) {

		throw new UnsupportedOperationException();
	}



	@Override
	public List<ZtreeNode> ztree(String parent) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void sort(S s, int sort) {
		throw new UnsupportedOperationException();
	}

	protected StringBuffer parseDoc(File file){
		StringBuffer sb  = null;
		try {
			sb = parseDoc(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb;
	}

	protected StringBuffer parseDoc(InputStream file){
		StringBuffer sb = new StringBuffer();
		try(WordExtractor extractor = new WordExtractor(file)){

			extractor.close();
			String[] texts = extractor.getParagraphText();

			if(texts==null||texts.length==0){
				return sb;
			}
			for(String t:texts)sb.append(t);
		} catch (IOException e) {
			sb.append(e.getMessage());
			return sb;
		}

		return sb;

	}

	protected StringBuffer parseDocx(Path file)throws IOException{

		StringBuffer sb = new StringBuffer();
		try(XWPFWordExtractor extractor =
					new XWPFWordExtractor(new XWPFDocument(new FileInputStream(file.toFile())))){

			String text = extractor.getText();
			sb.append(text);

		} catch (IOException e) {
			throw e;
		}

		return sb;
	}
	protected StringBuffer parseDocx(InputStream file)throws IOException{

		StringBuffer sb = new StringBuffer();
		try(XWPFWordExtractor extractor = new XWPFWordExtractor(new XWPFDocument(file))){

			String text = extractor.getText();
			sb.append(text);

		} catch (IOException e) {
			throw e;
		}

		return sb;
	}
}
