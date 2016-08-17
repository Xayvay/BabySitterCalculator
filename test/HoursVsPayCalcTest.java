import static org.junit.Assert.*;

import org.junit.Test;

public class HoursVsPayCalcTest {

	@Test
	public void whenYouWorkTheEntireShiftWithABedTimeAtFivePM() {
		HoursVsPayCalc calcAmount = new HoursVsPayCalc();
		assertEquals(120.0,
				calcAmount.amountPaid("5:00pm", "5:00pm", "4:00am"), 0);
	}

	@Test
	public void whenYouWorkTheEntireShiftWithABedTimeAtSixPM() {
		HoursVsPayCalc calcAmount = new HoursVsPayCalc();
		assertEquals(124.0,
				calcAmount.amountPaid("5:00pm", "6:00pm", "4:00am"), 0);
	}

	@Test
	public void whenYouWorkTheEntireShiftWithABedTimeAtSevenPM() {
		HoursVsPayCalc calcAmount = new HoursVsPayCalc();
		assertEquals(128.0,
				calcAmount.amountPaid("5:00pm", "7:00pm", "4:00am"), 0);
	}

	@Test
	public void whenYouWorkTheEntireShiftWithABedTimeAtEightPM() {
		HoursVsPayCalc calcAmount = new HoursVsPayCalc();
		assertEquals(132.0,
				calcAmount.amountPaid("5:00pm", "8:00pm", "4:00am"), 0);
	}

	@Test
	public void whenYouWorkTheEntireShiftWithABedTimeAtNinePM() {
		HoursVsPayCalc calcAmount = new HoursVsPayCalc();
		assertEquals(136.0,
				calcAmount.amountPaid("5:00pm", "9:00pm", "4:00am"), 0);
	}

	@Test
	public void whenYouWorkTheEntireShiftWithABedTimeAtTenPM() {
		HoursVsPayCalc calcAmount = new HoursVsPayCalc();
		assertEquals(140.0,
				calcAmount.amountPaid("5:00pm", "10:00pm", "4:00am"), 0);
	}

	@Test
	public void whenYouWorkTheEntireShiftWithABedTimeAtElevenPM() {
		HoursVsPayCalc calcAmount = new HoursVsPayCalc();
		assertEquals(144.0,
				calcAmount.amountPaid("5:00pm", "11:00pm", "4:00am"), 0);
	}
	
	@Test
	public void whenYouWorkAtSevenWithABedTimeAtNinePMAndEndAtThreeAM(){
		HoursVsPayCalc calcAmount = new HoursVsPayCalc();
		assertEquals(96.0,calcAmount.amountPaid("7:00pm", "9:00pm", "3:00am"),0);
	}
	
	@Test
	public void whenYouWorkAtTwelveWithABedTimeAtOneAMUntilFourAM(){
		HoursVsPayCalc calcAmount = new HoursVsPayCalc();
		assertEquals(64.0,calcAmount.amountPaid("12:00am", "1:00am", "4:00am"),0);
	}
	
	@Test
	public void whenYouWorkAtTwelveWithABedTimeAtOneAMUntilThreeAM(){
		HoursVsPayCalc calcAmount = new HoursVsPayCalc();
		assertEquals(48.0,calcAmount.amountPaid("12:00am", "1:00am", "3:00am"),0);
	}
}
