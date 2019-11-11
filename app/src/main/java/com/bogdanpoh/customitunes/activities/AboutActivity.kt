package com.bogdanpoh.customitunes.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bogdanpoh.customitunes.R
import com.bogdanpoh.customitunes.helpers.ViewHelpers
import com.r0adkll.slidr.Slidr
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.toolbar.*
import maes.tech.intentanim.CustomIntent.customType

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        toolbar_back.visibility = View.VISIBLE
        toolbar_info.visibility = View.GONE
        toolbar_title.text = getString(R.string.about_title)

        Slidr.attach(this)

        toolbar_back.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            customType(this, "right-to-left")
        }

        ViewHelpers.Font.setFontTextViews(
            R.font.lato, this, arrayListOf(
                toolbar_title,
                about_position_dev_label,
                about_position_des_label,
                about_name_designer,
                about_name_developer
            )
        )

        ViewHelpers.Font.setFontTextViews(
            R.font.lato_regular, this, arrayListOf(
                about_email_label,
                about_instagram_label,
                about_github_label,
                about_email_developer,
                about_instagram_developer,
                about_github_developer,
                about_email_designer_label,
                about_instagram_designer_label,
                about_behance_designer_label,
                about_email_designer,
                about_instagram_designer,
                about_behance_developer
            )
        )
    }
}
