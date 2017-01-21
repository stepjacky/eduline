package org.jackysoft.edu.extend.jasperreport;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSource;

public class JRRawDataSource<T> extends JRAbstractBeanDataSource {

	private T current;
	
	public JRRawDataSource(T bean) {
		super(true);
		this.current = bean;
	}

	@Override
	public void moveFirst() throws JRException {
		// TODO Auto-generated method stub
		
	}

	boolean next = true;
	@Override
	public boolean next() throws JRException {
		if(next) {
			next = false;
			return true;
		}
		return false;
	}

	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		return getFieldValue(current, jrField);
	}

	

}
