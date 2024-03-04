package hu.bme.mit.train.interfaces;

public interface TrainSensor {

	int getSpeedLimit();

	void tachograf();

	int getTachoSize();

	void overrideSpeedLimit(int speedLimit);

}
