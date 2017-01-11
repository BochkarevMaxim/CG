package TASK_2_STAR.shaders;

import Engine.Shader.ShaderProgram;

public class StaticShader extends ShaderProgram {
	
	private static final String VERTEX_FILE = "src/TASK_2_STAR/shaders/vertexShader.vert";
	private static final String FRAGMENT_FILE = "src/TASK_2_STAR/shaders/fragmentShader.frag";

	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}
	
	

}
