package com.frc8.team8vision.util;

/**
 * Created by Alvin on 9/10/2017.
 */

public class AutoCloseableLock implements AutoCloseable{

	private int closableState = 0;
	private ReadWriteLock mLock;

	public AutoCloseableLock(ReadWriteLock lock, int lockState){
		this.closableState = lockState;
		this.mLock = lock;
	}

	@Override
	public void close() throws Exception {
		switch (closableState){
			case ReadWriteLock.WRITING:
				mLock.unlockWrite();
				break;
			case ReadWriteLock.READING:
				mLock.unlockRead();
				break;
			case 0:
				throw new IllegalMonitorStateException("Lock "+mLock.getName()+" is trying to be closed in IDLE state");
		}
	}
}

