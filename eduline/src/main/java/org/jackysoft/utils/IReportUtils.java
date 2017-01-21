package org.jackysoft.utils;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;

public class IReportUtils {
	private static final Log log = LogFactory.getLog(IReportUtils.class);

	public static void compileReportDirectory(File jrbase) {

		if (!jrbase.isDirectory())
			throw new IllegalStateException("路径" + jrbase.getAbsolutePath()
					+ "不是一个目录");
		File[] jrxmls = jrbase.listFiles(fn->fn.getName().endsWith(".jrxml"));
		log.info("开始编译jasper报表.......");
		for (File jr : jrxmls){
			
			compileReportFile(jr);
		}
		log.info("编译jasper报表完成");
	}

	public static void compileReportFile(File srcPath) {
		try {

			log.info("start compile "+srcPath.getAbsolutePath());
			String destName = srcPath.getAbsolutePath().substring(0, srcPath.getAbsolutePath().length()-5)+"jasper";
			JasperCompileManager.compileReportToFile(srcPath
					.getAbsolutePath(),destName);
			log.info(destName + "  done!");

		} catch (JRException e) {
			log.info("编译jasper报表出错 : \n\r" + e);
            log.error(e);
		}
	}
	public static void compileReportFile(String srcPath) {
		compileReportFile(new File(srcPath));
	}
}
