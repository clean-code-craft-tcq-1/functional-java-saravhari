package vitals;

import vitals.model.BatteryManagementService;
import vitals.validator.BMSValidator;

public class ElectricVehicleBMS {
	static boolean batteryIsOk(float temperature, float soc, float chargeRate) {
		BatteryManagementService bms = new BatteryManagementService(temperature, soc, chargeRate);
		bms.validate(bms, BMSValidator::isValidTemperature).validate(bms, BMSValidator::isValidStateOfCharge)
				.validate(bms, BMSValidator::isValidChargeRate);
		return bms.batteryStatus;
	}

	public static void main(String[] args) {
		assert (batteryIsOk(25, 70, 0.7f) == true);
		assert (batteryIsOk(25, 15, 0.0f) == false);
		assert (batteryIsOk(25, 85, 0.0f) == false);
		assert (batteryIsOk(25, 70, 0.9f) == false);
		assert (batteryIsOk(50, 21, 0.0f) == false);
		assert (batteryIsOk(-50, 21, 0.0f) == false);
		assert (batteryIsOk(40, 79, 11.0f) == false);
		assert (batteryIsOk(-50, 79, 0.1f) == false);
		assert (batteryIsOk(50, 79, 0.1f) == false);
		assert (batteryIsOk(50, 12, 0.1f) == false);
		assert (batteryIsOk(50, 85, 0.1f) == false);
		assert (batteryIsOk(0, 0, 0f) == false);
	}
}
