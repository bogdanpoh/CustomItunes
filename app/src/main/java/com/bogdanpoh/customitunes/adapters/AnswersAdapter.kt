package com.bogdanpoh.customitunes.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bogdanpoh.customitunes.R
import com.bogdanpoh.customitunes.activities.ShowActivity
import com.bogdanpoh.customitunes.helpers.ViewHelpers
import com.bogdanpoh.customitunes.models.MainModel
import com.bogdanpoh.customitunes.models.Answer
import com.squareup.picasso.Picasso
import maes.tech.intentanim.CustomIntent.customType

class AnswersAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mAnswersList: ArrayList<Answer> = ArrayList()

    fun setupAnswer(mainModel: MainModel) {
        mAnswersList.clear()
        mAnswersList.addAll(mainModel.feed.results)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.cell_audiobook, parent, false)
        return AnswerViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mAnswersList.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AnswerViewHolder) {
            holder.bind(answer = mAnswersList[position])
        }
    }


    class AnswerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var mImgArtWork: ImageView = itemView.findViewById(R.id.cell_art_work)
        private var mTxtNameBook: TextView = itemView.findViewById(R.id.cell_txt_name_book)
        private var mTxtArtistName: TextView = itemView.findViewById(R.id.cell_txt_artist_name)


        fun bind(answer: Answer) {
            Picasso.get().load(answer.artworkUrl100).into(mImgArtWork)

            mTxtNameBook.text = answer.name
            //mTxtNameBook.typeface = ResourcesCompat.getFont(itemView.context, R.font.lato_regular)
            mTxtArtistName.text = answer.artistName
            //mTxtArtistName.typeface = ResourcesCompat.getFont(itemView.context, R.font.lato_regular)

            ViewHelpers.Font.setFontTextViews(R.font.lato_regular, itemView.context, arrayListOf(mTxtNameBook, mTxtArtistName))

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, ShowActivity::class.java)

                intent.putExtra("id", answer.id)
                intent.putExtra("artWorkUrl", answer.artworkUrl100)
                intent.putExtra("type", answer.kind)

                itemView.context.startActivity(intent)
                customType(itemView.context, "left-to-right")
            }
        }
    }
}