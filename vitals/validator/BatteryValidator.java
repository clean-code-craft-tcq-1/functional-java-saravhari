package vitals.validator;

import java.util.function.Function;

import vitals.constant.BatteryFactor;
import vitals.model.BatteryManagementService;

public class BatteryValidator {

	public static boolean batteryIsOk(float temperature, float soc, float chargeRate) {
		BatteryManagementService bms = new BatteryManagementService(temperature, soc, chargeRate);
		if (isValidTemperature(bms))
			return true;
		return false;
	}

	public static Function<BatteryManagementService, Boolean> isValidChargeRate = (BatteryManagementService bms) -> {
		return checkChargeRate(BatteryFactor.MAX_CHANGE_RATE, bms.chargeRate, BatteryFactor.CHARGE_RATE);
	};

	public static Function<BatteryManagementService, Boolean> isValidStateOfCharge = (BatteryManagementService bms) -> {
		if (checkRange(BatteryFactor.MIN_SOC, BatteryFactor.MAX_SOC, bms.soc, BatteryFactor.SOC)) {
			return isValidChargeRate.apply(bms);
		}
		return false;
	};

	public static Boolean isValidTemperature(BatteryManagementService bms) {
		if (checkRange(BatteryFactor.MIN_TEMPERATURE, BatteryFactor.MAX_TEMPERATURE, bms.temperature, BatteryFactor.TEMPERATURE)) {
			return isValidStateOfCharge.apply(bms);
		}
		return false;
	};

	public static Boolean checkRange(float minVal, float maxVal, float value, String factorName) {
		if (value < minVal || value > maxVal) {
			return false;
		}
		return true;
	}

	static Boolean checkChargeRate(float maxVal, float value, String factorName) {
		if (value > maxVal) {
			return false;
		}
		return true;
	}

	static void printStatus(String factorName, boolean isHigh) {
		System.out.println(factorName + " is out of range! and it is " + (isHigh ? "high!" : "low!"));
	}
}
