package com.example.englanguage.adapter

import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.englanguage.R
import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.englanguage.extensions.toast

class ExamAdapter(
    private val listImage: List<String>,
    private val listWordA: List<String>,
    private val listWordB: List<String>,
    private val listWordC: List<String>,
    private val listWordD: List<String>,
    private val context: Context,
    private val btn_submit: Button,
    private var listSubmit: Int
) : RecyclerView.Adapter<ExamAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_exam, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.tv_A.text = listWordA[position]
        holder.tv_B.text = listWordB[position]
        holder.tv_C.text = listWordC[position]
        holder.tv_D.text = listWordD[position]
        holder.idExam.text = (position + 1).toString()
        Glide.with(context).load(listImage[position]).into(holder.img_exam);
        holder.tv_A.setOnClickListener {
            holder.circleA.visibility = View.VISIBLE
            holder.circleB.visibility = View.GONE
            holder.circleC.visibility = View.GONE
            holder.circleD.visibility = View.GONE

            if (holder.circleA.visibility == View.VISIBLE) {
                if (listWordA[position] == "Abide by" && (position + 1).toString() == "1" ||
                    listWordA[position] == "Accommodate" && (position + 1).toString() == "2" ||
                    listWordA[position] == "Address" && (position + 1).toString() == "3" ||
                    listWordA[position] == "Agreement" && (position + 1).toString() == "4" ||
                    listWordA[position] == "Arrangement" && (position + 1).toString() == "5" ||
                    listWordA[position] == "Association" && (position + 1).toString() == "6" ||
                    listWordA[position] == "Assurance" && (position + 1).toString() == "7" ||
                    listWordA[position] == "Attend" && (position + 1).toString() == "8" ||
                    listWordA[position] == "Attract" && (position + 1).toString() == "9" ||
                    listWordA[position] == "Avoid" && (position + 1).toString() == "10"
                ) {
                    listSubmit++
                }
            }
        }
        holder.tv_B.setOnClickListener {
            holder.circleA.visibility = View.GONE
            holder.circleB.visibility = View.VISIBLE
            holder.circleC.visibility = View.GONE
            holder.circleD.visibility = View.GONE

            if (holder.circleB.visibility == View.VISIBLE) {
                if (listWordB[position] == "Abide by" && (position + 1).toString() == "1" ||
                    listWordB[position] == "Accommodate" && (position + 1).toString() == "2" ||
                    listWordB[position] == "Address" && (position + 1).toString() == "3" ||
                    listWordB[position] == "Agreement" && (position + 1).toString() == "4" ||
                    listWordB[position] == "Arrangement" && (position + 1).toString() == "5" ||
                    listWordB[position] == "Association" && (position + 1).toString() == "6" ||
                    listWordB[position] == "Assurance" && (position + 1).toString() == "7" ||
                    listWordB[position] == "Attend" && (position + 1).toString() == "8" ||
                    listWordB[position] == "Attract" && (position + 1).toString() == "9" ||
                    listWordB[position] == "Avoid" && (position + 1).toString() == "10"
                ) {
                    listSubmit++
                }
            }
        }
        holder.tv_C.setOnClickListener {
            holder.circleA.visibility = View.GONE
            holder.circleB.visibility = View.GONE
            holder.circleC.visibility = View.VISIBLE
            holder.circleD.visibility = View.GONE

            if (holder.circleC.visibility == View.VISIBLE) {
                if (listWordC[position] == "Abide by" && (position + 1).toString() == "1" ||
                    listWordC[position] == "Accommodate" && (position + 1).toString() == "2" ||
                    listWordC[position] == "Address" && (position + 1).toString() == "3" ||
                    listWordC[position] == "Agreement" && (position + 1).toString() == "4" ||
                    listWordC[position] == "Arrangement" && (position + 1).toString() == "5" ||
                    listWordC[position] == "Association" && (position + 1).toString() == "6" ||
                    listWordC[position] == "Assurance" && (position + 1).toString() == "7" ||
                    listWordC[position] == "Attend" && (position + 1).toString() == "8" ||
                    listWordC[position] == "Attract" && (position + 1).toString() == "9" ||
                    listWordC[position] == "Avoid" && (position + 1).toString() == "10"
                ) {
                    listSubmit++
                }
            }
        }
        holder.tv_D.setOnClickListener {
            holder.circleA.visibility = View.GONE
            holder.circleB.visibility = View.GONE
            holder.circleC.visibility = View.GONE
            holder.circleD.visibility = View.VISIBLE

            if (holder.circleD.visibility == View.VISIBLE) {

                if (listWordD[position] == "Abide by" && (position + 1).toString() == "1" ||
                    listWordD[position] == "Accommodate" && (position + 1).toString() == "2" ||
                    listWordD[position] == "Address" && (position + 1).toString() == "3" ||
                    listWordD[position] == "Agreement" && (position + 1).toString() == "4" ||
                    listWordD[position] == "Arrangement" && (position + 1).toString() == "5" ||
                    listWordD[position] == "Association" && (position + 1).toString() == "6" ||
                    listWordD[position] == "Assurance" && (position + 1).toString() == "7" ||
                    listWordD[position] == "Attend" && (position + 1).toString() == "8" ||
                    listWordD[position] == "Attract" && (position + 1).toString() == "9" ||
                    listWordD[position] == "Avoid" && (position + 1).toString() == "10"
                ) {
                    listSubmit++
                }
            }
        }
        btn_submit.setOnClickListener {
            context.toast(listSubmit.toString())
        }
    }

    override fun getItemCount(): Int {
        return listWordA.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_A: TextView
        var tv_B: TextView
        var tv_C: TextView
        var tv_D: TextView
        var idExam: TextView
        var img_exam: ImageView
        var circleA: ImageView
        var circleB: ImageView
        var circleC: ImageView
        var circleD: ImageView

        init {
            img_exam = itemView.findViewById(R.id.img_exam)
            tv_A = itemView.findViewById(R.id.tv_A)
            tv_B = itemView.findViewById(R.id.tv_B)
            tv_C = itemView.findViewById(R.id.tv_C)
            tv_D = itemView.findViewById(R.id.tv_D)
            circleA = itemView.findViewById(R.id.circleA)
            circleB = itemView.findViewById(R.id.circleB)
            circleC = itemView.findViewById(R.id.circleC)
            circleD = itemView.findViewById(R.id.circleD)
            idExam = itemView.findViewById(R.id.idExam)
        }
    }
}