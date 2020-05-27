package mm.gov.dtvet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import kotlinx.android.synthetic.main.activity_course.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var course: CardView
    lateinit var job:CardView
    lateinit var service:CardView
    lateinit var information:CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        course = findViewById(R.id.coursecard)
        job = findViewById(R.id.jobcard)
        service = findViewById(R.id.servicecard)
        information = findViewById(R.id.informationcard)

        course.setOnClickListener {
            val intent = Intent(this, CourseActivity::class.java)
            startActivity(intent)
        }

//        job.setOnClickListener {
//            val intent = Intent(this, JobActivity::class.java)
//            startActivity(intent)
//        }

        service.setOnClickListener {
            val intent = Intent(this, ServiceActivity::class.java)
            startActivity(intent)
        }

        information.setOnClickListener {
            val intent = Intent(this, InformationActivity::class.java)
            startActivity(intent)
        }

    }
}

