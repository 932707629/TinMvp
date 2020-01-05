<manifest xmlns:android="http://schemas.android.com/apk/res/android">

    <application>
	   <activity
			android:name="${ativityPackageName}.${activityClass}"
            android:configChanges="orientation|keyboardHidden|screenSize"
            android:screenOrientation="portrait" />
    </application>
</manifest>