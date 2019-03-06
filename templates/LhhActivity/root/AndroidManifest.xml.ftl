<manifest xmlns:android="http://schemas.android.com/apk/res/android"
	package="${packageName}">

    <application>
		<activity
            android:name="${ativityPackageName}.${activityClass}"
			android:screenOrientation="portrait" />
    </application>
</manifest>
