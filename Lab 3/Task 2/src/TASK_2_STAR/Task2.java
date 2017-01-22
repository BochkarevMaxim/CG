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
				-1f, -1f, 0,
				-1f, 1f, 0,
				1f, 1f, 0,
				1f, -1f, 0
		};

		int[] indices = {
				0,1,2,
				2,3,0
		};

		RawModel model = loader.loadToVAO(vertices, indices);
		
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
