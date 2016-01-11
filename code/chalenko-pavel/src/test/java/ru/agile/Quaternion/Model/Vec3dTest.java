package test.java.ru.agile.Quaternion.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import main.java.ru.agile.Quaternion.Model.Vec3d;

import org.junit.Before;
import org.junit.Test;

public class Vec3dTest {

	private Vec3d vector1;
	private Vec3d vector2;
	final private double eps = 0.001;
	
	@Before
	public void setUp() throws Exception {
		vector1 = new Vec3d(1.0, 2.0, 3.0);
		vector2 = new Vec3d(0.5, 0.25, 0.33333);
	}

	@Test
	public void canCreateVec3dWithInitialValues() {
		assertNotNull("Can't create Vec3d with initial value", vector1);
		assertNotNull("Can't create Vec3d with initial value", vector2);
	}
	
	@Test
	public void areVec3dEquals(){
		Vec3d vector3 = new Vec3d(1, 2.0, 3.0);
		
		assertTrue("Two vectors are not equal", vector1.equals(vector3));
	}
	
	@Test
	public void isVec3dEqualsComutative(){
		Vec3d vector = new Vec3d(1, 2.0, 3.0);
		
		boolean e1 = vector1.equals(vector);
		boolean e2 = vector.equals(vector1);
		
		assertEquals("Equals isn't comutative", e1, e2);
	}
	
	@Test
	public void Vec3dAreNotEquals(){
		Vec3d vector3 = new Vec3d(1, 2.0, 3.0);
		
		assertFalse("Two vectors are equal", vector2.equals(vector3));
	}
	
	@Test
	public void Vec3dAreNotEqualsInt(){
		int number = 7;
		
		assertFalse("Two vectors are equal", vector2.equals(number));
	}
	
	@Test
	public void Vec3dAreNotEqualsParticly1(){
		Vec3d vector3 = new Vec3d(1, 2.0, 3.5);
		
		assertFalse("Two vectors are equal", vector1.equals(vector3));
	}
	
	@Test
	public void Vec3dAreNotEqualsParticly2(){
		Vec3d vector3 = new Vec3d(1, 2.5, 3.5);
		
		assertFalse("Two vectors are equal", vector1.equals(vector3));
	}
	
	@Test
	public void canEqualsEps(){
		vector2 = new Vec3d(1, 2.+ eps/2, 3 - eps/2);
		Vec3d vector3 = new Vec3d(1, 2 + 2*eps, 3);
		
		boolean v1equalsv2 = vector1.equals(vector2, eps);
		boolean v1equalsv3 = vector1.equals(vector3, eps);
		
		assertTrue("Can't compare vectors with precision", v1equalsv2);
		assertFalse("Can't compare vectors with precision", v1equalsv3);
	}
	
	@Test
	public void canSetInitialValueX(){
		double x = vector1.getX();
		
		assertEquals("Can't set initial x value", 1, x, eps);
	}
	
	@Test
	public void canSetInitialValueY(){
		double y = vector1.getY();
		
		assertEquals("Can't set initial y value", 2.0, y, eps);
	}
	
	@Test
	public void canSetInitialValueZ(){
		double z = vector1.getZ();
		
		assertEquals("Can't set initial z value", 3.0, z, eps);
	}
	
	@Test
	public void canAdd(){
		Vec3d expectedSumVector = new Vec3d(1.5, 2.25, 3.33333);
		
		Vec3d actualSumVector = vector1.add(vector2);
		
		assertTrue("Can't added vectors", actualSumVector.equals(expectedSumVector));
	}
	
	@Test
	public void isAddComutative(){
		Vec3d add12 = vector1.add(vector2);
		Vec3d add21 = vector2.add(vector1);
		boolean actual = add12.equals(add21);
		
		assertTrue("Vector add isn't comutative", actual);
	}
	
	@Test
	public void canCalculateDotProduct(){
		double expectedDot = 2;
		
		double actualDot = vector1.dot(vector2);
		
		assertEquals("Can't scalar multiply", expectedDot, actualDot, eps);
	}
	
	@Test
	public void canCalculateDotProductWithZeroVector(){
		double expectedDot = 0;
		vector2 = new Vec3d(0, 0, 0);
		
		double actualDot = vector1.dot(vector2);
		
		assertEquals("Can't scalar multiply", expectedDot, actualDot, eps);
	}
	
	@Test
	public void isDotProductComutative(){
		double actualDot12 = vector1.dot(vector2);
		double actualDot21 = vector2.dot(vector1);
		
		assertEquals("Can't scalar multiply", actualDot12, actualDot21, eps);
	}
	
	@Test
	public void canCalculateLength(){
		double expectedLen = 3.74166;
		
		double actualLen = vector1.length();
		
		assertEquals("Can't calculate length", expectedLen, actualLen, eps);
	}
	
	@Test
	public void canCalculateZeroVectorLength(){
		double expectedLen = 0;
		vector1 = new Vec3d(0, 0, 0);
		
		double actualLen = vector1.length();
		
		assertEquals("Can't calculate length", expectedLen, actualLen, eps);
	}
	
	@Test
	public void canVecMulOrt(){
		Vec3d ortX = new Vec3d(1, 0, 0);
		Vec3d ortY = new Vec3d(0, 1, 0);
		Vec3d ortZ = new Vec3d(0, 0, 1);
		
		Vec3d actualVector = ortX.mul(ortY);
		
		assertTrue("Can't vector multiply", actualVector.equals(ortZ));
	}
	
	@Test
	public void canVecMul(){
		Vec3d expectedVector = new Vec3d(-0.08334, 1.16667, -0.75);
		
		Vec3d actualVector = vector1.mul(vector2);
		
		//assertTrue("Can't calculate length", actualVector.equals(expectedVector));
		assertEquals("Can't vector multiply: wrong i component", expectedVector.getX(), actualVector.getX(), eps);
		assertEquals("Can't vector multiply: wrong j component", expectedVector.getY(), actualVector.getY(), eps);
		assertEquals("Can't vector multiply: wrong k component", expectedVector.getZ(), actualVector.getZ(), eps);
	}
	
	@Test
	public void isVectorProductNonComutative(){
		Vec3d mul12 = vector1.mul(vector2);
		Vec3d mul21 = vector2.mul(vector1);
		
		boolean actual = mul12.equals(mul21);
		
		assertFalse("Vector multiply is comutative", actual);
	}
	
	@Test
	public void canMulScalarToVector(){
		Vec3d expectedVector = new Vec3d(2.0, 4, 6);
		
		Vec3d actualVector = vector1.mul(2);
		
		assertTrue("Can't multiply vector to scalar", actualVector.equals(expectedVector));
	}
	
	@Test
	public void canMulToZero(){
		Vec3d expectedVector = new Vec3d(0, 0, 0);
		
		Vec3d actualVector = vector1.mul(0);
		
		assertTrue("Can't multiply vector to scalar", actualVector.equals(expectedVector));
	}
	
	@Test
	public void canCopy(){
		Vec3d actualVector = new Vec3d(vector1);
		
		assertTrue("Can't copy vector", actualVector.equals(vector1));
		assertFalse("Can't copy vector", actualVector.equals(vector2));
	}
	
	@Test (expected = NullPointerException.class)
	public void canThrowExceptionWhenCopyNull(){
		Vec3d actualVector = new Vec3d(null);
		
		assertTrue("Can't copy vector", actualVector.equals(vector1));
		assertFalse("Can't copy vector", actualVector.equals(vector2));
	}
	
	@Test
	public void canNormalize(){
		Vec3d expectedVector = new Vec3d(0.577, .577, .577);
		Vec3d actualVector = new Vec3d(1, 1, 1);
		
		actualVector.normalize();
		
		assertTrue("Can't normalize vector", actualVector.equals(expectedVector, eps));
	}
	
	@Test
	public void canNormalizeOrt(){
		Vec3d expectedVector = new Vec3d(1, 0, 0);
		Vec3d actualVector = new Vec3d(1, 0, 0);
		
		actualVector.normalize();
		
		assertTrue("Can't normalize ort", actualVector.equals(expectedVector, eps));
	}
	
	@Test
	public void canNormalizeVectorWithLength2(){
		Vec3d expectedVector = new Vec3d(0, 1, 0);
		Vec3d actualVector = new Vec3d(0, 2, 0);
		
		actualVector.normalize();
		
		assertTrue("Can't normalize vector", actualVector.equals(expectedVector, eps));
	}
	
	@Test (expected = ArithmeticException.class)
	public void canNotNormalizeZeroVector(){
		Vec3d actualVector = new Vec3d(0., .0, .0);
		
		actualVector.normalize();
	}
}
