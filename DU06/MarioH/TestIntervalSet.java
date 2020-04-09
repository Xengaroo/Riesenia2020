import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;

import LISTTestScoring.LISTTestScoring;

public class TestIntervalSet {
	private static LISTTestScoring scoring = null;
		@BeforeClass
		public static void initScoring() {
			scoring = new LISTTestScoring();
			scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
		}

	@Test
	public void testGentle() {
			IntervalSet<Integer> is = new IntervalSet<Integer>();
			for(int i = 10; i < 20; i++) { 
				is.add(-i, i);
				is.remove(0, i);
			}
			for(int i = -25; i < 25; i++) { 
				assertEquals("nachadza sa " + i + " ? ", i == 19 || (i > -20) && i<=0, is.contains(i));
			}
			scoring.updateScore("lang:common_list_test_scoring_name", 10);		
	}
	
	@Test
	public void testEasy() {
		IntervalSet<Integer> is = new IntervalSet<Integer>();
		for(int i = 0; i < 100; i++) 
			is.add(i, i+2);
		for(int i = 0; i < 200; i++) 
			assertEquals("nachadza sa " + i + " ? ", i >= 0 && i <= 101, is.contains(i));
		scoring.updateScore("lang:common_list_test_scoring_name", 5);		
	}

	@Test
	public void testOdd() {
		IntervalSet<Integer> is = new IntervalSet<Integer>();
		for(int i = 0; i < 100; i++) { 
			is.add(2*i, 2*i+2);
			is.remove(2*i-1, 2*i+1);
		}
		for(int i = 0; i < 200; i++) 
			assertEquals("nachadza sa " + i + " ? ",  (i&1)>0, is.contains(i));
		is.add(0, 0);
		is.remove(0, 0);
		assertEquals("add(0, 0); remove(0, 0); ... nachadza sa 0? ",  true, is.contains(0));
		scoring.updateScore("lang:common_list_test_scoring_name", 10);		
	}
	@Test
	public void testRandom1() {
		final int POCET_POKUSOV = 10;
		final int POCET_OPERACII = 10;
		final int ROZSAH = 20;
		testRandomParameter(POCET_POKUSOV, POCET_OPERACII, ROZSAH);
		scoring.updateScore("lang:common_list_test_scoring_name", 10);		
	}

	@Test
	public void testRandom2() {
		final int POCET_POKUSOV = 100;
		final int POCET_OPERACII = 100;
		final int ROZSAH = 200;
		testRandomParameter(POCET_POKUSOV, POCET_OPERACII, ROZSAH);
		scoring.updateScore("lang:common_list_test_scoring_name", 10);		
	}
	@Test
	public void testRandom3() {
		final int POCET_POKUSOV = 1000;
		final int POCET_OPERACII = 1000;
		final int ROZSAH = 2000;
		testRandomParameter(POCET_POKUSOV, POCET_OPERACII, ROZSAH);
		scoring.updateScore("lang:common_list_test_scoring_name", 20);		
	}
	@Test
	public void testRandom4() {
		final int POCET_POKUSOV = 10000;
		final int POCET_OPERACII = 1000;
		final int ROZSAH = 2000;
		testRandomParameter(POCET_POKUSOV, POCET_OPERACII, ROZSAH);
		scoring.updateScore("lang:common_list_test_scoring_name", 20);		
	}

	private void testRandomParameter(int POCET_POKUSOV, int POCET_OPERACII, int ROZSAH) {
		Random rnd = new Random();
		for (int pokus = 0; pokus < POCET_POKUSOV; pokus++) {
			StringBuilder sb = new StringBuilder("Operacie:\n");
			IntervalSet<Integer> is = new IntervalSet<Integer>();
			MyIntervalSet<Integer> mis = new MyIntervalSet<Integer>();
			for (int i = 0; i < POCET_OPERACII; i++) {
				int a = rnd.nextInt(ROZSAH);
				int b = a + rnd.nextInt(ROZSAH);
				double p = rnd.nextDouble();
				if (p < 0.6) {
					sb.append("add(").append(a).append(",").append(b).append("); ");
					is.add(a, b);
					mis.add(a, b);
				}
				else {
					if(p >= 0.98){ b = a; } // remove empty interval with p=2%
					sb.append("remove(").append(a).append(",").append(b).append("); ");
					is.remove(a, b);
					mis.remove(a, b);
				}
				if(i % 10 == 9){
					sb.append("\n");
				}
			}
			for (int i = 0; i < ROZSAH+ROZSAH; i++) {
				boolean actual = is.contains(i);
				boolean expected = mis.contains(i);
				if(actual != expected){
					// This "double check" is to reduce the time inefficiency
					// caused by the expensive String construction in assert.
					assertEquals(sb.append("\n\nNachadza sa " + i + "? ").toString(),
							mis.contains(i), is.contains(i));
				}
			}
		}
	}
	
	@Test
	public void testDiabolical() {
		IntervalSet<Integer> is = new IntervalSet<Integer>();
		for(int i = 0; i < 1_000_000; i++) 
			is.add(i, i+1);
		for(int i = 0; i < 1_000_000; i++) 
			assertEquals("nachadza sa " + i + " ? ", true, is.contains(i));
		scoring.updateScore("lang:common_list_test_scoring_name", 15);
	}
}
