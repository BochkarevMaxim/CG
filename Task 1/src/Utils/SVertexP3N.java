package Utils;

import com.sun.javafx.geom.Vec3f;
import com.sun.javafx.geom.Vec4f;
import com.sun.prism.impl.BufferUtil;

import java.nio.FloatBuffer;

class SVertexP3N{

	public Vec3f getPosition() {
		return position;
	}

	public void setPosition(Vec3f position) {
		this.position = position;
	}

	Vec3f position;

	public Vec3f getNormal() {
		return normal;
	}

	public void setNormal(Vec3f normal) {
		this.normal = normal;
	}

	Vec3f normal;

	SVertexP3N(){};
	SVertexP3N(Vec3f position){
		this.position = position;
	}
}

class MathVec3f {

	static Vec3f normalizeVector(Vec3f v) {
		float length_of_v = (float) Math.sqrt((v.x * v.x) + (v.y * v.y) + (v.z * v.z));
		return new Vec3f(v.x / length_of_v, v.y / length_of_v, v.z / length_of_v);
	}

	static Vec3f divideVectorByInt(Vec3f v, int divider){
return new Vec3f(v.x / divider, v.y / divider, v.z / divider);
	}

	static Vec3f getTwoVectorCross(Vec3f v1, Vec3f v2) {
		return new Vec3f(
				(v1.y * v2.z) - (v1.z * v2.y),
				(v1.z * v2.x) - (v1.x * v2.z),
				(v1.x * v2.y) - (v1.y * v2.x)
		);
	}
}

class MathVec4f {

	static FloatBuffer toBuffer(Vec4f v){
		FloatBuffer buffer = BufferUtil.newFloatBuffer(4);
		buffer.put(v.x);
		buffer.put(v.y);
		buffer.put(v.z);
		buffer.put(v.w);
		return buffer;
	}
}