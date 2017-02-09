package util;

/**
 * Utility class to check which operating system the application running on. Possible uses are for scripting purposes.
 * 
 * @author Sonny.Saniev
 * 
 */
import java.util.Locale;

import org.springframework.stereotype.Component;

@Component
public class OsValidator {

	/**
	 * types of Operating Systems
	 */
	public enum OperatingSystemType {
		WINDOWS, MAC_OS, LINUX, OTHER
	};

	/**
	 * detect the operating system from the os.name System property and cache
	 * the result
	 * 
	 * @returns - the operating system detected
	 */
	public OperatingSystemType getOperatingSystemType() {
		OperatingSystemType osType = null;
		if (osType == null) {
			String os = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
			if ((os.indexOf("mac") >= 0) || (os.indexOf("darwin") >= 0)) {
				osType = OperatingSystemType.MAC_OS;
			} else if (os.indexOf("win") >= 0) {
				osType = OperatingSystemType.WINDOWS;
			} else if (os.indexOf("nux") >= 0) {
				osType = OperatingSystemType.LINUX;
			} else {
				osType = OperatingSystemType.OTHER;
			}
		}

		return osType;
	}
	
	/**
	 * Checks if OS the app is running on is ms-windows
	 * 
	 * @return boolean
	 */
	public boolean isWindows(){
		 return OperatingSystemType.WINDOWS.equals(getOperatingSystemType());
	}

	/**
	 * Checks if OS the app is running on is linux
	 * 
	 * @return boolean
	 */
	public boolean isLinux(){
		return OperatingSystemType.LINUX.equals(getOperatingSystemType());
	}
	
	/**
	 * Checks if OS the app is running on is a mac
	 * 
	 * @return boolean
	 */
	public boolean isMac(){
		return OperatingSystemType.MAC_OS.equals(getOperatingSystemType());
	}
	
	/**
	 * Checks if OS the app is running on is unknown
	 * 
	 * @return boolean
	 */
	public boolean isOther(){
		return OperatingSystemType.OTHER.equals(getOperatingSystemType());
	}
}
