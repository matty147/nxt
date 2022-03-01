import java.io.IOException;
import java.util.Random;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.RangeFinder;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.SteeringPilot;
import lejos.robotics.objectdetection.Feature;
import lejos.robotics.objectdetection.FeatureDetector;
import lejos.robotics.objectdetection.FeatureListener;
import lejos.robotics.objectdetection.RangeFeatureDetector;

public class Roomba implements FeatureListener {
	private final static double WHEEL_DIAMETER = 13.5;
	
    private final RegulatedMotor mainMotor;
	private final RegulatedMotor steeringMotor;
	private final FeatureDetector fd;
    private final Random rand = new Random();
	
    public Roomba(final RegulatedMotor mainMotor, final RegulatedMotor steeringMotor, final SensorPort sonicPort) {
		this.mainMotor = mainMotor;
		this.steeringMotor = steeringMotor;
		
		RangeFinder sonar = new UltrasonicSensor(sonicPort);
        fd = new RangeFeatureDetector(sonar, 40, 250);
        fd.enableDetection(false); 
        fd.addListener(this);
    }
	
	public void enableDetection(boolean enable) {
        fd.enableDetection(enable); 
	}		
    
	public void travel(double distance, boolean immediateReturn) {
		int tachos = (int)((distance * 360) / (WHEEL_DIAMETER * Math.PI));
		if (tachos > 0) {
			System.out.println("Forward T");
		}
		else {
			System.out.println("Backward T");
		}
			
		mainMotor.rotate(-tachos, immediateReturn);	   
	}

	public void forward() {
		System.out.println("Forward");
		mainMotor.backward();	   
	}
	   
	public void featureDetected(Feature feature, FeatureDetector detector) {
		Sound.beepSequence();

		detector.enableDetection(false);

		travel(-(5 + rand.nextInt(10)), false);
	
		// turn a wheels by a random angle
		int angle = (Math.random() > 0.5 ? -1 : 1) * (10 + rand.nextInt(50));   // todo can be too big
		System.out.println("Angle: " + angle);
		steeringMotor.rotate(angle);

	    detector.enableDetection(true);

	    forward();
	}

    public static void main(String[] args) throws IOException, InterruptedException {
		final DoubleMotor mainMotor = new DoubleMotor(Motor.B, Motor.C);
		mainMotor.setSpeed(72);

		final RegulatedMotor steeringMotor = Motor.A;
    	
        final Roomba roomba = new Roomba(mainMotor, steeringMotor, SensorPort.S4);
		
        while(!Button.ESCAPE.isDown()) {
        	System.out.println("Press ENTER key");
        	Button.ENTER.waitForPressAndRelease();
        	
        	roomba.enableDetection(true); 
	        roomba.forward();
	        
	        while(mainMotor.isMoving())
	        	Thread.sleep(500);
        }
    }

}