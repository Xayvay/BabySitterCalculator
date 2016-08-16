import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 
 */

/**
 * @author Xavier S
 * 
 *         Background ---------- This kata simulates a babysitter working and
 *         getting paid for one night. The rules are pretty straight forward:
 * 
 *         The babysitter - starts no earlier than 5:00PM - leaves no later than
 *         4:00AM - gets paid $12/hour from start-time to bedtime - gets paid
 *         $8/hour from bedtime to midnight - gets paid $16/hour from midnight
 *         to end of job - gets paid for full hours (no fractional hours)
 * 
 * 
 *         Feature: As a babysitter In order to get paid for 1 night of work I
 *         want to calculate my nightly charge
 */
public class HoursVsPayCalc {

	/**
	 * Method that calculates the amount paid
	 * 
	 * @param start
	 *            Represents the time that you started to work
	 * @param end
	 *            Represents the time that you stopped working.
	 * @return
	 */
	public double amountPaid(String start, String bedTime, String end) {
		double addedHourlyPay = 0.0;
		double startMinutes = 0.0;
		int startHours = 0;
		double endMinutes = 0.0;
		int endHours = 0;
		int bedHours = 0;
		String startMeridiem = "";
		String endMeridiem = "";
		String bedTimeMeridiem = "";
		// Setting when you start your shift,end your shift and when bedtime is.
		// A.M. or P.M.
		startMeridiem = getMeridiem(start);
		endMeridiem = getMeridiem(end);
		bedTimeMeridiem = getMeridiem(bedTime);
		// Separating the starting shift string and converting it into
		// integer/double values for minutes and hours
		if (start.substring(0, 2).contains(":")) {
			startHours = Integer.parseInt(start.substring(0, 1));
			startMinutes = (Double.parseDouble(start.substring(2, 4)));

		} else {
			startHours = Integer.parseInt(start.substring(0, 2));
			startMinutes = Double.parseDouble(start.substring(3, 5));

		}
		// Separating the bed time shift string and converting it into
		// integer/double values for hours
		if (bedTime.substring(0, 2).contains(":")) {
			bedHours = Integer.parseInt(bedTime.substring(0, 1));
		} else {
			bedHours = Integer.parseInt(bedTime.substring(0, 2));
		}
		// Separating the end of shift string and converting it into
		// integer/double values for minutes and hours
		if (end.substring(0, 2).contains(":")) {
			endHours = Integer.parseInt(end.substring(0, 1));
			endMinutes = (Double.parseDouble(end.substring(2, 4)));
		} else {
			endHours = Integer.parseInt(end.substring(0, 2));
			endMinutes = Double.parseDouble(end.substring(3, 5));
		}

		if (startMinutes > 0.0) {
			startHours++;
		}

		// Adding the hours that you worked by calling the return amount method
		// and adding it to your return integer addedHourlypay

		addedHourlyPay = returnAmountHours(startHours, endHours, bedHours,
				bedTimeMeridiem, startMeridiem, endMeridiem);
		addedHourlyPay = addedHourlyPay
				+ returnAmountMinutes(startHours, startMinutes, bedHours,
						"start", startMeridiem)
				+ returnAmountMinutes(endHours, endMinutes, bedHours, "end",
						endMeridiem);
		// Converting it into a Double by using BigDecimal and rounding it to
		// the 10ths place
		BigDecimal bd = new BigDecimal(addedHourlyPay);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	/**
	 * The following gets if your when in the day you star your shift
	 * 
	 * @param time
	 *            The provided time you work or end your shift
	 * @return
	 */
	private String getMeridiem(String time) {
		String meridiem = "";
		if (time.contains("a.m.") || time.contains("A.M.")
				|| time.contains("am") || time.contains("AM")) {
			meridiem = "am";
		} else {
			meridiem = "pm";
		}
		return meridiem;
	}

	/**
	 * This method Calculates the hours that you have worked
	 * 
	 * @param startValue
	 *            Starting time of work
	 * @param endValue
	 *            Time the shift ended
	 * @param bedTime
	 *            Bed Time given
	 * @param bedTimeMeridiem
	 *            if bed time is in the a.m. or p.m.
	 * @param startMeridiem
	 *            if your start time was in the a.m. or p.m.
	 * @param endMeridiem
	 *            if your shift ends in the a.m. or p.m.
	 * @return
	 */
	private long returnAmountHours(int startValue, int endValue, int bedTime,
			String bedTimeMeridiem, String startMeridiem, String endMeridiem) {
		long valueAdder = 0;
		int copyValue = startValue;

		// setting bed Time for if after mid night
		if (bedTimeMeridiem == "am") {
			bedTime = 11;
		}
		// An if statement for if you start your shift in the p.m and end it after mid night
		// Else it takes the values for if you start and end your shift in the a.m. and the p.m.
		if (startMeridiem == "pm" && endMeridiem == "am") {
			while (copyValue <= 11) {
				if (copyValue < bedTime) {
					valueAdder = valueAdder + 12;
				} else if (copyValue >= bedTime && copyValue <= 11) {
					valueAdder = valueAdder + 8;
				}
				copyValue++;
			}

			if (endValue != 12) {
				copyValue = 0;
				while (copyValue < endValue) {

					valueAdder = valueAdder + 16;
					copyValue++;
				}
			}
		} else if (startMeridiem == "am") {
			if (copyValue == 12) {
				copyValue = 0;
			}
			while (copyValue < endValue) {

				valueAdder = valueAdder + 16;

				copyValue++;
			}
		} else {

			while (copyValue < endValue) {
				if (copyValue < bedTime) {
					valueAdder = valueAdder + 12;
				} else if (copyValue >= bedTime && copyValue < endValue) {
					valueAdder = valueAdder + 8;
				}
				copyValue++;
			}
		}

		return valueAdder;
	}

	/**
	 * Method for calculating the minutes within the hour that you started or
	 * the final minutes after ending your shift
	 * 
	 * @param hour
	 *            The hour that pay minutes will be charged towards
	 * @param minutes
	 *            The minutes within that hour that are being charged
	 * @param bedTime
	 *            The given bed time
	 * @param position
	 *            If These are starting hour minutes or ending our minutes
	 * @param merid
	 *            If this is in the p.m. or after midnight
	 * @return Returning the final minutes worked in a double
	 */
	private double returnAmountMinutes(int hour, double minutes, int bedTime,
			String position, String merid) {
		double valueAdder = 0.0;

		if (position == "start" && minutes > 0) {
			minutes = 60 - minutes;
		}

		if (merid == "pm") {

			if (hour < bedTime) {
				valueAdder = (12.0 / 60) * minutes;
			} else if (hour >= bedTime && hour < 12) {
				valueAdder = (8.0 / 60) * minutes;
			}

		}

		if (merid == "am") {
			valueAdder = (16.00 / 60) * minutes;
		}

		return valueAdder;

	}

	/**
	 * Main method With a call to Calculating payments
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		HoursVsPayCalc CalculatePay = new HoursVsPayCalc();
		// Uncomment and use for calculating start time bed time and end shift
		// CalculatePay.amountPaid(start, bedTime, end)

	}

}
