package fr.mabreizh.droid.tools;

import android.content.Context;
import android.text.TextUtils;
import fr.mabreizh.droid.R;

public class TimeTools {
	
	public static final int MILLIS = 0;
	public static final int SECOND = 1;
	public static final int MINUTE = 2;
	public static final int HOUR   = 3;
	public static final int DAY    = 4;
	public static final int WEEK   = 5;
	public static final int MONTH  = 6;
	public static final int YEAR   = 7;
	
	public static final boolean[] ALL 		= {true, true, true, true, true, true, true, true};
	public static final boolean   SMALL_DEF = true;
	
	public static String formatRemaining(Context context, long remainMillis, boolean[] which, boolean small)
	{
		StringBuilder sb = new StringBuilder();
		long remaining = remainMillis;
		int unit = YEAR;
		while(unit >= 0)
		{
			if(which[unit])
			{
				long units = convert(remaining, MILLIS, unit);
				int iUnits = (int) units;
				if(iUnits >= 1)
				{
					if(sb.length() > 0)
					{
						sb.append(" ");
					}
					sb.append(iUnits);
					sb.append(" ");
					String strUnit = unit(context, unit, small);
					sb.append(strUnit);
					if(iUnits > 1 && !TextUtils.isEmpty(strUnit) && !strUnit.endsWith("s"))
					{
						sb.append("s");
					}
					remaining = remaining - convert(iUnits, unit, MILLIS);
				}
			}
			unit--;
		}
		return sb.toString();
	}
	
	private static long convert(long val, int from, int to)
	{
		return (long) (val * fact(from, to));
	}
	
	private static double fact(int from, int to)
	{
		if(from == to) return 1;
		if(from < to) return 1 / fact(to, from);
		double fact = fact(from - 1, to);
		switch (from) {
		case SECOND:
			return 1000 * fact;
		case MINUTE:
		case HOUR:
			return 60 * fact;
		case DAY:
			return 24 * fact;
		case WEEK:
			return 7 * fact;
		case MONTH:
			return (52d / 12d) * fact;
		case YEAR:
			return 12 * fact;
		}
		return fact;
	}
	
	private static String unit(Context mContext, int unit, boolean small)
	{
//		if(unit <= lowest) return "";
		switch (unit) {
		case SECOND:
			return mContext.getString(small ? R.string.second_small : R.string.second);
		case MINUTE:
			return mContext.getString(small ? R.string.minute_small : R.string.minute);
		case HOUR:
			return mContext.getString(small ? R.string.hour_small : R.string.hour);
		case DAY:
			return mContext.getString(small ? R.string.day_small : R.string.day);
		case WEEK:
			return mContext.getString(small ? R.string.week_small : R.string.week);
		case MONTH:
			return mContext.getString(small ? R.string.month_small : R.string.month);
		case YEAR:
			return mContext.getString(small ? R.string.year_small : R.string.year);
		case MILLIS:
		default:
			return "";
		}
	}

}
