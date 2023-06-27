package org.jboss.seam.example.common.test;

import java.io.File;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public class DeploymentResolver {

    public static Archive<?> createDeployment() {
        String testDeployment = System.getProperty("testDeployment");
        String deploymentName = testDeployment.substring(testDeployment.lastIndexOf("/") + 1);

        Class<? extends Archive<?>> deploymentClass = deploymentName.endsWith(".war") ? WebArchive.class : EnterpriseArchive.class;

        return ShrinkWrap.create(ZipImporter.class, deploymentName)
                .importFrom(new File(testDeployment))
                .as(deploymentClass);
    }
}
