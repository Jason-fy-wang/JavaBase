package cyclicBarrier.demo6;

public class ThreadA extends Thread{
	private Service service;
	
	public ThreadA(Service service) {
		this.service = service;
	}
	
	@Override
	public void run() {
		service.testMethod();
	}
}
