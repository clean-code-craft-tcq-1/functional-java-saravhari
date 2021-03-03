package vitals.validator;

import vitals.constant.BMSFactor;
import vitals.model.BatteryManagementService;

public class BMSValidator {
	public static BatteryManagementService isValidTemperature(BatteryManagementService bms) {
		bms.batteryStatus = bms.batteryStatus && checkRange(BMSFactor.MIN_TEMPERATURE, BMSFactor.MAX_TEMPERATURE,
				bms.temperature, BMSFactor.TEMPERATURE);
		return bms;
	}

	public static BatteryManagementService isValidStateOfCharge(BatteryManagementService bms) {
		bms.batteryStatus = bms.batteryStatus
				&& checkRange(BMSFactor.MIN_SOC, BMSFactor.MAX_SOC, bms.soc, BMSFactor.SOC);
		return bms;
	}

	public static BatteryManagementService isValidChargeRate(BatteryManagementService bms) {
		bms.batteryStatus = bms.batteryStatus
				&& checkChargeRate(BMSFactor.MAX_CHANGE_RATE, bms.chargeRate, BMSFactor.CHARGE_RATE);
		return bms;
	}

	public static boolean checkRange(float minVal, float maxVal, float value, String factorName) {
		if (value < minVal || value > maxVal) {
			printStatus(factorName, (value > maxVal));
			return false;
		}

		return true;
	}

	static boolean checkChargeRate(float maxVal, float value, String factorName) {
		if (value > maxVal) {
			printStatus(factorName, (value > maxVal));
			return false;
		}

		return true;
	}

	static void printStatus(String factorName, boolean isHigh) {
		System.out.println(factorName + " is out of range! and it is " + (isHigh ? "high!" : "low!"));
	}
}
