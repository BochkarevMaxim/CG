import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
class TwistValueController implements KeyListener {

	private static final float MIN_TWIST = 0f;
	private static final float MAX_TWIST  = 2 * (float)Math.PI;
	private static final float NEXT_TWIST_STEP  = (float)Math.PI / 500;
	private static final float TWIST_CHANGE_SPEED   = 5.0f;

	void update(){

		final float delta = Math.abs(nextTwistValue - currentTwistValue);
		if (delta > Float.MIN_VALUE)
		{
			final float valueSign = (nextTwistValue > currentTwistValue) ? 1 : -1;
			final float growth = TWIST_CHANGE_SPEED;

			if (growth > delta)
				currentTwistValue = nextTwistValue;
			else
				currentTwistValue += valueSign * growth;
		}
	}

	private float currentTwistValue = 0;
	private float nextTwistValue = 0;

	@Override
	public void keyPressed(KeyEvent keyEvent) {

		if (keyEvent.getKeyChar() == '+' )
			nextTwistValue = Math.min(nextTwistValue + NEXT_TWIST_STEP, MAX_TWIST);

		else if (keyEvent.getKeyChar() == '-')
			nextTwistValue = Math.max(nextTwistValue - NEXT_TWIST_STEP, MIN_TWIST);
	}


	public float getCurrentTwistValue() {
		return currentTwistValue;
	}

	public void setCurrentTwistValue(float currentTwistValue) {
		this.currentTwistValue = currentTwistValue;
	}

	public float getNextTwistValue() {
		return nextTwistValue;
	}

	public void setNextTwistValue(float nextTwistValue) {
		this.nextTwistValue = nextTwistValue;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
	@Override
	public void keyReleased(KeyEvent keyEvent) {

	}

}
