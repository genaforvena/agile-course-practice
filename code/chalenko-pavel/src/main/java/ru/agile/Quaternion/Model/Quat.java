package main.java.ru.agile.Quaternion.Model;

public class Quat {

	double scalar;
	Vec3d vector;
	
	public Quat(double scalar, double i, double j, double k) {
		this.scalar = scalar;
		this.vector = new Vec3d(i, j, k);
	}

	public Quat(double scalar, Vec3d vector) {
		this.scalar = scalar;
		this.vector = new Vec3d(vector);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(scalar);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((vector == null) ? 0 : vector.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Quat){
			Quat q = (Quat)obj;
			return ((this.scalar == q.scalar) && (this.vector.equals(q.vector)));
		}
		return false;
	}
	
	public boolean equals(Quat other, double eps) {
		boolean isEqualsScalar = Math.abs(this.scalar - other.scalar) < eps;
		boolean isEqualsI = Math.abs(this.getI() - other.getI()) < eps;
		boolean isEqualsJ = Math.abs(this.getJ() - other.getJ()) < eps;
		boolean isEqualsK = Math.abs(this.getK() - other.getK()) < eps;
		return isEqualsScalar && isEqualsI && isEqualsJ && isEqualsK;
	}

	public double getScalar() {
		return scalar;
	}

	public double getI() {
		return vector.getX();
	}
	
	public double getJ() {
		return vector.getY();
	}

	public double getK() {
		return vector.getZ();
	}

	public Vec3d getVec() {
		return new Vec3d(vector);
	}

	public Quat add(Quat q2) {
		return new Quat(this.scalar + q2.scalar, this.getVec().add(q2.getVec()));
	}

	public double length() {
		return Math.sqrt(scalar * scalar + vector.dot(vector));
	}

	public Quat conj() {
		return new Quat(scalar, vector.mul(-1));
	}

	public Quat mul(Quat other) {
		double firstScalarSummand = this.scalar * other.scalar;
		double secondScalarSummand = - this.vector.dot(other.vector);
		double scalarPart = firstScalarSummand + secondScalarSummand;
		Vec3d firstVectorSummand = other.vector.mul(this.scalar);
		Vec3d secondVectorSummand = this.vector.mul(other.scalar);
		Vec3d thirdVectorSummand = this.vector.mul(other.vector);
		Vec3d vectorPart = firstVectorSummand.add(secondVectorSummand.add(thirdVectorSummand));
		return new Quat(scalarPart, vectorPart);
	}
	
	public Quat mul(double scalar) {
		return new Quat(this.scalar * scalar, this.vector.mul(scalar));
	}

	public Quat inv() {
		Quat conjQuat = this.conj();
		double len2 = this.length() * this.length();
		double coeff = 1.0 / len2;
		return conjQuat.mul(coeff);
	}

	public double getArg() {
		return Math.acos(this.getScalar() / this.length());
	}

	public static Quat createFromAxisAngle(Vec3d vector, double angle) {
		double qw = Math.cos(angle / 2);
		double qx = vector.getX() * Math.sin(angle / 2);
		double qy = vector.getY() * Math.sin(angle / 2);
		double qz = vector.getZ() * Math.sin(angle / 2);
		return new Quat(qw, qx, qy, qz);
	}

	public static Quat createFromYPR(double yaw, double pitch, double roll) {
		double sy = Math.sin(yaw * 0.5);		double cy = Math.cos(yaw * 0.5);
		double sp = Math.sin(pitch * 0.5);		double cp = Math.cos(pitch * 0.5);
		double sr = Math.sin(roll * 0.5);		double cr = Math.cos(roll * 0.5);
		
		double w = (sy * sp * sr + cy * cp * cr);
		double x = (-sy * sp * cr + cy * cp * sr);
		double y = (sy * cp * sr + cy * sp * cr);
		double z = (sy * cp * cr - cy * sp * sr);
		
		return new Quat(w, x, y, z);
	}
	
	public static Quat createFromYPR(Vec3d vector) {
		double sy = Math.sin(vector.getX() * 0.5);	double cy = Math.cos(vector.getX() * 0.5);
		double sp = Math.sin(vector.getY() * 0.5);	double cp = Math.cos(vector.getY() * 0.5);
		double sr = Math.sin(vector.getZ() * 0.5);	double cr = Math.cos(vector.getZ() * 0.5);
		
		double srcp = sr * cp, crsp = cr * sp;
		double crcp = cr * cp, srsp = sr * sp;
		
		double w=(crcp * cy + srsp * sy);
		double x=(srcp * cy - crsp * sy);
		double y=(crsp * cy + srcp * sy);
		double z=(crcp * sy - srsp * cy);
		
		return new Quat(w, x, y, z);
	}

}
