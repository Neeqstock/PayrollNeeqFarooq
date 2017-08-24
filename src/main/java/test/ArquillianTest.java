package test;

import java.io.File;

import org.apache.log4j.Logger;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.exporter.ZipExporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public class ArquillianTest {

	// LOGGER DEFINITION
	Logger log = Logger.getLogger(ArquillianTest.class);

	@Deployment(name = "PayrollNeeqFarooq_Test")
	@OverProtocol("Servlet 3.0") // To avoid timeout
	public static Archive<?> createDeployment() {

		WebArchive archive = ShrinkWrap.create(WebArchive.class, "PayrollTest.war")
				.addPackages(true, "constants")
				.addPackages(true, "model")
				.addPackages(true, "test")
				.addPackages(true, "controller")
				.addPackages(true, "dao")
				.addPackages(true, "view")
				.addAsResource("META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));

		archive.as(ZipExporter.class).exportTo(new File("target/arquillianPackage.war"), true);
		return archive;

	}

}
