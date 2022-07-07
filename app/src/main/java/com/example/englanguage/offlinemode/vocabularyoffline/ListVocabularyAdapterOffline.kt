package com.example.englanguage.offlinemode.vocabularyoffline

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.englanguage.R
import com.example.englanguage.model.vocabulary.SuccessVocabulary
import com.example.englanguage.offlinemode.OneVocabularyOffActivity
import java.util.*
import kotlin.collections.ArrayList

class ListVocabularyAdapterOffline(private var context: Context?) : RecyclerView.Adapter<ListVocabularyAdapterOffline.ViewHolder>(), Filterable {
    private var mListVocabulary = mutableListOf<SuccessVocabulary>()
    private var mListVocabularyOld = mutableListOf<SuccessVocabulary>()
    private var mTTS: TextToSpeech? = null

    fun setData(mListVocabulary: MutableList<SuccessVocabulary> = mutableListOf<SuccessVocabulary>()) {
        this.mListVocabulary = mListVocabulary
        mListVocabularyOld = mListVocabulary
        notifyDataSetChanged()
    }

    fun reload(mListVocabulary: List<SuccessVocabulary>?) {
        this.mListVocabulary.clear()
        this.mListVocabulary.addAll(mListVocabulary!!)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_vocabulary_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val successVocabulary = mListVocabulary[position] ?: return
        callMTTS()
        if (successVocabulary != null || mListVocabulary != null) {
            holder.tvWord.text = mListVocabulary[position].word
            holder.tvMean.text = mListVocabulary[position].mean
            holder.tvExample.text = mListVocabulary[position].example
            val word = mListVocabulary[position].word
            val mean = mListVocabulary[position].mean

            holder.img_detail.setOnClickListener {
                if (holder.tvWord.text == "Determine") {
                    val speech = "/di'tə:min/"
                    val translate = context!!.getString(R.string.determine)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Establish") {
                    val speech = "/is'tæbliʃ/"
                    val translate = context!!.getString(R.string.establish)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Engage") {
                    val speech = "/in'geidʤ/"
                    val translate = context!!.getString(R.string.engage)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Abide by") {
                    val speech = "/ə'baid/"
                    val translate = context!!.getString(R.string.abideby)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Assurance") {
                    val speech = "/ə'ʃuərəns/"
                    val translate = context!!.getString(R.string.assurance)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Cancellation") {
                    val speech = "/,kænse'leiʃn/"
                    val translate = context!!.getString(R.string.cancellation)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Agreement") {
                    val speech = "/ə'gri:mənt/"
                    val translate = context!!.getString(R.string.agreement)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Satisfaction") {
                    val speech = "/,sætis'fækʃn/"
                    val translate = context!!.getString(R.string.satisfaction)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Productive") {
                    val speech = "/prəˈdʌktɪv/"
                    val translate = context!!.getString(R.string.productive)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Persuasion") {
                    val speech = "/pə'sweiʤn/"
                    val translate: String = context!!.getString(R.string.persuasion)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Market") {
                    val speech = "/'mɑ:kit/"
                    val translate: String = context!!.getString(R.string.market)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Inspiration") {
                    val speech = "/,inspə'reiʃn/"
                    val translate: String = context!!.getString(R.string.inspiration)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Fad") {
                    val speech = "/fæd/"
                    val translate = context!!.getString(R.string.fad)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Currently") {
                    val speech = "/ˈkʌrəntli/"
                    val translate = context!!.getString(R.string.currently)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Convince") {
                    val speech = "/kən'vins/"
                    val translate: String = context!!.getString(R.string.convince)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Consume") {
                    val speech = "/kən'sju:m/"
                    val translate = context!!.getString(R.string.consume)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Competition") {
                    val speech = "/,kɔmpi'tiʃn/"
                    val translate = context!!.getString(R.string.competition)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Compare") {
                    val speech = "/kəm'peə/"
                    val translate = context!!.getString(R.string.compare)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Attract") {
                    val speech = "/ə'trækt/"
                    val translate = context!!.getString(R.string.attract)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Resolve") {
                    val speech = "/ri'zɔlv/"
                    val translate = context!!.getString(R.string.resolve)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Specific") {
                    val speech = "/spi'sifik/"
                    val translate = context!!.getString(R.string.specific)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Provision") {
                    val speech = "/provision/"
                    val translate = context!!.getString(R.string.provision)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Characteristic") {
                    val speech = "/,kæriktə'ristik/"
                    val translate = context?.getString(R.string.characteristic)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Consequence") {
                    val speech = "/'kɔnsikwəns/"
                    val translate = context?.getString(R.string.consequence)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Consider") {
                    val speech = "/kən'sidə/"
                    val translate = context?.getString(R.string.consider)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Cover") {
                    val speech = "/'kʌvə/"
                    val translate = context?.getString(R.string.cover)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Expiration") {
                    val speech = "/,ekspaiə'reiʃn/"
                    val translate = context?.getString(R.string.expiration)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Frequently") {
                    val speech = "/ˈfriːkwəntli/"
                    val translate = context?.getString(R.string.frequently)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Imply") {
                    val speech = "/im'plai/"
                    val translate = context?.getString(R.string.imply)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Protect") {
                    val speech = "/protect/"
                    val translate = context?.getString(R.string.protect)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Promise") {
                    val speech = "/promise/"
                    val translate = "Người cộng tác kinh doanh hứa rằng tấm nệm mới của chúng ta sẽ đến trước trưa thứ Bảy"
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Reputation") {
                    val speech = "/,repju:'teiʃn/"
                    val translate = "Công ty biết rằng tiếng tăm sản phẩm của họ là thứ tài sản quan trọng nhất mà họ có"
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Require") {
                    val speech = "/ri'kwaiə/"
                    val translate = "Luật pháp yêu cầu mỗi món hàng trình bày rõ ràng thông tin về bảo hành"
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Variety") {
                    val speech = "/və'raiəti/"
                    val translate = context?.getString(R.string.variety)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Address") {
                    val speech = "/ə'dres/"
                    val translate = "Kế hoạch kinh doanh của Marco nhằm vào nhu cầu của những chủ doanh nghiệp nhỏ"
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Avoid") {
                    val speech = "/ə'vɔid/"
                    val translate = "Nhằm tránh lụn bại việc làm ăn, những người chủ nên chuẩn bị một kế hoạch KD phù hợp"
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Demonstrate") {
                    val speech = "/'demənstreit/"
                    val translate = "Vị giáo sư đã chứng minh thông qua bài học tình huống là một kế hoạch kinh doanh có thể gây ấn tượng với một người cho vay"
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Develop") {
                    val speech = "/di'veləp/"
                    val translate = "Lily đã phát triển ý tưởng của cô ta vào kế hoạch kinh doanh bằng cách tham dự một lớp học tại trường cao đẳng cộng đồng"
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Evaluate") {
                    val speech = "/i'væljueit/"
                    val translate = "Đánh giá sức cạnh tranh của bạn là việc quan trọng khi lập một kế hoạch kinh doanh"
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Gather") {
                    val speech = "/'gæðə/"
                    val translate = "Chúng tôi thu thập thông tin cho bản kế hoạch của mình từ nhiều nguồn"
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Substitution") {
                    val speech = "/,sʌbsti'tju:ʃn/"
                    val translate = "Việc anh lấy tên giả thay cho tên thật khiến cho tài liệu có vẻ như không thành thật"
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Offer") {
                    val speech = "/'ɔfə/"
                    val translate = context?.getString(R.string.offer)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Primarily") {
                    val speech = "/'praimərili/"
                    val translate = context?.getString(R.string.primarily)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Risk") {
                    val speech = "/rɪsk/"
                    val translate = context?.getString(R.string.risk)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Strategy") {
                    val speech = "/ˈstrætədʒi/"
                    val translate = context?.getString(R.string.strategy)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Strong") {
                    val speech = "/strɔɳ/"
                    val translate = context?.getString(R.string.strong)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Accommodate") {
                    val speech = "/ə'kɔmədeit/"
                    val translate = context?.getString(R.string.accommodate)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Arrangement") {
                    val speech = "/ə'reindʤmənt/"
                    val translate = context?.getString(R.string.arrangement)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Association") {
                    val speech = "/ə,sousi'eiʃn/"
                    val translate = context?.getString(R.string.association)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Attend") {
                    val speech = "/ə'tend/"
                    val translate = context?.getString(R.string.attend)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "get in touch") {
                    val speech = "N/A"
                    val translate = context?.getString(R.string.getintouch)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "hold") {
                    val speech = "/hould/"
                    val translate = context?.getString(R.string.hold)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "take part in") {
                    val speech = "N/A"
                    val translate = context?.getString(R.string.takepartin)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Location") {
                    val speech = "/lou'keiʃn/"
                    val translate = context?.getString(R.string.location)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Overcrowded") {
                    val speech = "/əʊvəˈkraʊdɪd/"
                    val translate = context?.getString(R.string.overcrowded)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Register") {
                    val speech = "/'redʤistə/"
                    val translate = context?.getString(R.string.register)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Select") {
                    val speech = "/si'lekt/"
                    val translate = context?.getString(R.string.select)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Session") {
                    val speech = "/'seʃn/"
                    val translate = context?.getString(R.string.session)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "access") {
                    val speech = "/ˈækses/"
                    val translate = context?.getString(R.string.access)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "allocate") {
                    val speech = "/ˈæləkeɪt/"
                    val translate = context?.getString(R.string.allocate)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Compatible") {
                    val speech = "/kəm'pætəbl/"
                    val translate = context?.getString(R.string.compatible)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Delete") {
                    val speech = "/di'li:t/"
                    val translate = context?.getString(R.string.delete)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Display") {
                    val speech = "/dis'plei/"
                    val translate = context?.getString(R.string.display)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Duplicate") {
                    val speech = "/'dju:plikit/"
                    val translate = context?.getString(R.string.duplicate)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Failure") {
                    val speech = "/'feiljə/"
                    val translate = context?.getString(R.string.failure)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Figure out") {
                    val speech = "/ˈfɪɡə/ /aʊt/"
                    val translate = context?.getString(R.string.figureout)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Ignore") {
                    val speech = "/ig'nɔ:/"
                    val translate = context?.getString(R.string.ignore)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Search") {
                    val speech = "/sə:tʃ/"
                    val translate = context?.getString(R.string.search)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Shut down") {
                    val speech = "/ʃʌt/ /daʊn/"
                    val translate = context?.getString(R.string.shutdown)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Warning") {
                    val speech = "/'wɔ:niɳ/"
                    val translate = context?.getString(R.string.warning)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
            }

            holder.layout_item.setOnClickListener {
                val intent = Intent(context, OneVocabularyOffActivity::class.java)
                intent.putExtra("word", word)
                intent.putExtra("mean", mean)
                context!!.startActivity(intent)
            }

        }
    }

    override fun getItemCount(): Int {
        return if (mListVocabulary != null) {
            mListVocabulary.size
        } else 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvWord: TextView
        val tvMean: TextView
        val tvExample: TextView
        val img_detail: ImageView
        val layout_item: LinearLayout

        init {
            tvWord = itemView.findViewById(R.id.word)
            tvMean = itemView.findViewById(R.id.mean)
            tvExample = itemView.findViewById(R.id.example)
            img_detail = itemView.findViewById(R.id.img_detail)
            layout_item = itemView.findViewById(R.id.layout_item)
        }
    }

    fun release() {
        context = null
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val strSearch = charSequence.toString()
                mListVocabulary = if (strSearch.isEmpty()) {
                    mListVocabularyOld
                } else {
                    val list: MutableList<SuccessVocabulary> = ArrayList()
                    for (successVocabulary in mListVocabularyOld!!) {
                        if (successVocabulary.word.toLowerCase()
                                .contains(strSearch.toLowerCase())
                        ) {
                            list.add(successVocabulary)
                        }
                    }
                    list
                }
                val filterResults = FilterResults()
                filterResults.values = mListVocabulary
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                mListVocabulary = (filterResults.values as List<SuccessVocabulary>).toMutableList()
                notifyDataSetChanged()
            }
        }
    }

    fun openDialogShowVocabulary(gravity: Int, position: Int, speech: String, translate: String) {
        val dialog = Dialog(context!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_view_xemchitiet)
        val window = dialog.window
        if (window == null) {
            return
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val windowAttributes = window.attributes
        windowAttributes.gravity = gravity
        window.attributes = windowAttributes
        if (Gravity.CENTER == gravity) {
            dialog.setCancelable(true)
        } else {
            dialog.setCancelable(false)
        }
        val wordDialog = dialog.findViewById<TextView>(R.id.wordDialog)
        val speechDialog = dialog.findViewById<TextView>(R.id.speechDialog)
        val meanDialog = dialog.findViewById<TextView>(R.id.meanDialog)
        val exampleDialog = dialog.findViewById<TextView>(R.id.exampleDialog)
        val translateDialog = dialog.findViewById<TextView>(R.id.translateDialog)
        val imgVolumeDialog = dialog.findViewById<ImageView>(R.id.imgVolumeDialog)
        val btnConFirmDialog: Button = dialog.findViewById(R.id.btnConFirmDialog)
        val word: String = mListVocabulary[position].word
        val mean: String = mListVocabulary[position].mean
        val example: String = mListVocabulary[position].example
        wordDialog.text = word
        speechDialog.text = speech
        meanDialog.text = mean
        exampleDialog.text = example
        translateDialog.text = translate

        btnConFirmDialog.setOnClickListener {
            dialog.dismiss()
        }

        imgVolumeDialog.setOnClickListener {
            speak(word)
        }

        dialog.show()
    }

    fun callMTTS() {
        mTTS = TextToSpeech(context, object : TextToSpeech.OnInitListener {
            override fun onInit(i: Int) {
                if (i == TextToSpeech.SUCCESS) {
                    val result = mTTS!!.setLanguage(Locale.ENGLISH)
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported")
                    }
                    run { Log.e("TTS", "Initialization failed") }
                }
            }
        })
    }

    fun speak(word: String) {
        var text: String? = word
        mTTS!!.speak(text, TextToSpeech.QUEUE_FLUSH, null)
    }
}