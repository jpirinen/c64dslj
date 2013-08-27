package org.pirinen.c64dslj.iwant.wsdefdef;

import net.sf.iwant.api.IwantWorkspaceProvider;
import net.sf.iwant.api.WorkspaceDefinitionContext;
import net.sf.iwant.api.javamodules.JavaSrcModule;

public class C64dsljWorkspaceProvider implements IwantWorkspaceProvider {

	@Override
	public JavaSrcModule workspaceModule(WorkspaceDefinitionContext ctx) {
		return JavaSrcModule.with().name("c64dslj-wsdef")
				.locationUnderWsRoot("as-c64dslj-developer/i-have/wsdef")
				.mainJava("src/main/java").mainDeps(ctx.iwantApiModules())
				.end();
	}

	@Override
	public String workspaceClassname() {
		return "org.pirinen.c64dslj.iwant.wsdef.C64dsljWorkspace";
	}

}
