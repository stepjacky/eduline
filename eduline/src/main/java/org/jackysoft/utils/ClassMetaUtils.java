package org.jackysoft.utils;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.util.ClassUtils;

import com.google.common.base.Strings;

public class ClassMetaUtils {

	/**
	 * Find all classes in the given package that has the specified annotation.
	 * 
	 * @param annotationType
	 *            The annotation of the classes to find.
	 * @param scanPackageName
	 *            The name of the package to scan.
	 * @param includeSubpackages
	 *            Boolean indicating if sub-packages must be scanned too.
	 * @return A list containing all classes in the given package that has the
	 *         specified annotation.
	 * @throws Exception
	 *             If annotations cannot be scanned.
	 */
	public static Set<Class<?>> findAnnotatedClasses(
			final Class<? extends Annotation> annotationType,
			final String scanPackageName, final boolean includeSubpackages)
			throws Exception {
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		String scanPattern = buildScanPattern(scanPackageName,
				includeSubpackages);
		Resource[] resources = resolver.getResources(scanPattern);
		MetadataReaderFactory readerFactory = new SimpleMetadataReaderFactory();
		Set<Class<?>> classes = new HashSet<Class<?>>();
		for (Resource resource : resources) {
			if (resource.isReadable()) {
				MetadataReader reader = readerFactory
						.getMetadataReader(resource);
				AnnotationMetadata metadata = reader.getAnnotationMetadata();
				if (metadata.hasAnnotation(annotationType.getName())) {
					Class<? extends Object> clazz = ClassUtils.forName(metadata
							.getClassName(), Thread.currentThread()
							.getContextClassLoader());
					classes.add(clazz);
				}
			}
		}
		return classes;
	}

	/**
	 * Builds the pattern used to scan packages.
	 * 
	 * @param scanPackageName
	 *            The name of the package to scan.
	 * @param includeSubpackages
	 *            Boolean indicating if sub-packages must be scanned too.
	 * @return The pattern used to scan packages.
	 */
	private static String buildScanPattern(final String scanPackageName,
			final boolean includeSubpackages) {
		StringBuffer scanPattern = new StringBuffer();
		scanPattern.append(ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX);
		scanPattern.append(scanPackageName.replace('.', File.separatorChar));
		scanPattern.append("/");
		if (includeSubpackages) {
			scanPattern.append("**/");
		}
		scanPattern.append("*.class");
		return scanPattern.toString();
	}
	
	
	public static List<String> getClassListByRecursion(String packageName,String postFix, boolean isRecursive) {
        List<String> classList = new ArrayList<>();
        try {
            Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(packageName.replaceAll("\\.", "/"));
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    String protocol = url.getProtocol();
                    if (protocol.equals("file")) {
                        String packagePath = url.getPath();
                        addClass(classList, packagePath, packageName,postFix, isRecursive);
                    } else if (protocol.equals("jar")) {
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        JarFile jarFile = jarURLConnection.getJarFile();
                        Enumeration<JarEntry> jarEntries = jarFile.entries();
                        while (jarEntries.hasMoreElements()) {
                            JarEntry jarEntry = jarEntries.nextElement();
                            String jarEntryName = jarEntry.getName();
                            if (jarEntryName.endsWith(postFix)) {
                                String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
                                if (isRecursive || className.substring(0, className.lastIndexOf(".")).equals(packageName)) {
                                    classList.add((className));
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classList;
    }
	
	private static void addClass(List<String> classList, String packagePath, String packageName,String postFix, boolean isRecursive) {
        try {
            File[] files = getClassFiles(packagePath,postFix);
            if (files != null) {
                for (File file : files) {
                    String fileName = file.getName();
                    if (file.isFile()) {
                        //String className = getClassName(packageName, fileName);
                        classList.add(file.getAbsolutePath());
                    } else {
                        if (isRecursive) {
                            String subPackagePath = getSubPackagePath(packagePath, fileName);
                            String subPackageName = getSubPackageName(packageName, fileName);
                            addClass(classList, subPackagePath, subPackageName, postFix,isRecursive);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	private static File[] getClassFiles(String packagePath,String postFix) {
        return new File(packagePath).listFiles(fn->
        		fn.isFile()&&fn.getName().endsWith(postFix)||fn.isDirectory()
        		);
    }
	private static String getClassName(String packageName, String fileName) {
        String className = fileName.substring(0, fileName.lastIndexOf("."));
        if (!Strings.isNullOrEmpty(packageName)) {
            className = packageName + "." + className;
        }
        return className;
    }
	private static String getSubPackagePath(String packagePath, String filePath) {
        String subPackagePath = filePath;
        if (!Strings.isNullOrEmpty(packagePath)) {
            subPackagePath = packagePath + "/" + subPackagePath;
        }
        return subPackagePath;
    }

    private static String getSubPackageName(String packageName, String filePath) {
        String subPackageName = filePath;
        if (!Strings.isNullOrEmpty(packageName)) {
            subPackageName = packageName + "." + subPackageName;
        }
        return subPackageName;
    }

    private static void addClassByAnnotation(List<String> classList, String packagePath, String packageName,String postFix, Class<? extends Annotation> annotationClass) {
        try {
            File[] files = getClassFiles(packagePath,postFix);
            if (files != null) {
                for (File file : files) {
                    String fileName = file.getName();
                    if (file.isFile()) {
                        String className = getClassName(packageName, fileName);
                        Class<?> cls = Class.forName(className);
                        if (cls.isAnnotationPresent(annotationClass)) {
                            classList.add(className);
                        }
                    } else {
                        String subPackagePath = getSubPackagePath(packagePath, fileName);
                        String subPackageName = getSubPackageName(packageName, fileName);
                        addClassByAnnotation(classList, subPackagePath, subPackageName, postFix, annotationClass);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static List<Field> getFieldsRecusive(Class<?> cls){
		List<Field> fds = new ArrayList<>();
		if(cls==null) return fds;
		if(cls.getSuperclass()!=null) {
			fds.addAll(getFieldsRecusive(cls.getSuperclass()));
		}
		Field[] fa = cls.getDeclaredFields();
		for(Field f:fa) {
			if(f!=null)
			fds.add(f);
		}
		return fds;
	}
	
	public static Field[] getFields(Class<?> cls) {
		List<Field> fds = getFieldsRecusive(cls);
		Field[] fs = new Field[fds.size()];
		return fds.toArray(fs);
	}
	
}

