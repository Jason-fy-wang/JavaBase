package semaphore.yzit.semaphore.interrupt;

import java.util.concurrent.Semaphore;

public class ServerDemo {
	//�������߳̿��Եõ�����ź���  Ȼ��ȥ��������
	private Semaphore semphore = new Semaphore(1);
	public void testMethod(){
		try {
			//��ȡ�ź���
			semphore.acquire();
			System.out.println(Thread.currentThread().getName()+
					"  begin time="+System.currentTimeMillis());
			Thread.sleep(1000*5);
			System.out.println(Thread.currentThread().getName()+
					" end time ="+System.currentTimeMillis());
			//�ͷ��ź���
			semphore.release();
		} catch (InterruptedException e) {
			System.out.println("�߳�--"+Thread.currentThread().getName()+
					" ������catch");
			e.printStackTrace();
		}
	}
}
