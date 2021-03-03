package vitals.model;

import java.util.function.Function;

public class BatteryManagementService {
	public float temperature;
	public float soc;
	public float chargeRate;
	public boolean batteryStatus;

	public BatteryManagementService(float temperature, float soc, float chargeRate) {
		this.temperature = temperature;
		this.soc = soc;
		this.chargeRate = chargeRate;
		this.batteryStatus = true;
	}

	public BatteryManagementService validate(BatteryManagementService bms,
			Function<BatteryManagementService, BatteryManagementService> checkStatus) {
		checkStatus.apply(this);
		return bms;
	}
}
