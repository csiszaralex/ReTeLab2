package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;

import static org.mockito.Mockito.*;

public class TrainSensorTest {

    TrainController controller;
	TrainUser user;
    TrainSensor s;
	
	@Before
	public void before() {
        controller = mock(TrainController.class);
        user = mock(TrainUser.class);
        s = new TrainSensorImpl(controller, user);
	}

    @Test
    public void test1() {

        when(controller.getReferenceSpeed()).thenReturn(0);

        s.overrideSpeedLimit(-100);

        verify(user, times(2)).setAlarmState(true); 
    }
    @Test
    public void test2() {

        when(controller.getReferenceSpeed()).thenReturn(0);

        s.overrideSpeedLimit(501);

        verify(user, times(1)).setAlarmState(true); 
    }
    @Test
    public void test3() {

        when(controller.getReferenceSpeed()).thenReturn(100);

        s.overrideSpeedLimit(49);

        verify(user, times(1)).setAlarmState(true); 
    }
    @Test
    public void test4() {

        when(controller.getReferenceSpeed()).thenReturn(100);

        s.overrideSpeedLimit(90);

        verify(user, times(0)).setAlarmState(true); 
    }
}
