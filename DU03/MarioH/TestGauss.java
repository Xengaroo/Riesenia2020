import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestGauss {
	@Test
	public void testVydel() {
		Gauss p = new Gauss(new double[][]{
            {2, 4, -2, 6},
            {0, -3, 6, -3},
            {4, 1, -2, 2} });
		p.vydel(0, 2);
		assertTrue("test 1:\n" + p, equals(p.dajRiadok(0), new double[]{1, 2, -1, 3})); 
		p.vydel(1, 3);
		assertTrue("test 2:\n" + p, equals(p.dajRiadok(1), new double[]{0,-1,2,-1})); 
		p.vydel(2, 4);
		assertTrue("test 3:\n" + p, equals(p.dajRiadok(2), new double[]{1, 0.25, -0.5, 0.5}));
		//-----------		
	}

	@Test
	public void testVymen() {
		Gauss p = new Gauss(new double[][]{
            {2, 4, -2, 6},
            {0, -3, 6, -3},
            {4, 1, -2, 2} });
		p.vymen(0, 1);
		assertTrue("test 1:\n" + p, equals(p.dajRiadok(0), new double[]{0, -3, 6, -3})); 
		assertTrue("test 1a:\n" + p, equals(p.dajRiadok(1), new double[]{2, 4, -2, 6})); 
		assertTrue("test 1b:\n" + p, equals(p.dajRiadok(2), new double[]{4, 1, -2, 2}));
		p.vymen(2, 1);
		assertTrue("test 2:\n" + p, equals(p.dajRiadok(0), new double[]{0, -3, 6, -3})); 
		assertTrue("test 2b:\n" + p, equals(p.dajRiadok(1), new double[]{4, 1, -2, 2}));
		assertTrue("test 2a:\n" + p, equals(p.dajRiadok(2), new double[]{2, 4, -2, 6})); 
		p.vymen(2, 0);
		assertTrue("test 3:\n" + p, equals(p.dajRiadok(2), new double[]{0, -3, 6, -3})); 
		assertTrue("test 3b:\n" + p, equals(p.dajRiadok(1), new double[]{4, 1, -2, 2}));
		assertTrue("test 3a:\n" + p, equals(p.dajRiadok(0), new double[]{2, 4, -2, 6})); 
		//-----------
	}
	
	@Test
	public void testPripocitaj() {
		Gauss p = new Gauss(new double[][]{
            {2, 4, -2, 6},
            {0, -3, 6, -3},
            {4, 1, -2, 2} });
		p.pripocitaj(0, 2, 1);
		assertTrue("test 1:\n" + p, equals(p.dajRiadok(0), new double[]{2, -2, 10, 0})); 
		assertTrue("test 1a:\n" + p, equals(p.dajRiadok(1), new double[]{0, -3, 6, -3})); 
		assertTrue("test 1b:\n" + p, equals(p.dajRiadok(2), new double[]{4, 1, -2, 2}));
		p.pripocitaj(2, -2, 0);
		assertTrue("test 2:\n" + p, equals(p.dajRiadok(0), new double[]{2, -2, 10, 0})); 
		assertTrue("test 2a:\n" + p, equals(p.dajRiadok(1), new double[]{0, -3, 6, -3})); 
		assertTrue("test 2b:\n" + p, equals(p.dajRiadok(2), new double[]{0, 5, -22, 2}));
		p.pripocitaj(2, 0, 0);
		assertTrue("test 3:\n" + p, equals(p.dajRiadok(0), new double[]{2, -2, 10, 0})); 
		assertTrue("test 3a:\n" + p, equals(p.dajRiadok(1), new double[]{0, -3, 6, -3})); 
		assertTrue("test 3b:\n" + p, equals(p.dajRiadok(2), new double[]{0, 5, -22, 2}));
		p.pripocitaj(2, -1, 2);
		assertTrue("test 3:\n" + p, equals(p.dajRiadok(0), new double[]{2, -2, 10, 0})); 
		assertTrue("test 3a:\n" + p, equals(p.dajRiadok(1), new double[]{0, -3, 6, -3})); 
		assertTrue("test 3b:\n" + p, equals(p.dajRiadok(2), new double[]{0, 0, 0, 0}));
		//-----------
	}
	
	@Test
	public void testGauss() {
		{
		Gauss p = new Gauss(new double[][]{
			{2, 4, -2, 6},
			{0, -3, 6, -3},
			{4, 1, -2, 2} });
		assertTrue("test 4:\n" + p, p.gauss()); 
		assertTrue("test 1:\n" + p, equals(p.dajRiadok(0), new double[] {1.0, 0.0, 0.0, 0.25})); 
		assertTrue("test 1a:\n" + p, equals(p.dajRiadok(1), new double[]{-0.0, 1.0, 0.0, 1.5})); 
		assertTrue("test 1b:\n" + p, equals(p.dajRiadok(2), new double[]{-0.0, -0.0, 1.0, 0.25}));
		}
		{
		Gauss p = new Gauss(new double[][]{
			{1, 0, 0, 6},
			{0, 1, 0, -3},
			{0, 0, 1, 2}});
		assertTrue("test 4:\n" + p, p.gauss()); 
		assertTrue("test 2:\n" + p, equals(p.dajRiadok(0), new double[] {1.0, 0.0, 0.0, 6})); 
		assertTrue("test 2a:\n" + p, equals(p.dajRiadok(1), new double[]{0.0, 1.0, 0.0, -3})); 
		assertTrue("test 2b:\n" + p, equals(p.dajRiadok(2), new double[]{0.0, 0.0, 1.0, 2}));
		}
		{
		Gauss p = new Gauss(new double[][]{
			{0, 0, 1, 6},
			{0, 1, 0, -3},
			{1, 0, 0, 2}
			});
		assertTrue("test 4:\n" + p, p.gauss()); 
		assertTrue("test 3:\n" + p, equals(p.dajRiadok(0), new double[] {1.0, 0.0, 0.0, 2})); 
		assertTrue("test 3a:\n" + p, equals(p.dajRiadok(1), new double[]{0.0, 1.0, 0.0, -3})); 
		assertTrue("test 3b:\n" + p, equals(p.dajRiadok(2), new double[]{0.0, 0.0, 1.0, 6}));
		}
		{
		Gauss p = new Gauss(new double[][]{
			{0, 1, 0, 6},
			{0, 1, 0, -3},
			{1, 0, 0, 2}
			});
		assertFalse("test 4:\n" + p, p.gauss()); 
		}
		{
		Gauss p = new Gauss(new double[][]{
			{1, 1, 1, 6},
			{1, 1, 1, -3},
			{1, 1, 1, 2}
			});
		assertFalse("test 4:\n" + p, p.gauss()); 
		}
}
	
	public static boolean equals(double[] a, double[] b) {
		if (a == null && b == null)
			return true;
		if (a != null && b != null && a.length == b.length) {
			for (int i = 0; i<a.length; i++)
				if (Math.abs(a[i]-b[i]) > 0.001 )
					return false;
			return true;	
		} else return false;
	}

}
