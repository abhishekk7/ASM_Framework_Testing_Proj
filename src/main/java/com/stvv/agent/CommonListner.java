package com.stvv.agent;

/**
 * Created by abhis on 3/18/2017.
 */

import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;

public class CommonListner extends RunListener {

	public void testStarted(Description description) {

		HashsetUtil.writeToFile("[TEST] " + description.getClassName() + ":" + description.getMethodName());

	}

	public void testFinished(Description description) {

		HashsetUtil.printLines();
	}
}