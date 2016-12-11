package com.java.concurrent.timer;

import java.util.TimerTask;

public class SynchroTimerTask extends TimerTask {
	
	private String m_log;
	
	public SynchroTimerTask(String log) {
		m_log = log;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("[INFO] " + System.currentTimeMillis() + " " + m_log);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	
}
