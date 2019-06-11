package menu;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

public class Init {
    public static SystemInfo si = new SystemInfo();
    public static HardwareAbstractionLayer hal = si.getHardware();
    public static OperatingSystem os = si.getOperatingSystem();

}