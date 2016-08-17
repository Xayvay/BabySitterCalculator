import static org.junit.Assert.*;

import org.junit.Test;

public class HoursVsPayCalcTest {

	
	@Test
	public void whenYouWorkTheEntireShiftWithABedTimeAtEightPM(){
		HoursVsPayCalc calcAmount = new HoursVsPayCalc();
		assertEquals(132.0,calcAmount.amountPaid("5:00pm", "8:00pm", "4:00am"),0);
	}

}
