package path;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.Scanner;

import org.junit.jupiter.api.Test;


public class PathUnitTests {
		
		// Initialize string and scanner
		String temp = "3 0 1 2 7 5 10";
		Scanner scan = new Scanner(temp);
		
		// Create test path
		Path testPath = new Path(scan);
	
		@Test
		public void testgetPointCount()
		{		
			int count = testPath.getPointCount();
			
			if(count != 3)
				fail("count = 3, but was returned as " + count);  // Record an error 		
			
		}
	
		@Test
		public void testgetX()
		{	
			int x2 = testPath.getX(1);
			
			if(x2 != 2)
				fail("x2 = 2, but was returned as " + x2);  // Record an error 		
			
		}
		
		@Test
		public void testgetY()
		{	
			int y3 = testPath.getY(2);
			
			if(y3 != 10)
				fail("y3 = 10, but was returned as " + y3);  // Record an error 		
			
		}
		
		@Test
		public void testadd()
		{	
			testPath.add(0, 0);
			
			int x4 = testPath.getX(3);
			int y4 = testPath.getY(3) ;
			
			if(x4 != 0 && y4 != 0)
				fail("(x4,y4) = (0,0), but was returned as (" + x4 + "," + y4);  // Record an error 		
			
		}

		@Test
		public void testtoString()
		{	
			String test = testPath.toString();
			
			System.out.println(test);
			
			String solution = "3" + "\n" + "0 1" + "\n" + "2 7" + "\n" + "5 10";
			
			if(!test.equals(solution))
				fail();  // Record an error 		
			
		}


}
