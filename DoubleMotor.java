import lejos.robotics.RegulatedMotorListener;
import lejos.robotics.RegulatedMotor;

public class DoubleMotor implements RegulatedMotor {
    private final RegulatedMotor left;
    private final RegulatedMotor right;

    public DoubleMotor(RegulatedMotor left, RegulatedMotor right) {
        this.left = left;
        this.right = right;
    }

    public void forward() {
        left.forward();
        right.forward();
    }

    public void backward() {
        left.backward();
        right.backward();
    }

    public void stop() {
        left.stop();
        right.stop();
    }

    public void flt() {
        left.flt();
        right.flt();
    }

    public boolean isMoving() {
        return left.isMoving();
    }
    

    public void addListener(RegulatedMotorListener listener) {
    }

    public RegulatedMotorListener removeListener() {
        return null;
    }

    public void stop(boolean immediateReturn) {
        left.stop(true);
        right.stop(immediateReturn);
    }

    public void flt(boolean immediateReturn) {
        left.flt(true);
        right.flt(immediateReturn);
    }

    public void waitComplete() {
        left.waitComplete();
        right.waitComplete();
    }

    public void rotate(int angle, boolean immediateReturn) {
        left.rotate(angle, true);
        right.rotate(angle, immediateReturn);
    }

    public void rotate(int angle) {
        left.rotate(angle, true);
        right.rotate(angle);
    }

    public void rotateTo(int limitAngle) {
        left.rotateTo(limitAngle);
        right.rotateTo(limitAngle);
    }

    public void rotateTo(int limitAngle, boolean immediateReturn) {
        left.rotateTo(limitAngle, true);
        right.rotateTo(limitAngle, immediateReturn);
    }

    public int getLimitAngle() {
        return left.getLimitAngle();
    }

    public void setSpeed(int speed) {
        left.setSpeed(speed);
        right.setSpeed(speed);
    }

    public int getSpeed() {
        return left.getSpeed();
    }

    public float getMaxSpeed() {
        return left.getMaxSpeed();
    }

    public boolean isStalled() {
        return left.isStalled() || right.isStalled();
    }

    public void setStallThreshold(int error, int time) {
        left.setStallThreshold(error, time);
        right.setStallThreshold(error, time);
    }

    public void setAcceleration(int acceleration) {
        left.setAcceleration(acceleration);
        right.setAcceleration(acceleration);
    }
    
    public int getRotationSpeed() {
        return left.getRotationSpeed();
    }
     
    public int getTachoCount() {
        return left.getTachoCount();
    }

    public void resetTachoCount() {
        left.resetTachoCount();
        right.resetTachoCount();
    }
}
