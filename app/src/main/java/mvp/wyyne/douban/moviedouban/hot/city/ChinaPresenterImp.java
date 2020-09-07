package mvp.wyyne.douban.moviedouban.hot.city;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import androidx.core.app.ActivityCompat;

import mvp.wyyne.douban.moviedouban.home.IMain;

/**
 * @author XXW
 * @date 2018/1/19
 */

public class ChinaPresenterImp implements ChinaPresenter {
    private IMain mMain;
    private Context mContext;
    private LocationManager mLocationManager;
    private Criteria mCriteria;

    public ChinaPresenterImp(IMain main, Context context) {
        mMain = main;
        mContext = context;
    }


    @Override
    public void getData() {

    }

    @Override
    public void initLocation() {
        mLocationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);

        mCriteria = new Criteria();
        mCriteria.setAccuracy(Criteria.ACCURACY_COARSE);
        mCriteria.setAltitudeRequired(false);
        mCriteria.setBearingRequired(false);
        mCriteria.setCostAllowed(true);
        mCriteria.setPowerRequirement(Criteria.ACCURACY_LOW);


        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = mLocationManager.getLastKnownLocation(mLocationManager.getBestProvider(mCriteria, true));
        location.getProvider();
    }
}
