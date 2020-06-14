package countdown.demo1;

import java.util.concurrent.CountDownLatch;

public class Service {
	private CountDownLatch countDownLatch = new CountDownLatch(1);
	
	public void testMethod(){
		try {
			System.out.println("AAAAAAAAA");
			//�жϼ����Ƿ�Ϊ0    ���Ϊ0  ��ô�ü���ִ��   ����ȴ�
			countDownLatch.await();
			System.out.println("BBBBBBBBB");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void downMethod(){
		try {
			Thread.sleep(1000*5);
			System.out.println("count down");
			countDownLatch.countDown();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
