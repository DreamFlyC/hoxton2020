import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-8 14:37:43
 * @description :
 */
public class Base {
	private String baseName = "base";

	public Base() {
		callName();
	}

	public void callName() {
		System.out.println(baseName);
	}
}

class Sub extends Base {
	private static String baseName = "sub";

	public void callName() {
		System.out.println(baseName);
	}

	/*public static void main(String[] args) {
		Base b = new Sub();
	}*/

	public static void main(String[] args) {
		ThreadLocal<SimpleDateFormat> dft = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd,HHmmss"));
		final DateFormat df = new SimpleDateFormat("yyyyMMdd,HHmmss");
		ExecutorService ts = Executors.newFixedThreadPool(100);
		for (;;) {
			ts.execute(() -> {
				try {
					//生成随机数，格式化日期
//					String format =  df.format(new Date(Math.abs(new Random().nextLong())));
					String format = dft.get().format(new Date(Math.abs(new Random().nextLong())));
					System.out.println(format);
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(1);
				}
			});
		}
	}
}