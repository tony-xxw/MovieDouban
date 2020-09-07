package mvp.wyyne.douban.moviedouban.flutter

import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity
import mvp.wyyne.douban.moviedouban.R


class FlutterSampleActivity : FlutterActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flutter_sample)
    }

}