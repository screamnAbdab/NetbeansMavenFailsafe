package org.bneuman.nb.modules.maven.failsafe;

import java.io.InputStream;
import java.util.Set;
import javax.swing.Action;
import org.netbeans.api.annotations.common.StaticResource;
import org.netbeans.api.project.Project;
import org.netbeans.modules.maven.api.NbMavenProject;
import org.netbeans.modules.maven.spi.actions.AbstractMavenActionsProvider;
import org.netbeans.modules.maven.spi.actions.MavenActionsProvider;
import org.netbeans.spi.project.ProjectServiceProvider;
import org.netbeans.spi.project.ui.support.ProjectSensitiveActions;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.awt.DynamicMenuContent;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;

@ProjectServiceProvider(
        service
        = MavenActionsProvider.class,
        projectType = {
            "org-netbeans-modules-maven/" + NbMavenProject.TYPE_JAR,
            "org-netbeans-modules-maven/" + NbMavenProject.TYPE_WAR,
            "org-netbeans-modules-maven/" + NbMavenProject.TYPE_EAR,
            "org-netbeans-modules-maven/" + NbMavenProject.TYPE_EJB
        })
public class FailsafeActionsProvider extends AbstractMavenActionsProvider {

    static final String VERIFY = "verify";
    @StaticResource private static final String MAPPINGS = "org/bneuman/nb/modules/maven/failsafe/actionMappings.xml";

    
    @ActionID(id = "org.bneuman.nb.modules.failsafe.Verify", category = "Project")
    @ActionRegistration(displayName = "#ACT_Verify", lazy=false)
    @ActionReference(position = 1250, path = "Projects/org-netbeans-modules-maven/Actions")
    @Messages("ACT_Verify=Run Integration Tests")
    public static Action createReloadAction() {
        Action a = ProjectSensitiveActions.projectCommandAction(VERIFY, "verify", null);
        a.putValue(DynamicMenuContent.HIDE_WHEN_DISABLED, true);
        return a;
    }

    
    @Override
    public Set<String> getSupportedDefaultActions() {
        Set<String> actions = super.getSupportedDefaultActions();
        actions.add("verify");
        return actions;
    }    
    
    @Override
    protected InputStream getActionDefinitionStream() {
        return FailsafeActionsProvider.class.getClassLoader().getResourceAsStream(MAPPINGS);
    }
    
    @Override
    public boolean isActionEnable(String action, Project project, Lookup lookup) {
        return true;
    }
}
