
package statusbar.com.statusbardemo;

import android.app.Activity;
import android.widget.Toast;

public class JSKit {
	private Activity ma;
	
	public JSKit(Btn06Activity context) {
		this.ma = context;
	}
	
	public void submitAndroid(String msg) {
		Toast.makeText(ma, msg, Toast.LENGTH_SHORT).show();
	}
}
