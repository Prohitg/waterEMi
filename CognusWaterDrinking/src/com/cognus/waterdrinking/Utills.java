package com.cognus.waterdrinking;

import java.text.NumberFormat;

public class Utills {

	public static final String MyPREFERENCES = "MyPREFERENCES";

	public static final String KEYWEIGHTID = "weight_id";

	public static final int KGID = 108;

	public static final int LBSID = 1008;

	public static final String CAPWEIGHTID = "capacity_id";

	public static final int MLID = 10008;
	public static final int OZID = 1008;

	public static final String KEYWEIGHTVAL = "KEYWEIGHTVAL";
	public static final String KEYGENDERVAL = "KEYGENDERVAL";
	public static final String KEYISPREGNET = "KEYISPREGNET";
	public static final String KEYISBREASTFEEDING = "KEYISBREASTFEEDING";
	public static final String KEYLIFESTYLEVAL = "KEYLIFESTYLE";
	public static final String KEYWEATHERVAL = "KEYWEATHER";

	public static final String KEYNEEDEDML = "neededml";

	public static NumberFormat numFormat = NumberFormat.getInstance();
	static {
		numFormat.setMinimumFractionDigits(0);
		numFormat.setMaximumFractionDigits(2);
	}

	/**
	 * 
	 * @param feet
	 * @return cm
	 */
	public static Double feettoCm(Double feet) {

		return 30.48 * feet;
	}

	public static Double lbstoKg(Double lbs) {

		return 0.453592 * lbs;
	}

	public static Double kgtoLbs(Double kg) {
		return kg / 0.453592;
	}

	/**
	 * 
	 * @param ozval
	 * @return default val is 29.5735 but here we use 29.570
	 */
	public static double oztoMl(double ozval) {
		return 29.5735 * ozval;
	}

	/**
	 * 
	 * @param mlval
	 * @return
	 */
	public static double mltoOz(double mlval) {
		return 0.033814 * mlval;
	}

}
