package org.jackysoft.edu.bean;

import static java.util.stream.Collectors.toSet;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.script.ScriptException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jackysoft.edu.annotation.Column;
import org.jackysoft.edu.annotation.IdStrategy;
import org.jackysoft.edu.annotation.Table;
import org.jackysoft.edu.form.FormInput;
import org.jackysoft.utils.ClassMetaUtils;
import org.jackysoft.utils.StringUtils;

import com.google.common.base.Joiner;
import com.google.common.base.Ticker;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import jetbrick.template.JetEngine;
import jetbrick.template.JetTemplate;
import jetbrick.util.tuple.NameValuePair;

public class SourceGenerator {

	static final Log logger = LogFactory.getLog(SourceGenerator.class);
	public static final String entitypkg = "org.jackysoft.edu.entity";
	static final JetEngine jetEngine = JetEngine.create();

	public static Set<String> parseCreateSql(final String basePackage) {

		Set<Class<?>> classes = scanedClass(Table.class, entitypkg);
		Set<String> sqlset = classes.stream()
				.map(c -> new String(classToSql(c))).collect(toSet());

		return sqlset;

	}

	public static String classToSql(Class<?> cls) {
		StringBuffer sb = new StringBuffer();
		Table t = cls.getAnnotation(Table.class);
		String tableName = t.prefix() + cls.getSimpleName().toLowerCase();
		Field[] fields = cls.getDeclaredFields();
		Set<String> pkname = Sets.newHashSet();
		Map<String, String> coldefine = Maps.newHashMap();
		Arrays.asList(fields)
		.stream()
		.filter(f->f.isAnnotationPresent(Column.class))
		.forEach(fd->{
			Column colano = fd.getAnnotation(Column.class);
			
			if (colano.id()) {
				pkname.add(fd.getName());
				if(colano.idStrategy()==IdStrategy.AUTOINC) {
					String extra =  " AUTO_INCREMENT ";
					coldefine
					.put(fd.getName(), columnDefMap.get(fd.getType().getName())+extra);
				}else {
					coldefine
					.put(fd.getName(), columnDefMap.get(fd.getType().getName())+extraMap.get(fd.getType().getName()));
				}
				
			}else {
			
				coldefine
						.put(fd.getName(), columnDefMap.get(fd.getType().getName())+extraMap.get(fd.getType().getName()));
			}
		});
		sb.append(String.format("\n\nDROP TABLE IF EXISTS `%s`;\n",tableName));
		sb.append(String.format("CREATE TABLE IF NOT EXISTS `%s` (\n",
				tableName));
		Set<String> coldef = coldefine
				.entrySet()
				.stream()
				.map(e -> String.format("\t`%s`  %s ", e.getKey(), e.getValue()))
				.collect(Collectors.toSet());

		sb.append(Joiner.on(", \n").join(coldef));
		if (!pkname.isEmpty()) {
			sb.append(",\n\t PRIMARY KEY (`");
			sb.append(Joiner.on("`,`").join(pkname));
			sb.append("`)\r");
		}
		sb.append(");\n\n");
		/*coldefine
		.entrySet()
		.forEach(e->{
			sb.append(String.format("CREATE INDEX %s ON %s (`%s`);\n",StringUtils.randomstring(),tableName,e.getKey()));
		});*/
		
		return sb.toString();
	}

	static Map<String, Object> resolveJetBrickContext(Class<?> cls) {		
		Table table = cls.getAnnotation(Table.class);
		String tableName = table.prefix() + cls.getSimpleName().toLowerCase();
		Field[] fields = cls.getDeclaredFields();
		Set<String> pkname = Sets.newHashSet();
		Map<String, String> coldefine = Maps.newHashMap();
		Set<String> fieldNames = Sets.newHashSet();
		String pktype = "string";
		List<FormInput> formInputs = new ArrayList<>();
		for (Field fd : fields) {
			if (fd.isAnnotationPresent(Column.class)) {

				Column colano = fd.getAnnotation(Column.class);
     			StringBuffer optHtml = new StringBuffer("");
					String html = String.format(colano.formType().toString(),
							fd.getName(), fd.getName(),"%s","%s","%s");
					if (colano.data().length>1 && colano.data().length%2==0) {
						HashMap<String, String> opts = new HashMap<String,String>();
						for(int i=0;i<colano.data().length;i+=2) {
							opts.put(colano.data()[i], colano.data()[i+1]);
						}						

						opts.entrySet()
								.stream()
								.forEach(
										e -> optHtml.append(String
												.format("<option value='%s'>%s</option>",
														e.getKey(),
														e.getValue())));
					}else if(colano.data().length==1) {
						html = StringUtils.addJsonSource(html, colano.data()[0]);						
					}

					FormInput fi = new FormInput(fd.getName(), colano.label(),
							String.format(html, optHtml.toString(),fd.getName(),fd.getName()));
                    fi.setListed(colano.list());
                    fi.setInputed(colano.input());
                    fi.setQueried(colano.query());
					formInputs.add(fi);
			
				fieldNames.add(fd.getName());
				if (colano.id()) {
					pkname.add(fd.getName());
					pktype = fd.getType().getSimpleName();
					if (fd.getType().isPrimitive()) {
						pktype = p2boxMap.get(pktype.toLowerCase());
					}

				}
				fd.getType().getName();
				coldefine
						.put(fd.getName(), columnDefMap.get(fd.getType().getName()));
			}
		}

		Stream<String> stream = fieldNames.stream();
		Set<String> updates = stream
				.filter(n -> !pkname.contains(n))
				.map(s -> String.format(String.format("%s=VALUES(`%s`)", s, s)))
				.distinct().collect(Collectors.toSet());
        long inputSize = formInputs.stream().filter(f->f.isInputed()).count();
        long listSize = formInputs.stream().filter(f->f.isListed()).count();
		Map<String, Object> context = Maps.newHashMap();
		context.put("table", tableName);
		context.put("aliasname", cls.getSimpleName().toLowerCase());
		context.put("entityFullname", cls.getName());
		context.put("entityShortname", cls.getSimpleName());
		context.put("javaMapper", cls.getSimpleName() + "Mapper");
		context.put("namespace",
				"org.jackysoft.edu.mapper." + cls.getSimpleName() + "Mapper");
		context.put("pktypealias", pktype.toLowerCase());
		context.put("pktype", pktype);
		context.put("pkname", pkname.isEmpty() ? "id" : pkname.iterator()
				.next());
		context.put("fields", "`" + Joiner.on("`,`").join(fieldNames) + "`");
		context.put("values", "#{" + Joiner.on("},#{").join(fieldNames) + "}");
		context.put("updates", Joiner.on(",").join(updates));
		String entityLabel = table.label();
		String pathspace = cls.getSimpleName().toLowerCase();
		context.put("pathspace", pathspace);
		context.put("entityLabel", entityLabel);
		context.put("formInputs", formInputs);
		context.put("inputSize", inputSize);
		context.put("listSize", listSize);
		context.put("contextPath", "/");
		return context;
	}

	public static Path[] resolveViewPathz(Class<?> cls, TempType tt) {

		String sname = cls.getSimpleName().toLowerCase();

		String jetxPath = String.format(
				"src/main/webapp/WEB-INF/views/%s/%s.jsp",sname,"%s");

		String jsPath = String.format(
				"src%smain%sresources%sstatic%sscripts%s%s%s%s.js",
				File.separator, File.separator, File.separator, File.separator,
				File.separator, sname, File.separator, "%s");
		String cssPath = String.format(
				"src%smain%sresources%sstatic%scss%s%s%s%s.css",
				File.separator, File.separator, File.separator, File.separator,
				File.separator, sname, File.separator, "%s");
		List<String> viewNames = Arrays.asList("input", "edit", "pager",
				"query");
		switch (tt) {
		case Jetx:
			return viewNames
					.stream()
					.map(ft -> new File(String.format(jetxPath, ft)).toPath()
							.toAbsolutePath()).toArray(Path[]::new);
		case Script:
			return viewNames
					.stream()
					.map(ft -> new File(String.format(jsPath, ft)).toPath()
							.toAbsolutePath()).toArray(Path[]::new);
		case Css:
			return viewNames
					.stream()
					.map(ft -> new File(String.format(cssPath, ft)).toPath()
							.toAbsolutePath()).toArray(Path[]::new);
		default:
			break;
		}

		return null;

	}

	public static List<String> classToMapper(Class<?> cls, TempType type)
			throws ScriptException {

		String pakdir = String.format("org%sjackysoft%sedu%smapper%s%s%s",
				File.separator, File.separator, File.separator, File.separator,
				cls.getSimpleName(), "Mapper");

		File xmlMapperPath = new File(String.format(
				"src%smain%sjava%s%s%s", File.separator, File.separator,
				File.separator, pakdir, ".xml"));

		
		pakdir = String.format("org%sjackysoft%sedu%smapper%s%s%s",
				File.separator, File.separator, File.separator, File.separator,
				cls.getSimpleName(), "Mapper");

		File javaMapperPath = new File(
				String.format("src%smain%sjava%s%s%s", File.separator,
						File.separator, File.separator, pakdir, ".java"));

		
		pakdir = String.format("org%sjackysoft%sedu%sservice%s%s%s",
				File.separator, File.separator, File.separator, File.separator,
				cls.getSimpleName(), "Service");

		File javaServicePath = new File(
				String.format("src%smain%sjava%s%s%s", File.separator,
						File.separator, File.separator, pakdir, ".java"));

		pakdir = String.format("org%sjackysoft%sedu%scontroller%s%s%s",
				File.separator, File.separator, File.separator, File.separator,
				cls.getSimpleName(), "Controller");

		File javaControllerPath = new File(String.format(
				"src%smain%sjava%s%s%s", File.separator, File.separator,
				File.separator, pakdir, ".java"));

		Path[] javaPathz = {

		xmlMapperPath.toPath(), javaMapperPath.toPath(),
				javaServicePath.toPath(), javaControllerPath.toPath() };

		Path[] jetxPathz = resolveViewPathz(cls, TempType.Jetx);

		Path[] cssPathz = resolveViewPathz(cls, TempType.Css);

		Path[] jsPathz = resolveViewPathz(cls, TempType.Script);

		JetTemplate[] javaTemplates = {
				jetEngine.getTemplate("/java/XmlMapper.jetx"),
				jetEngine.getTemplate("/java/JavaMapper.jetx"),
				jetEngine.getTemplate("/java/JavaService.jetx"),
				jetEngine.getTemplate("/java/JavaController.jetx")

		};

		JetTemplate[] jetxTemplates = {

		jetEngine.getTemplate("/view/input.jetx"),
				jetEngine.getTemplate("/view/edit.jetx"),
				jetEngine.getTemplate("/view/pager.jetx"),
				jetEngine.getTemplate("/view/query.jetx"), };

		JetTemplate[] cssTemplates = {

		jetEngine.getTemplate("/css/input.jetx"),
				jetEngine.getTemplate("/css/edit.jetx"),
				jetEngine.getTemplate("/css/pager.jetx"),
				jetEngine.getTemplate("/css/query.jetx"), };

		JetTemplate[] jsTemplates = {
				jetEngine.getTemplate("/javascript/input.jetx"),
				jetEngine.getTemplate("/javascript/edit.jetx"),
				jetEngine.getTemplate("/javascript/pager.jetx"),
				jetEngine.getTemplate("/javascript/query.jetx"), };

		Map<String, Object> context = resolveJetBrickContext(cls);

		List<String> list = new ArrayList<>();
		switch (type) {
		case Css:
			jetx2Files(context, cssPathz, cssTemplates, list);
			break;
		case JavaSource:
			jetx2Files(context, javaPathz, javaTemplates, list);
			break;
		case Jetx:
			jetx2Files(context, jetxPathz, jetxTemplates, list);
			break;
		case Script:
			jetx2Files(context, jsPathz, jsTemplates, list);
			break;
		case Other:
			class2OtherViews(cls);
		case ALL:
			jetx2Files(context, javaPathz, javaTemplates, list);
			jetx2Files(context, jetxPathz, jetxTemplates, list);
			class2OtherViews(cls);
			jetx2Files(context, cssPathz, cssTemplates, list);
			jetx2Files(context, jsPathz, jsTemplates, list);
			break;
		default:
			break;

		}

		return list;
	}

	static void jetx2Files(Map<String, Object> context, Path[] paths,
			JetTemplate[] temps, final List<String> list) {

		IntStream.range(0, paths.length).forEach(
				i -> class2Source(context, temps[i], paths[i], list));
	}

	static String class2Source(Map<String, Object> context,
			JetTemplate template, Path destPath, List<String> resultList) {

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		template.render(context, out);
		byte[] data = out.toByteArray();

		try {
			if (!Files.exists(destPath.getParent()))
				Files.createDirectories(destPath.getParent());
			Writer writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(destPath.toAbsolutePath().toString()),
					"UTF-8"));
			try {
				writer.write(new String(data));
			} finally {
				writer.close();
			}
		} catch (IOException e) {
			logger.error(e);
		}
		String dstr = destPath.toAbsolutePath().toString();
		resultList.add(dstr);
		logger.info(dstr + "----[OK]");
		return new String(data);
	}

	static Set<Class<?>> scanedClass(
			final Class<? extends Annotation> annotationType,
			final String scanPackageName) {
		Set<Class<?>> classes = Sets.newHashSet();
		try {
			classes.addAll(ClassMetaUtils.findAnnotatedClasses(annotationType,
					scanPackageName, true));
		} catch (Exception e) {
			logger.error(e);
		}

		return classes;
	}

	static void class2OtherViews(Class<?> cls){
		String sname = cls.getSimpleName().toLowerCase();

		String jetxPath = String.format(
				"src/main/webapp/WEB-INF/views/%s/%s.jsp", sname,"%s");
		List<String> otherViews = Arrays.asList("update","upload","persiste","persisties","remove","index");
		
		JetTemplate template = jetEngine.getTemplate("/view/other.jetx");
		Map<String,Object> context = resolveJetBrickContext(cls);
		otherViews
		.stream()
		.map(ft -> new File(String.format(jetxPath, ft)).toPath()
				.toAbsolutePath())
				.forEach(p->class2Source(context,template,p,new ArrayList<>()));					
		 
	}
	
	static Set<Class<?>> entityClasses() {
		return scanedClass(Table.class, entitypkg);
	}

	static final LoadingCache<String, Set<NameValuePair<String, String>>> menuCache = CacheBuilder
			.newBuilder()
			.concurrencyLevel(10)
			.refreshAfterWrite(5L, TimeUnit.DAYS)
			.ticker(Ticker.systemTicker())
			.build(new CacheLoader<String, Set<NameValuePair<String, String>>>() {
				@Override
				public Set<NameValuePair<String, String>> load(String key)
						throws Exception {

					Set<NameValuePair<String, String>> menus = new HashSet<>();
					Set<Class<?>> classes = scanedClass(Table.class, entitypkg);
					menus.addAll(classes.stream().filter(c -> c != null)
							.map(m -> class2Menu(m)).collect(toSet()));
					return menus;
				}
			});

	static Set<NameValuePair<String, String>> mainMenus() {
		try {
			return menuCache.get("");
		} catch (ExecutionException e) {
			logger.error(e);
		}
		return null;
	}

	static NameValuePair<String, String> class2Menu(Class<?> cls) {

		Table t = cls.getAnnotation(Table.class);
		if (!t.asmenu())
			return null;
		return new NameValuePair<>(t.label(), "/"
				+ cls.getSimpleName().toLowerCase());
	}

	static final Map<String, String> columnDefMap = Maps.newHashMap();
	static final Map<String, String> extraMap = Maps.newHashMap();
	static final Map<String, String> p2boxMap = Maps.newHashMap();
	static {
		columnDefMap.put("java.lang.String", "VARCHAR(255) ");
		columnDefMap.put("java.lang.byte[]", "MEDIUMBLOB ");
		columnDefMap.put("java.lang.Integer", "INT ");
		columnDefMap.put("int", "INT ");
		columnDefMap.put("java.lang.Long", "BIGINT ");
		columnDefMap.put("long", "BIGINT ");
		columnDefMap.put("java.lang.Float", "FLOAT ");
		columnDefMap.put("float", "FLOAT ");
		columnDefMap.put("java.lang.Double", "DOUBLE ");
		columnDefMap.put("double", "DOUBLE ");
		columnDefMap.put("boolean", "INT ");
		columnDefMap.put("java.lang.Boolean", "INT ");

		
		extraMap.put("java.lang.String", " NULL DEFAULT ''");
		extraMap.put("java.lang.byte[]", " NULL DEFAULT NULL");
		extraMap.put("java.lang.Integer", " NOT NULL DEFAULT '0'");
		extraMap.put("int", " NOT NULL DEFAULT '0'");
		extraMap.put("java.lang.Long", " NOT NULL DEFAULT 0");
		extraMap.put("long", " NOT NULL DEFAULT 0");
		extraMap.put("java.lang.Float", " NOT NULL DEFAULT 0");
		extraMap.put("float", " NOT NULL DEFAULT 0");
		extraMap.put("java.lang.Double", " NOT NULL DEFAULT 0");
		extraMap.put("double", " NOT NULL DEFAULT 0");
		extraMap.put("boolean", " NOT NULL DEFAULT 0");
		extraMap.put("java.lang.Boolean", " NOT NULL DEFAULT 0");
		
		
		
		p2boxMap.put("boolean", "Integer");
		p2boxMap.put("int", "Integer");
		p2boxMap.put("long", "Long");
		p2boxMap.put("float", "Float");
		p2boxMap.put("double", "Double");

	}

}
