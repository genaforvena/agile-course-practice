package test.java.ru.agile.Quaternion.Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.java.ru.agile.Quaternion.Model.Quat;
import main.java.ru.agile.Quaternion.Model.Vec3d;

public class QuatTest {

	private Quat q1;
	private Quat q2;
	final private double eps = 0.001;
	
	@Before
	public void setUp() throws Exception {
		q1 = new Quat(1, 2., 3.0, 4);
		q2 = new Quat(2, new Vec3d(1., .5, 0.25));
	}

	@Test
	public void canCreateQuatWithInitialValues() {
		assertNotNull("Can't create Vec3d with initial value", q1);
		assertNotNull("Can't create Vec3d with initial value", q2);
	}
	
	@Test
	public void areQuatEquals(){
		Quat q3 = new Quat(1.0, 2, 3.0, 4.);
		
		assertTrue("Two quaternions are not equal", q1.equals(q3));
	}
	
	@Test
	public void isQuatEqualsComutative(){
		Quat q = new Quat(1, 2.0, 3.0, 4);
		
		boolean e1 = q1.equals(q);
		boolean e2 = q.equals(q1);
		
		assertEquals("Equals isn't comutative", e1, e2);
	}
	
	@Test
	public void QuatAreNotEquals(){
		Quat q3 = new Quat(1.0, 2, 3.0, 4.);
		
		assertFalse("Two quaternions are equal", q2.equals(q3));
	}
	
	@Test
	public void QuatAreNotEqualsInt(){
		int number = 12;
		
		assertFalse("Two quaternions are equal", q2.equals(number));
	}
	
	@Test
	public void canEqualsEps(){
		q2 = new Quat(1, 2. + eps/2, 3 - eps/2, 4.);
		Quat q3 = new Quat(1, 2 + 2 * eps, 3, 4);
		
		boolean q1equalsq2 = q1.equals(q2, eps);
		boolean q1equalsq3 = q1.equals(q3, eps);
		
		assertTrue("Can't compare quaternions with precision", q1equalsq2);
		assertFalse("Can't compare quaternions with precision", q1equalsq3);
	}

	@Test
	public void canSetInitialValueScalar(){
		double s = q1.getScalar();
		
		assertEquals("Can't set initial scalar value", 1, s, eps);
	}
	
	@Test
	public void canSetInitialValueJ(){
		double j = q1.getJ();
		
		assertEquals("Can't set initial j value", 3.0, j, eps);
	}
	
	@Test
	public void canSetInitialValueVector(){
		Vec3d expectedVector = new Vec3d(2, 3, 4);
		
		Vec3d actualVector = q1.getVec();
		
		assertTrue("Can't set initial vector value", expectedVector.equals(actualVector));
	}
	
	@Test
	public void canAdd(){
		Quat expectedSumQuat = new Quat(3, 3, 3.5, 4.25);
		
		Quat actualSumQuat = q1.add(q2);
		
		assertTrue("Can't added quaternions", actualSumQuat.equals(expectedSumQuat));
	}
	
	@Test
	public void isAddComutative(){
		Quat q12 = q1.add(q2);
		Quat q21 = q2.add(q1);
		boolean actual = q12.equals(q21);
		
		assertTrue("Quaternions add isn't comutative", actual);
	}
	
	@Test
	public void canCalculateLength(){
		double expectedLen = 5.477;
		
		double actualLen = q1.length();
		
		assertEquals("Can't calculate length", expectedLen, actualLen, eps);
	}
	
	@Test
	public void canCalculateConjugateVector(){
		Quat expectedConjQuat = new Quat(1, -2, -3, -4);
		
		Quat actualConjQuat = q1.conj();
		
		assertTrue("Can't calculate conjugate quaternion", actualConjQuat.equals(expectedConjQuat));
	}
	
	@Test
	public void isConjugateOfConjugateEqualsOriginal(){
		Quat actualConjConjQuat = q1.conj().conj();
		
		assertTrue("Can't calculate conjugate quaternion", actualConjConjQuat.equals(q1));
	}
	
	@Test
	public void canMulScalarPartQuat(){
		Quat expectedMulQuat = new Quat(8, 0, 0, 0);
		q1 = new Quat(2, 0, 0, 0);
		q2 = new Quat(4, 0, 0, 0);
		
		Quat actualMulQuat = q1.mul(q2);
		
		assertTrue("Can't multiply quaternions", actualMulQuat.equals(expectedMulQuat));
	}
	
	@Test
	public void canMulVectorPartQuat(){
		Quat expectedMulQuat = new Quat(-10, -4, 8, -4);
		q1 = new Quat(0, 1, 2, 3);
		q2 = new Quat(0, 3, 2, 1);
		
		Quat actualMulQuat = q1.mul(q2);
		
		assertTrue("Can't multiply quaternions", actualMulQuat.equals(expectedMulQuat));
	}
	
	@Test
	public void canMulScalalPartQuatToVectorPartQuat(){
		Quat expectedMulQuat = new Quat(0, 6, 4, 2);
		q1 = new Quat(2, 0, 0, 0);
		q2 = new Quat(0, 3, 2, 1);
		
		Quat actualMulQuat = q1.mul(q2);
		
		assertTrue("Can't multiply quaternions", actualMulQuat.equals(expectedMulQuat));
	}
	
	@Test
	public void canMul(){
		Quat expectedMulQuat = new Quat(-2.5, 3.75, 10, 6.25);
		
		Quat actualMulQuat = q1.mul(q2);
		
		assertTrue("Can't multiply quaternions", actualMulQuat.equals(expectedMulQuat));
	}
	
	@Test
	public void isMulNonComutative(){
		Quat q12 = q1.mul(q2);
		Quat q21 = q2.mul(q1);
		boolean actual = q12.equals(q21);
		
		assertFalse("Quaternions mul is comutative", actual);
	}
	
	@Test
	public void canInv(){
		Quat expectedInvQuat = new Quat(0.033, -0.067, -0.1, -0.133);
		
		Quat actualInvQuat = q1.inv();
		
		assertEquals("Can't inverse quaternion", actualInvQuat.getScalar(), expectedInvQuat.getScalar(), eps);
		assertEquals("Can't inverse quaternion", actualInvQuat.getI(), expectedInvQuat.getI(), eps);
		assertEquals("Can't inverse quaternion", actualInvQuat.getJ(), expectedInvQuat.getJ(), eps);
		assertEquals("Can't inverse quaternion", actualInvQuat.getK(), expectedInvQuat.getK(), eps);
	}
	
	@Test
	public void isInvInvEqualsIdentical(){
		Quat actualInvInvQuat = q1.inv().inv();
		
		assertEquals("Can't inverse quaternion", actualInvInvQuat.getScalar(), q1.getScalar(), eps);
		assertEquals("Can't inverse quaternion", actualInvInvQuat.getI(), q1.getI(), eps);
		assertEquals("Can't inverse quaternion", actualInvInvQuat.getJ(), q1.getJ(), eps);
		assertEquals("Can't inverse quaternion", actualInvInvQuat.getK(), q1.getK(), eps);
	}
	
	@Test
	public void isQuatMulInvEqualsIdentical(){
		Quat expectedIdentical = new Quat(1, 0, 0, 0);
		
		Quat actualMulInvt = q1.mul(q1.inv());
		
		assertTrue("Can't inverse quaternion", actualMulInvt.equals(expectedIdentical));
	}
	
	@Test
	public void canGetArg(){
		double expectedAngle = 1.387;
		
		double actualAngle = q1.getArg();
		
		assertEquals("Can't get argument of quaternion", expectedAngle, actualAngle, eps);
	}
	
	@Test
	public void canCreateQuaternionFromAxisAngleX0(){
		Quat expectedQuat = new Quat(1, 0., 0, 0);
		
		Quat actualQuat = Quat.createFromAxisAngle(new Vec3d(1, 0, 0), 0);
		
		assertTrue("Can't create quaternion from axis-angle", expectedQuat.equals(actualQuat, eps));
	}
	
	@Test
	public void canCreateQuaternionFromAxisAngleX90(){
		Quat expectedQuat = new Quat(0.707, 0.707, 0, 0);
		
		Quat actualQuat = Quat.createFromAxisAngle(new Vec3d(1, 0, 0), Math.PI/2);
		
		assertTrue("Can't create quaternion from axis-angle", expectedQuat.equals(actualQuat, eps));
	}
	
	@Test
	public void canCreateQuaternionFromAxisAngleX180(){
		Quat expectedQuat = new Quat(0, 1, 0, 0);
		
		Quat actualQuat = Quat.createFromAxisAngle(new Vec3d(1, 0, 0), Math.PI);
		
		assertTrue("Can't create quaternion from axis-angle", expectedQuat.equals(actualQuat, eps));
	}
	
	@Test
	public void canCreateQuaternionFromAxisAngleZ180(){
		Quat expectedQuat = new Quat(0., 0., 0, 1);
		
		Quat actualQuat = Quat.createFromAxisAngle(new Vec3d(0, 0, 1), Math.PI);
		
		assertTrue("Can't create quaternion from axis-angle", expectedQuat.equals(actualQuat, eps));
	}
	
	@Test
	public void canCreateQuaternionFromAxisAngleXYZ180(){
		Quat expectedQuat = new Quat(0., 0.577, 0.577, 0.577);
		Vec3d axis = new Vec3d(1, 1, 1);
		axis.normalize();
		
		Quat actualQuat = Quat.createFromAxisAngle(axis, Math.PI);
		
		assertTrue("Can't create quaternion from axis-angle", expectedQuat.equals(actualQuat, eps));
	}
	
	@Test
	public void canCreateQuaternionFromYPR0(){
		Quat expectedQuat = new Quat(1, 0, 0, 0);
		
		Quat actualQuat = Quat.createFromYPR(0, 0, 0);
		
		assertTrue("Can't create quaternion from YAW, PITCH, ROLL", expectedQuat.equals(actualQuat));
	}
	
	@Test
	public void canCreateQuaternionFromYPR0090(){
		Quat expectedQuat = new Quat(0.707, 0.707, 0, 0);
		
		Quat actualQuat = Quat.createFromYPR(0, 0, Math.PI / 2);
		
		assertTrue("Can't create quaternion from YAW, PITCH, ROLL", expectedQuat.equals(actualQuat, eps));
	}
	
	@Test
	public void canCreateQuaternionFromYPR00180(){
		Quat expectedQuat = new Quat(0, 1, 0, 0);
		
		Quat actualQuat = Quat.createFromYPR(0, 0, Math.PI);
		
		assertTrue("Can't create quaternion from YAW, PITCH, ROLL", expectedQuat.equals(actualQuat, eps));
	}
	
	@Test
	public void canCreateQuaternionFromYPR18000(){
		Quat expectedQuat = new Quat(0., 0., 0, 1);
		
		Quat actualQuat = Quat.createFromYPR(Math.PI, 0, 0);
		
		assertTrue("Can't create quaternion from YAW, PITCH, ROLL", expectedQuat.equals(actualQuat, eps));
	}
	
	@Test
	public void canCreateQuaternionFromYPR(){
		Quat expectedQuat = new Quat(0.146, 0.854, 0.354, -0.354);
		
		Quat actualQuat = Quat.createFromYPR(Math.PI / 4, Math.PI / 4, Math.PI);
		
		assertTrue("Can't create quaternion from YAW, PITCH, ROLL", expectedQuat.equals(actualQuat, eps));
	}
}
