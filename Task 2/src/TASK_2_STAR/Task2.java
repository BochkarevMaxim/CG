package TASK_2_STAR;

import Engine.renderEngine.RawModel;
import Engine.renderEngine.DisplayManager;
import Engine.renderEngine.Loader;
import Engine.renderEngine.Renderer;
import TASK_2_STAR.shaders.StaticShader;
import org.lwjgl.opengl.Display;

public class Task2 {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		StaticShader shader = new StaticShader();

		float[] vertices = {
				-0.17f, -0.4f, 0,
				0, -0.2f,	0,
				0.0f, 0.40f,	0,

				0.17f, -0.4f,	0,
				-0.25f, 0.1f,	0,
				0.25f, 0.1f,	0,

		};

		int[] indices = {
				1,0,2,
				1,2,3,
				1,4,5
		};

		RawModel model = loader.loadToVAO(vertices,indices);
		
		while(!Display.isCloseRequested()){

			renderer.prepare();
			shader.start();
			renderer.render(model);
			shader.stop();
			DisplayManager.updateDisplay();			
		}

		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}
}
