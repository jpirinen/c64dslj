package org.pirinen.c64dslj.iwant.wsdef;

import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import net.sf.iwant.api.Downloaded;
import net.sf.iwant.api.EclipseSettings;
import net.sf.iwant.api.EmmaTargetsOfJavaModules;
import net.sf.iwant.api.FromRepository;
import net.sf.iwant.api.IwantWorkspace;
import net.sf.iwant.api.SideEffectDefinitionContext;
import net.sf.iwant.api.TestedIwantDependencies;
import net.sf.iwant.api.javamodules.JavaBinModule;
import net.sf.iwant.api.javamodules.JavaSrcModule;
import net.sf.iwant.api.model.Concatenated;
import net.sf.iwant.api.model.Concatenated.ConcatenatedBuilder;
import net.sf.iwant.api.model.Path;
import net.sf.iwant.api.model.SideEffect;
import net.sf.iwant.api.model.StringFilter;
import net.sf.iwant.api.model.Target;

public class C64dsljWorkspace implements IwantWorkspace {

	@Override
	public List<? extends Target> targets() {
		return Arrays.asList(emmaCoverageReport());
	}

	@Override
	public List<? extends SideEffect> sideEffects(
			SideEffectDefinitionContext ctx) {
		return Arrays.asList(EclipseSettings
				.with()
				.name("eclipse-settings")
				.modules(ctx.wsdefdefJavaModule(), ctx.wsdefJavaModule(),
						c64dsljCore()).end());
	}

	private static Target emmaCoverageReport() {
		EmmaTargetsOfJavaModules emmaTargets = EmmaTargetsOfJavaModules.with()
				.antJars(ant(), antLauncher()).emma(emma())
				.filter(emmaFilter()).modules(allSrcModules()).end();
		return emmaTargets.emmaReport();
	}

	private static Path emmaFilter() {
		ConcatenatedBuilder filter = Concatenated.named("emma-filter");
		filter.string("-org.pirinen.c64dslj.builder.ByteHex\n");
		filter.string("-org.pirinen.c64dslj.builder.WordHex*\n");
		filter.string("-org.pirinen.c64dslj.example.*\n");
		return filter.end();
	}

	private static SortedSet<JavaSrcModule> allSrcModules() {
		return new TreeSet<JavaSrcModule>(Arrays.asList(c64dsljCore()));
	}

	private static Path ant() {
		return TestedIwantDependencies.antJar();
	}

	private static Path antLauncher() {
		return TestedIwantDependencies.antLauncherJar();
	}

	private static Path emma() {
		return TestedIwantDependencies.emma();
	}

	private static class TestClassNameFilter implements StringFilter {

		@Override
		public boolean matches(String candidate) {
			return candidate.matches(".*Test$")
					&& !candidate.matches(".*Abstract[^.]*Test$");
		}

	}

	// the modules

	private static JavaSrcModule c64dsljCore() {
		return JavaSrcModule.with().name("c64dslj-core")
				.mainJava("src/main/java").testJava("src/test/java")
				.mainDeps(guava()).testDeps(hamcrest(), junit())
				.testedBy(new TestClassNameFilter()).end();
	}

	/**
	 * TODO use FromRepository.repo1MavenOrg() when iwant provides that
	 */
	private static JavaBinModule guava() {
		return JavaBinModule.providing(Downloaded
				.withName("guava-13.0.jar")
				.url("http://repo1.maven.org/maven2/"
						+ "com/google/guava/guava/13.0/guava-13.0.jar")
				.noCheck());
	}

	private static JavaBinModule hamcrest() {
		return JavaBinModule.providing(FromRepository.ibiblio()
				.group("org/hamcrest").name("hamcrest-core").version("1.1"));
	}

	private static JavaBinModule junit() {
		return JavaBinModule.providing(FromRepository.ibiblio().group("junit")
				.name("junit").version("4.10"));
	}

}
