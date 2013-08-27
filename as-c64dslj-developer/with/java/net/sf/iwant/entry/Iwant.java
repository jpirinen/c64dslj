package net.sf.iwant.entry;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Writer;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.Permission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.tools.DiagnosticListener;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * $Id: Iwant.java 517 2013-04-17 12:33:47Z wipu_ $
 */
public class Iwant {

	private static final boolean DEBUG_LOG = "a".contains("b");

	private static final File HOME = new File(System.getProperty("user.home"));

	public static final File IWANT_USER_DIR = new File(HOME, ".net.sf.iwant");

	static {
		IWANT_USER_DIR.mkdir();
	}

	private final IwantNetwork network;

	protected Iwant(IwantNetwork network) {
		this.network = network;
	}

	/**
	 * TODO rename, this is not only about network
	 */
	public interface IwantNetwork {

		File cacheLocation(UnmodifiableSource<?> src);

		URL svnkitUrl();

		URL junitUrl();

		JavaCompiler systemJavaCompiler();

	}

	public static abstract class UnmodifiableSource<T> {

		private final T location;

		public UnmodifiableSource(T location) {
			this.location = location;
		}

		public String rawLocationString() {
			return location.toString();
		}

		@Override
		public final String toString() {
			return getClass().getSimpleName() + ":" + rawLocationString();
		}

		public final T location() {
			return location;
		}

		@Override
		public boolean equals(Object obj) {
			if (!getClass().equals(obj.getClass())) {
				return false;
			}
			UnmodifiableSource<?> other = (UnmodifiableSource<?>) obj;
			return location.equals(other.location());
		}

		@Override
		public int hashCode() {
			return location.hashCode();
		}

	}

	public static class UnmodifiableUrl extends UnmodifiableSource<URL> {

		public UnmodifiableUrl(URL location) {
			super(location);
		}

	}

	public static class UnmodifiableZip extends UnmodifiableSource<URL> {

		public UnmodifiableZip(URL location) {
			super(location);
		}

	}

	public static class UnmodifiableIwantBootstrapperClassesFromIwantWsRoot
			extends UnmodifiableSource<URL> {

		public UnmodifiableIwantBootstrapperClassesFromIwantWsRoot(File iwantWs) {
			super(fileToUrl(iwantWs));
		}

	}

	public static URL fileToUrl(File file) {
		try {
			URL url = file.toURI().toURL();
			String urlString = url.toExternalForm();
			url = new URL(withoutTrailingSlash(urlString));
			return url;
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private static URL withTrailingSlashIfDir(URL url) {
		try {
			// here we trust this is only used for file urls
			File asFile = new File(url.toURI());
			if (!asFile.isDirectory()) {
				return url;
			}
			String urlString = url.toExternalForm();
			if (urlString.endsWith("/")) {
				return url;
			}
			return new URL(urlString + "/");
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	private static class RealIwantNetwork implements IwantNetwork {

		@Override
		public File cacheLocation(UnmodifiableSource<?> src) {
			File cached = new File(IWANT_USER_DIR, "cached");
			File cachedFromSrc = new File(cached, src.getClass()
					.getSimpleName());
			String fileName = toSafeFilename(src.rawLocationString());
			return new File(cachedFromSrc, fileName);
		}

		@Override
		public URL svnkitUrl() {
			return url("http://www.svnkit.com/"
					+ "org.tmatesoft.svn_1.3.5.standalone.nojna.zip");
		}

		@Override
		public URL junitUrl() {
			final String v = "4.8.2";
			return url("http://mirrors.ibiblio.org/maven2" + "/junit/junit/"
					+ v + "/junit-" + v + ".jar");
		}

		@Override
		public JavaCompiler systemJavaCompiler() {
			return ToolProvider.getSystemJavaCompiler();
		}

	}

	public static URL url(String url) {
		try {
			return new URL(url);
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static Iwant using(IwantNetwork network) {
		return new Iwant(network);
	}

	public static Iwant usingRealNetwork() {
		return new Iwant(new RealIwantNetwork());
	}

	public IwantNetwork network() {
		return network;
	}

	public static void main(String[] args) throws Exception {
		try {
			usingRealNetwork().evaluate(args);
		} catch (IwantException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}

	public static class IwantException extends RuntimeException {

		public IwantException(String message) {
			super(message);
		}

	}

	public void evaluate(String... args) throws Exception {
		if (args.length <= 0) {
			throw new IwantException("Usage: " + Iwant.class.getCanonicalName()
					+ " AS_SOMEONE_DIRECTORY [args...]");
		}
		File asSomeone = new File(args[0]);
		if (!asSomeone.exists()) {
			throw new IwantException("AS_SOMEONE_DIRECTORY does not exist: "
					+ asSomeone.getCanonicalPath());
		}
		File iwantWs = iwantWsrootOfWishedVersion(asSomeone);

		File iwantBootstrapClasses = iwantBootstrapperClasses(iwantWs);

		String[] iwant2Args = new String[args.length + 1];
		iwant2Args[0] = iwantWs.getCanonicalPath();
		System.arraycopy(args, 0, iwant2Args, 1, args.length);

		runJavaMain(false, true, "net.sf.iwant.entry2.Iwant2",
				Arrays.asList(iwantBootstrapClasses), iwant2Args);
	}

	File iwantWsrootOfWishedVersion(File asSomeone) {
		try {
			Properties iwantFromProps = iwantFromProperties(asSomeone);
			URL iwantLocation = new URL(
					iwantFromProps.getProperty("iwant-from"));
			boolean reExportNotNeeded = "false".equals(iwantFromProps
					.getProperty("re-export"));
			File iwantWs = exportedFromSvn(iwantLocation, !reExportNotNeeded);
			return iwantWs;
		} catch (IwantException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static Properties iwantFromProperties(File asSomeone)
			throws IOException, FileNotFoundException {
		File iHaveConf = new File(asSomeone, "i-have/conf");
		if (!iHaveConf.exists()) {
			iHaveConf.mkdirs();
		}
		File iwantFrom = new File(iHaveConf, "iwant-from");
		if (!iwantFrom.exists()) {
			newTextFile(iwantFrom, "iwant-from=TODO\n");
			throw new IwantException("I created " + iwantFrom
					+ "\nPlease edit it and rerun me.");
		}
		Properties iwantFromProps = new Properties();
		iwantFromProps.load(new FileReader(iwantFrom));
		return iwantFromProps;
	}

	private static File tryToWriteTextFile(File file, String content)
			throws IOException {
		file.getParentFile().mkdirs();
		FileWriter writer = null;
		try {
			writer = new FileWriter(file);
			writer.append(content);
			return file;
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	public static File newTextFile(File file, String content) {
		try {
			return tryToWriteTextFile(file, content);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	private File iwantBootstrapperClasses(File iwantWs) {
		File classes = network
				.cacheLocation(new UnmodifiableIwantBootstrapperClassesFromIwantWsRoot(
						iwantWs));
		return compiledClasses(classes, iwantBootstrappingJavaSources(iwantWs),
				Collections.<File> emptyList(), true, null);
	}

	public File compiledClasses(File dest, List<File> src,
			List<File> classLocations, boolean debug, Charset encoding) {
		try {
			StringBuilder cp = new StringBuilder();
			for (Iterator<File> iterator = classLocations.iterator(); iterator
					.hasNext();) {
				File classLocation = iterator.next();
				cp.append(classLocation.getCanonicalPath());
				if (iterator.hasNext()) {
					cp.append(pathSeparator());
				}
			}
			return compiledClasses(dest, src, cp.toString(), debug, encoding);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public File compiledClasses(File dest, List<File> src, String classpath,
			boolean debug, Charset encoding) {
		try {
			debugLog("compiledClasses", "dest: " + dest, "src: " + src,
					"classpath: " + classpath, "debug:" + debug);
			del(dest);
			dest.mkdirs();
			JavaCompiler compiler = network.systemJavaCompiler();
			if (compiler == null) {
				throw new IwantException(
						"Cannot find system java compiler. Are you running a JRE instead of JDK?");
			}
			DiagnosticListener<? super JavaFileObject> diagnosticListener = null;
			Locale locale = null;
			StandardJavaFileManager fileManager = compiler
					.getStandardFileManager(diagnosticListener, locale,
							encoding);
			Iterable<? extends JavaFileObject> compilationUnits = fileManager
					.getJavaFileObjectsFromFiles(src);
			Writer compilerTaskOut = null;
			Iterable<String> classes = null;

			List<String> options = new ArrayList<String>();
			options.add("-Xlint");
			options.add("-Xlint:-serial");
			if (debug) {
				options.add("-g");
			}
			options.add("-d");
			options.add(dest.getCanonicalPath());
			options.add("-classpath");
			options.add(classpath);

			CompilationTask compilerTask = compiler.getTask(compilerTaskOut,
					fileManager, diagnosticListener, options, classes,
					compilationUnits);
			Boolean compilerTaskResult = compilerTask.call();
			fileManager.close();
			if (!compilerTaskResult) {
				throw new IwantException("Compilation failed.");
			}
			return dest;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static char pathSeparator() {
		return File.pathSeparatorChar;
	}

	public static void debugLog(String task, Object... lines) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < lines.length; i++) {
			b.append(String.format("(%16s    ", task));
			b.append(lines[i]);
			b.append(")");
			if (i < lines.length - 1) {
				b.append("\n");
			}
		}
		fileLog(b.toString());
		if (DEBUG_LOG) {
			System.err.println(b);
		}
	}

	public static void log(String task, File target) {
		StringBuilder b = new StringBuilder();
		b.append(String.format(":%16s -> ", task));
		b.append(target.getName());
		System.err.println(b);
		fileLog(b.toString());
	}

	private static List<File> iwantBootstrappingJavaSources(File iwantWs) {
		File iwant2 = new File(iwantWs,
				"iwant-distillery/src/main/java/net/sf/iwant/entry2/Iwant2.java");
		File iwant = new File(iwantWs,
				"iwant-distillery/as-some-developer/with/java/net/sf/iwant/entry/Iwant.java");
		return Arrays.asList(iwant2, iwant);
	}

	private static class StreamsAndSecurityManager {

		private final PrintStream out;
		private final PrintStream err;
		private final SecurityManager securityManager;

		StreamsAndSecurityManager(PrintStream out, PrintStream err,
				SecurityManager securityManager) {
			this.out = out;
			this.err = err;
			this.securityManager = securityManager;
		}

	}

	private static StreamsAndSecurityManager originalStreamsAndSecurityManager;
	private static int catchStreamsAndSystemExitsRequestCount = 0;

	private static synchronized void catchStreamsAndSystemExits() {
		catchStreamsAndSystemExitsRequestCount++;
		if (originalStreamsAndSecurityManager != null) {
			// already handled
			return;
		}
		originalStreamsAndSecurityManager = new StreamsAndSecurityManager(
				System.out, System.err, System.getSecurityManager());
		System.setOut(System.err);
		System.setSecurityManager(new ExitCatcher());
	}

	private static synchronized void restoreOriginalStreamsAndSecurityManager() {
		catchStreamsAndSystemExitsRequestCount--;
		if (originalStreamsAndSecurityManager != null
				&& catchStreamsAndSystemExitsRequestCount <= 0) {
			System.setOut(originalStreamsAndSecurityManager.out);
			System.setErr(originalStreamsAndSecurityManager.err);
			System.setSecurityManager(originalStreamsAndSecurityManager.securityManager);
			originalStreamsAndSecurityManager = null;
		}
	}

	public static void runJavaMain(boolean catchPrintsAndSystemExit,
			boolean hideIwantClasses, String className,
			List<File> classLocations, String... args) throws Exception {
		debugLog("runJavaMain", "class: " + className,
				"args: " + Arrays.toString(args), "catchPrintsAndSystemExit="
						+ catchPrintsAndSystemExit, "hideIwantClasses="
						+ hideIwantClasses, "classLocations: " + classLocations);
		ClassLoader classLoader = classLoader(hideIwantClasses, classLocations);
		Class<?> helloClass = classLoader.loadClass(className);
		Method mainMethod = helloClass.getMethod("main", String[].class);

		Object[] invocationArgs = { args };

		if (catchPrintsAndSystemExit) {
			catchStreamsAndSystemExits();
		}
		try {
			mainMethod.invoke(null, invocationArgs);
		} catch (ExitCalledException e) {
			if (e.status() != 0) {
				throw new IwantException(className + " exited with "
						+ e.status());
			}
		} finally {
			if (catchPrintsAndSystemExit) {
				restoreOriginalStreamsAndSecurityManager();
			}
		}
	}

	public static class ExitCalledException extends SecurityException {

		private final int status;

		public ExitCalledException(int status) {
			this.status = status;
		}

		public int status() {
			return status;
		}

	}

	private static class ExitCatcher extends SecurityManager {

		@Override
		public void checkPermission(Permission perm) {
			// everything allowed
		}

		@Override
		public void checkExit(int status) {
			throw new ExitCalledException(status);
		}

	}

	/**
	 * This forces the second phase of bootstrapping to use its own versions of
	 * any iwant classes so we are free to make changes to this very file
	 * without breaking things.
	 */
	private static class ClassLoaderThatHidesIwant extends ClassLoader {

		@Override
		protected synchronized Class<?> loadClass(String name, boolean resolve)
				throws ClassNotFoundException {
			if (isClassnameToHide(name)) {
				return null;
			} else {
				return super.loadClass(name, resolve);
			}
		}

		private static boolean isClassnameToHide(String name) {
			if (name.startsWith("net.sf.iwant")) {
				return isIwantClassnameToHide(name);
			}
			return false;
		}

		private static boolean isIwantClassnameToHide(String name) {
			// canonical name of inner classes is not compatible with
			// classloading (!!) so this manual name tweaking is needed:
			if ((Iwant.class.getCanonicalName() + "$" + ExitCalledException.class
					.getSimpleName()).equals(name)) {
				// this is an exceptional case, for catches to work
				return false;
			}
			return true;
		}

	}

	public static ClassLoader classLoader(boolean hideIwantClasses,
			List<File> locations) {
		ClassLoader parent = hideIwantClasses ? new ClassLoaderThatHidesIwant()
				: null;
		return classLoader(parent, locations);
	}

	public static ClassLoader classLoader(ClassLoader parent,
			List<File> locations) {
		URL[] urls = new URL[locations.size()];
		for (int i = 0; i < locations.size(); i++) {
			File location = locations.get(i);
			URL asUrl = fileToUrl(location);
			// TODO own type so we don't need to slash back and forth
			asUrl = withTrailingSlashIfDir(asUrl);
			urls[i] = asUrl;
		}
		if (parent != null) {
			return new URLClassLoader(urls, parent);
		} else {
			return new URLClassLoader(urls);
		}
	}

	public static void fileLog(String msg) {
		try {

			new FileWriter(new File(IWANT_USER_DIR, "log"), true)
					.append(new Date().toString()).append(" - ").append(msg)
					.append("\n").close();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	private static final String encSlash = urlEncode("/");
	private static final String encQuestion = urlEncode("?");

	public static String toSafeFilename(String from) {
		String to = urlEncode(from);
		to = to.replaceAll(encSlash, "/");
		// starting slash
		to = to.replaceAll("^/", encSlash);
		// parent dir refs
		to = to.replaceAll("/\\.\\.", encSlash + "..");
		to = to.replaceAll("\\.\\./", ".." + encSlash);
		// repeating slashes
		to = to.replaceAll("//", "/" + encSlash);
		// url query
		to = to.replaceAll(encQuestion, "?");
		return to;
	}

	private static String urlEncode(String s) {
		try {
			return URLEncoder.encode(s, "UTF-8");
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static void del(File file) {
		if (file.isDirectory()) {
			for (File child : file.listFiles()) {
				del(child);
			}
		}
		file.delete();
	}

	public void downloaded(URL from, File to) {
		try {
			if (to.exists()) {
				return;
			}
			to.getParentFile().mkdirs();
			debugLog("downloaded", "from " + from);
			log("downloaded", to);
			byte[] bytes = downloadBytes(from);
			FileOutputStream cachedOut = new FileOutputStream(to);
			cachedOut.write(bytes);
			cachedOut.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public File downloaded(URL from) {
		File to = network.cacheLocation(new UnmodifiableUrl(from));
		downloaded(from, to);
		return to;
	}

	private static byte[] downloadBytes(URL url) throws MalformedURLException,
			IOException {
		enableHttpProxy();
		InputStream in = url.openStream();
		byte[] respBody = readBytes(in);
		in.close();
		return respBody;
	}

	private static void enableHttpProxy() {
		unixHttpProxyToJavaHttpProxy(System.getenv("http_proxy"),
				System.getenv("https_proxy"));
	}

	static void unixHttpProxyToJavaHttpProxy(String httpProxy, String httpsProxy) {
		proxyUrlToJava(httpProxy, "http");
		proxyUrlToJava(httpsProxy, "https");
	}

	private static void proxyUrlToJava(String urlString, String prefix) {
		if (urlString == null || "".equals(urlString)) {
			return;
		}
		URL url = url(urlString);
		String host = url.getHost();
		int port = url.getPort();
		System.setProperty(prefix + ".proxyHost", host);
		if (port >= 0) {
			System.setProperty(prefix + ".proxyPort", Integer.toString(port));
		}
	}

	private static byte[] readBytes(InputStream in) throws IOException {
		ByteArrayOutputStream body = new ByteArrayOutputStream();
		while (true) {
			int i = in.read();
			if (i < 0) {
				break;
			}
			byte b = (byte) i;
			body.write(b);
		}
		return body.toByteArray();
	}

	public File unmodifiableZipUnzipped(UnmodifiableZip src) {
		try {
			File dest = network.cacheLocation(src);
			if (dest.exists()) {
				return dest;
			}
			log("unzipped", dest);
			dest.mkdirs();
			ZipInputStream zip = new ZipInputStream(src.location().openStream());
			ZipEntry e = null;
			byte[] buffer = new byte[32 * 1024];
			while ((e = zip.getNextEntry()) != null) {
				File entryFile = new File(dest, e.getName());
				if (e.isDirectory()) {
					entryFile.mkdirs();
					continue;
				}
				OutputStream out = new FileOutputStream(entryFile);
				while (true) {
					int bytesRead = zip.read(buffer);
					if (bytesRead <= 0) {
						break;
					}
					out.write(buffer, 0, bytesRead);
				}
				out.close();
			}
			zip.close();
			return dest;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public File unzippedSvnkit() {
		try {
			URL url = network.svnkitUrl();
			File cached = downloaded(url);
			File unzipped = unmodifiableZipUnzipped(new UnmodifiableZip(
					fileToUrl(cached)));
			return unzipped;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public URL svnkitUrl() {
		return network.svnkitUrl();
	}

	public File exportedFromSvn(URL url, boolean reExportIfFile) {
		try {
			File exported = network.cacheLocation(new UnmodifiableUrl(url));
			if (exported.exists()) {
				if (isFile(url)) {
					if (!reExportIfFile) {
						debugLog("svn-exported",
								"re-export disabled, skipping even though"
										+ " remote is a file.");
						return exported;
					}
					debugLog("svn-exported", "re-export needed,"
							+ " remote is a file.");
					del(exported);
				} else {
					return exported;
				}
			}
			debugLog("svn-exported", url);
			log("svn-exported", exported);
			String urlString = url.toExternalForm();
			if (isFile(url)) {
				urlString = url.getFile();
			}
			File svnkit = unzippedSvnkit();
			File svnkitJar = new File(svnkit, "svnkit-1.3.5.7406/svnkit.jar");
			File svnkitCliJar = new File(svnkit,
					"svnkit-1.3.5.7406/svnkit-cli.jar");
			enableHttpProxy();
			runJavaMain(true, false, "org.tmatesoft.svn.cli.SVN",
					Arrays.asList(svnkitJar, svnkitCliJar), "export",
					urlString, exported.getCanonicalPath());
			return exported;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static boolean isFile(URL url) {
		return "file".equals(url.getProtocol());
	}

	public static String withoutTrailingSlash(String string) {
		if (!string.endsWith("/")) {
			return string;
		}
		return string.substring(0, string.length() - 1);
	}
}
