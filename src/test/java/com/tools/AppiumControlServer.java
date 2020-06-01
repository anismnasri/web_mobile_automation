/**
 * 
 * @author Anis MNASRI
 */
package com.tools;

import java.io.IOException;
import java.net.ServerSocket;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumControlServer {


	private AppiumServiceBuilder asbuilder;
	private AppiumDriverLocalService service;
	private DesiredCapabilities capabilities;
	/**
	 * check if appium server is running in port port
	 * 
	 * @param port to run appium server
	 * @return
	 */
	public boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

	public void startServer() throws InterruptedException {
		//Set Capabilities
		capabilities = new DesiredCapabilities();
		capabilities.setCapability("noReset", "false");
		
		//Build the Appium service
		asbuilder = new AppiumServiceBuilder();
		asbuilder.withIPAddress("127.0.0.1");
		asbuilder.usingPort(4725);
		asbuilder.withCapabilities(capabilities);
		asbuilder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		asbuilder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");
		
		//Start the server with the builder
		service = AppiumDriverLocalService.buildService(asbuilder);
		service.start();
		Thread.sleep(3000);
		// can add here time sleep for 2 sec max if is needed
		
	}

}
