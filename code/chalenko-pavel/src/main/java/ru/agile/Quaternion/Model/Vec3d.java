package main.java.ru.agile.Quaternion.Model;

public class Vec3d {

	private double x = 0;
	private double y = 0;
	private double z = 0;
	
	public Vec3d(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vec3d(Vec3d vector) {
		if (vector != null){
			this.x = vector.x;
			this.y = vector.y;
			this.z = vector.z;
		} else {
			throw new NullPointerException();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vec3d){
			Vec3d vector = (Vec3d)obj;
			return ((this.x == vector.x) && (this.y == vector.y) && (this.z == vector.z));
		}
		return false;
	}

	public boolean equals(Vec3d other, double eps) {
		boolean isEqualsX = Math.abs(this.getX() - other.getX()) < eps;
		boolean isEqualsY = Math.abs(this.getY() - other.getY()) < eps;
		boolean isEqualsZ = Math.abs(this.getZ() - other.getZ()) < eps;
		return isEqualsX && isEqualsY && isEqualsZ;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public double getZ() {
		return this.z;
	}

	public Vec3d add(Vec3d other) {
		return new Vec3d(this.x + other.x, this.y + other.y, this.z + other.z);
	}

	public double dot(Vec3d other) {
		return this.x * other.x + this.y * other.y + this.z * other.z;
	}

	public double length() {
		return Math.sqrt(this.dot(this));
	}

	public Vec3d mul(Vec3d other) {
		double tmpX = this.y * other.z - this.z * other.y;
		double tmpY = - this.x * other.z + this.z * other.x;
		double tmpZ = this.x * other.y - this.y * other.x;
		return new Vec3d(tmpX, tmpY, tmpZ);
	}

	public Vec3d mul(double scalar) {
		return new Vec3d(scalar * this.x, scalar * this.y, scalar * this.z);
	}

	public void normalize() {
		double len = length();
		if (len != 0) {
			this.x /= len;
			this.y /= len;
			this.z /= len;
		} else {
			throw new ArithmeticException("Division by zero");
		}
	}
}
