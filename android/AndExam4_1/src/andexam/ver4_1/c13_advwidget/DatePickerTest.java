package andexam.ver4_1.c13_advwidget;

import andexam.ver4_1.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class DatePickerTest extends Activity {
	DatePicker mDate;
	TextView mTxtDate;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datepickertest);
		
		mDate = (DatePicker)findViewById(R.id.datepicker);
		mTxtDate = (TextView)findViewById(R.id.txtdate);
		mDate.init(mDate.getYear(), mDate.getMonth(), mDate.getDayOfMonth(), 
			new DatePicker.OnDateChangedListener() {
			public void onDateChanged(DatePicker view, int year, 
				int monthOfYear, int dayOfMonth) {
				mTxtDate.setText(String.format("%d/%d/%d", year, 
						monthOfYear + 1, dayOfMonth));
				}
			}
		);

		// 선택기로부터 날짜 조사
		findViewById(R.id.btnnow).setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String result = String.format("%d년 %d월 %d일", mDate.getYear(),
						mDate.getMonth() + 1, mDate.getDayOfMonth());
				Toast.makeText(DatePickerTest.this, result, 0).show();
			}
		});		
	}
}
