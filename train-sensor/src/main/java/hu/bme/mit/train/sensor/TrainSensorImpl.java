package hu.bme.mit.train.sensor;


import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;

import java.util.Date;

import com.google.common.collect.*;

public class TrainSensorImpl implements TrainSensor {

	private TrainController controller;
	private TrainUser user;
	private int speedLimit = 5;
	private Table<Date, Integer, Integer> tacho;
	
	

	public TrainSensorImpl(TrainController controller, TrainUser user) {
		this.controller = controller;
		this.user = user;
		this.tacho = HashBasedTable.create();
	}

	public int getTachoSize() {
		return tacho.size();
	}

	public void tachograf() {
		tacho.put(new Date(), user.getJoystickPosition(), controller.getReferenceSpeed());
	}

	@Override
	public int getSpeedLimit() {
		return speedLimit;
	}

	@Override
	public void overrideSpeedLimit(int speedLimit) {
		if(speedLimit < 0 || speedLimit > 500) user.setAlarmState(true);
		if(speedLimit  < controller.getReferenceSpeed() * 0.5) user.setAlarmState(true);
		this.speedLimit = speedLimit;
		controller.setSpeedLimit(speedLimit);
		tachograf();
	}

}
